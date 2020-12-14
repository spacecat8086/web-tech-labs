package by.vlados.carrentalsystem.dao.impl;

import by.vlados.carrentalsystem.connectionpool.ConnectionPool;
import by.vlados.carrentalsystem.dao.RepairBillDao;
import by.vlados.carrentalsystem.entity.Entity;
import by.vlados.carrentalsystem.entity.RepairBill;
import by.vlados.carrentalsystem.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados 
 * 
 * DAO implementation for RepairBillDao Interface
 */
public class RepairBillDaoImpl implements RepairBillDao {

    private Connection connection;
    private ConnectionPool pool;
    private static final Logger LOG = Logger.getLogger(RepairBillDaoImpl.class);
    private static final String GET_ALL = "SELECT damagebill.`bill_id`, damagebill.`damage`, "
            + "damagebill.`sum`, CAR.`carname` FROM damagebill LEFT JOIN "
            + "`ORDERC` ON `damagebill`.`order_id`=ORDERC.`order_id` "
            + "LEFT JOIN CAR ON ORDERC.`car_id`=CAR.`car_id` WHERE damagebill.`repaired`=0;";
    private static final String REPAIR = "UPDATE DAMAGEBILL SET DAMAGEBILL.`repaired`=1"
            + " where DAMAGEBILL.`bill_id`=?;";

    /**
     *
     * @throws DAOException
     */
    public RepairBillDaoImpl() throws DAOException {
        this.pool = ConnectionPool.getInstance();
        this.connection = pool.getConnection();
    }

    /**
     *
     * @return list of all repair bills
     * @throws DAOException
     */
    @Override
    public List getAll() throws DAOException {
        LOG.info("RepairBillDaoImpl.getAll()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(GET_ALL);
            ResultSet rs = stm.executeQuery();
            List<RepairBill> list = new ArrayList<>();
            while (rs.next()) {
                RepairBill bill = new RepairBill();
                bill.setCarname(rs.getString("carname"));
                bill.setSum(rs.getInt("sum"));
                bill.setDamage(rs.getString("damage"));
                bill.setId(rs.getInt("bill_id"));
                list.add(bill);
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while RepairBillDaoImpl.getAll()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);

        }
    }

    /**
     *
     * @param id of the repair bill
     * @return true if operation was successful, otherwise
     * @throws DAOException
     */

    public boolean repair(int id) throws DAOException {
        LOG.info("RepairBillDaoImpl.repair()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(REPAIR);
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            closePS(stm);
            pool.returnConnection(connection);
            throw new DAOException("DAOException while RepairBillDaoImpl.repair()", ex);
        }
    }

    /**
     * not yet implemented
     *
     * @param id
     * @return
     * @throws DAOException
     */


}
