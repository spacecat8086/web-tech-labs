package by.vlados.carrentalsystem.dao.impl;

import by.vlados.carrentalsystem.connectionpool.ConnectionPool;
import by.vlados.carrentalsystem.dao.OrderDao;
import by.vlados.carrentalsystem.entity.Order;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.exception.OrderException;
import java.sql.Connection;
import java.sql.Date;
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
 * DAO implementation for OrderDao Interface
 *
 */
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);
    private ConnectionPool pool;
    private static final String ORDERC_INSERT = "INSERT INTO ORDERC(USER_ID, CAR_ID, SUM_TO_PAY, CONFIRMED, "
            + "PAIDFOR, RETURNED) VALUES(?, ?, ?, 0, 0, 0);";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM ORDERC;";
    private static final String BILL_INSERT = "INSERT INTO bill (bill.`order_id`,"
            + " bill.`period`, bill.`price`, bill.`startdate`) VALUES (LAST_INSERT_ID(), ?, ?, ?);";
    private static final String GET_UNPAID_BY_USER_ID = "SELECT ORDERC.`sum_to_pay`,ORDERC.`order_id`,"
            + " CAR.`carname`,bill.`period`, bill.`startdate`, CAR.`price` "
            + "FROM ORDERC LEFT JOIN CAR ON ORDERC.`car_id`=CAR.`car_id` INNER JOIN bill ON"
            + " ORDERC.`order_id`=bill.`order_id` WHERE ORDERC.`user_id`= ? AND ORDERC.`paidfor`=0 "
            + "AND ORDERC.`confirmed`=1;";
    private static final String UPDATE_SET_CONFIRMED = "UPDATE ORDERC SET ORDERC.`confirmed`=1"
            + " where ORDERC.`order_id`=?;";
    private static final String UPDATE_SET_RETURNED = "UPDATE ORDERC SET ORDERC.`returned`=1"
            + " where ORDERC.`order_id`=?;";
    private static final String UPDATE_SET_REFUSED = "UPDATE ORDERC SET ORDERC.`confirmed`=2,"
            + " ORDERC.`reason_for_refusal`= ? where ORDERC.`order_id`=?;";
    private static final String SELECT_CREDIT = "SELECT CLIENT.`credit` FROM CLIENT WHERE CLIENT.`user_id`=?;";
    private static final String PAY_FOR_ORDER = "UPDATE ORDERC SET ORDERC.`paidfor`=1"
            + " where ORDERC.`order_id`=?;";
    private static final String CHANGE_CREDIT = "UPDATE CLIENT SET CLIENT.`credit`=(? - ?)  WHERE CLIENT.`user_id`=?;";
    private static final String DELETE_ORDER = "DELETE FROM ORDERC where ORDERC.`order_id`=?;";
    private static final String SELECT_NEW_ORDERS = "SELECT ORDERC.`order_id`, CAR.`carname`, "
            + "CAR.`price`, CLIENT.`username`, CLIENT.`surname`, "
            + "CLIENT.`passport_number`, ORDERC.`sum_to_pay`, bill.`period`, bill.`startdate` FROM "
            + "ORDERC LEFT JOIN CAR ON ORDERC.`car_id`=CAR.`car_id` "
            + "LEFT JOIN CLIENT on ORDERC.`user_id`=CLIENT.`user_id` INNER JOIN bill ON"
            + " ORDERC.`order_id`=bill.`order_id`"
            + " WHERE ORDERC.`confirmed`=0;";
    private static final String SELECT_PAID_ORDERS = "SELECT ORDERC.`order_id`, CAR.`carname`, "
            + "CAR.`price`, CLIENT.`username`, CLIENT.`surname`, "
            + "CLIENT.`passport_number`, ORDERC.`sum_to_pay`, bill.`period`, bill.`startdate` FROM "
            + "ORDERC LEFT JOIN CAR ON ORDERC.`car_id`=CAR.`car_id` "
            + "LEFT JOIN CLIENT on ORDERC.`user_id`=CLIENT.`user_id` INNER JOIN bill "
            + "ON ORDERC.`order_id`=bill.`order_id` "
            + " WHERE ORDERC.`paidfor`=1 AND ORDERC.`returned`=0;";
    private static final String SELECT_UNPAID_ORDERS = "SELECT ORDERC.`order_id`, CAR.`carname`, "
            + "CAR.`price`, CLIENT.`username`, CLIENT.`surname`, "
            + "CLIENT.`passport_number`, ORDERC.`sum_to_pay`, bill.`period`, bill.`startdate` FROM "
            + "ORDERC LEFT JOIN CAR ON ORDERC.`car_id`=CAR.`car_id` "
            + "LEFT JOIN CLIENT on ORDERC.`user_id`=CLIENT.`user_id` INNER JOIN bill ON"
            + " ORDERC.`order_id`=bill.`order_id`"
            + " WHERE ORDERC.`confirmed`=1 AND ORDERC.`paidfor`=0 AND ORDERC.`returned`=0;";
    private static final String SELECT_ARCHIVE_ORDERS = "SELECT ORDERC.`order_id`, CAR.`carname`, CAR.`price`, "
            + "CLIENT.`username`, CLIENT.`surname`, CLIENT.`passport_number`,"
            + " ORDERC.`sum_to_pay` FROM ORDERC LEFT JOIN CAR ON ORDERC.`car_id`"
            + "=CAR.`car_id` LEFT JOIN CLIENT on ORDERC.`user_id`="
            + "CLIENT.`user_id` WHERE ORDERC.`returned`=1;";
    private static final String SELECT_DENIED_BY_ID = "SELECT ORDERC.`sum_to_pay`,ORDERC.`order_id`,"
            + "ORDERC.`reason_for_refusal`, bill.`period`, bill.`startdate`, CAR.`carname`, CAR.`price` "
            + "FROM ORDERC LEFT JOIN CAR ON ORDERC.`car_id`=CAR.`car_id` INNER JOIN bill ON"
            + " ORDERC.`order_id`=bill.`order_id` WHERE ORDERC.`user_id`= ? AND ORDERC.`paidfor`=0 "
            + "AND ORDERC.`confirmed`=2;";
    private static final String SELECT_ARCHIVED_BY_ID = "SELECT ORDERC.`order_id`,ORDERC.`sum_to_pay`, CAR.`carname`, CAR.`price`, "
            + "bill.`period`, bill.`startdate` FROM ORDERC INNER JOIN CAR ON "
            + "ORDERC.`car_id`=CAR.`car_id` INNER JOIN bill ON "
            + "ORDERC.`order_id`=bill.`order_id` "
            + "WHERE ORDERC.`user_id`= ? AND ORDERC.`paidfor`=1;";
    private static final String UPDATE_DAMAGE_ORDER = "UPDATE ORDERC SET ORDERC.`returned`=2"
            + " where ORDERC.`order_id`=?;";
    private static final String UPDATE_DAMAGE_DAMAGEBILL = "INSERT INTO `damagebill` "
            + "(sum, damage, order_id, repaired) VALUES (?, ?, ?, 0);";
    private Connection connection;

    /**
     *
     * @throws by.vlados.carrentalsystem.exception.DAOException
     */
    public OrderDaoImpl() throws DAOException {
        this.pool = ConnectionPool.getInstance();
        this.connection = pool.getConnection();
    }

    /**
     *
     * @param order - order made by some user writes order and bill for this
     * order into database
     * @throws DAOException
     */
    @Override
    public void create(Order order) throws DAOException {

        PreparedStatement stm = null;
        PreparedStatement stm1 = null;
        try {
            connection.setAutoCommit(false);
            LOG.info("OrderDaoImpl.create");
            int userId = order.getUserId();
            int carId = order.getCarId();
            int sumToPay = order.getSumToPay();
            int price = order.getPrice();
            Date date = order.getDate();
            int period = order.getPeriod();
            stm = connection.prepareStatement(ORDERC_INSERT);
            stm.setInt(1, userId);
            stm.setInt(2, carId);
            stm.setInt(3, sumToPay);
            stm.executeUpdate();
            stm1 = connection.prepareStatement(BILL_INSERT);
            stm1.setInt(1, period);
            stm1.setInt(2, price);
            stm1.setDate(3, date);
            stm1.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            LOG.info("Added order: " + order.toString());

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DAOException("DAOException while OrderDaoImpl.create() at rollback", e);
            }
            throw new DAOException("DAOException while OrderDaoImpl.create()", ex);
        } finally {
            closePS(stm);
            closePS(stm1);
            pool.returnConnection(connection);
        }

    }

    /**
     *
     * @param id - id of the user
     * @return list of all unpaid (but confirmed) orders by this user
     * @throws DAOException
     */
    @Override
    public List<Order> getUByUserId(int id) throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getUByUserId()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(GET_UNPAID_BY_USER_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setPeriod(rs.getInt("period"));
                order.setId(rs.getInt("order_id"));
                order.setDate(rs.getDate("startdate"));
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.getUByUserId()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id
     * @return true if upading orders with 'confirmed=1' was successful,
     * otherwise returns exception
     * @throws DAOException
     */
    public boolean confirm(int id) throws DAOException {
        LOG.info("OrderDaoImpl.confirm()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(UPDATE_SET_CONFIRMED);
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.confirm()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id of the order sets field returned as 1, which means car was
     * returned without any damage
     * @throws DAOException
     */
    @Override
    public void returnCar(int id) throws DAOException {
        LOG.info("OrderDaoImpl.returnCar()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(UPDATE_SET_RETURNED);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.returnCar()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id of the order
     * @param reason for refusal
     * @return true if update was successful, otherwise
     * @throws DAOException
     */
    @Override
    public boolean deny(int id, String reason) throws DAOException {
        LOG.info("OrderDaoImpl.deny()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(UPDATE_SET_REFUSED);
            stm.setString(1, ("\'" + reason + "\'"));
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.returnCar()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id of the order
     * @param idOr
     * @param sum
     * @return true id the payment was successful, otherwise
     * @throws DAOException
     */
    @Override
    public boolean pay(int id, int idOr, int sum) throws DAOException {
        LOG.info("OrderDaoImpl.pay()");
        PreparedStatement stm = null, stm1 = null, stm2 = null;

        try {
            stm1 = connection.prepareStatement(SELECT_CREDIT);
            stm1.setInt(1, id);
            ResultSet rs = stm1.executeQuery();
            rs.next();
            int credit = rs.getInt("credit");
            if (sum > credit) {
                return false;
            }
            connection.setAutoCommit(false);
            stm = connection.prepareStatement(PAY_FOR_ORDER);
            stm.setInt(1, idOr);
            stm.executeUpdate();
            stm2 = connection.prepareCall(CHANGE_CREDIT);
            stm2.setInt(1, credit);
            stm2.setInt(2, sum);
            stm2.setInt(3, id);
            stm2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DAOException("DAOException while OrderDaoImpl.pay() at rollback", e);
            }
            throw new DAOException("SQLException in ADI.pay():" + ex);
        } finally {
            closePS(stm);
            closePS(stm1);
            closePS(stm2);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id of the order
     * @return true id order was deleted successfully, otherwise
     * @throws DAOException
     */
    @Override
    public boolean delete(int id) throws DAOException {
        LOG.info("OrderDaoImpl.delete()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(DELETE_ORDER);
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException(" DAOException in ADI.delete():" + ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @return list of all newly made orders (for admin)
     * @throws DAOException
     */
    @Override
    public List<Order> getNewOrders() throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getNewOrders()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_NEW_ORDERS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setClientSurname(rs.getString("surname"));
                order.setPassNum(rs.getString("passport_number"));
                order.setId(rs.getInt("order_id"));
                order.setDate(rs.getDate("startdate"));
                order.setPeriod(rs.getInt("period"));
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.getNewOrders ()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @return list of all paid orders (for admin)
     * @throws DAOException
     */
    @Override
    public List<Order> getPaidOrders() throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getPaidOrders()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_PAID_ORDERS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setClientSurname(rs.getString("surname"));
                order.setPassNum(rs.getString("passport_number"));
                order.setId(rs.getInt("order_id"));
                order.setDate(rs.getDate("startdate"));
                order.setPeriod(rs.getInt("period"));
                order.setPaid(true);
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException ex) {
            throw new DAOException(ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @return list of all unpaid orders (for admin)
     * @throws DAOException
     */
    public List<Order> getUnPaidOrders() throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getUnPaidOrders()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_UNPAID_ORDERS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setClientSurname(rs.getString("surname"));
                order.setPassNum(rs.getString("passport_number"));
                order.setId(rs.getInt("order_id"));
                order.setPeriod(rs.getInt("period"));
                order.setDate(rs.getDate("startdate"));
                order.setPaid(true);
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.getUnPaidOrders()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @return list of all archived orders (for admin)
     * @throws DAOException
     */
    public List<Order> getArchiveOrders() throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getArchiveOrders()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_ARCHIVE_ORDERS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setClientSurname(rs.getString("surname"));
                order.setPassNum(rs.getString("passport_number"));
                order.setId(rs.getInt("order_id"));
                order.setPaid(true);
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException ex) {
            throw new DAOException("DAOException while OrderDaoImpl.getArchiveOrders()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id - id of the user
     * @return list of all denied orders for this user (with reason, why they
     * were denied)
     * @throws DAOException
     */
    public List<Order> getDByUserId(int id) throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrdercationDaoImpl.getDByUserId()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_DENIED_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setPeriod(rs.getInt("period"));
                order.setId(rs.getInt("order_id"));
                order.setDate(rs.getDate("startdate"));
                order.setRefusalReason(rs.getString("reason_for_refusal"));
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException ex) {
            throw new DAOException("DAOException while OrdercationDaoImpl.getDByUserId()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id - id of the user
     * @return list of all archived orders for this user
     * @throws DAOException
     */
    public List<Order> getAByUserId(int id) throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getAByUserId()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_ARCHIVED_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setSumToPay(rs.getInt("sum_to_pay"));
                order.setCarName(rs.getString("carname"));
                order.setPrice(rs.getInt("price"));
                order.setPeriod(rs.getInt("period"));
                order.setDate(rs.getDate("startdate"));
                list.add(order);
            }
            return list;
        } catch (SQLException | OrderException e) {
            throw new DAOException("DAOException while OrderDaoImpl.getAByUserId()", e);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id
     * @param damage
     * @param damageCost
     * @throws DAOException
     */
    public void returnDamage(int id, String damage, int damageCost) throws DAOException {
        LOG.info("OrderDaoImpl.returnDamage()");
        PreparedStatement stm = null;
        PreparedStatement stm1 = null;
        try {
            connection.setAutoCommit(false);
            stm = connection.prepareStatement(UPDATE_DAMAGE_ORDER);
            stm.setInt(1, id);
            stm.executeUpdate();
            stm1 = connection.prepareStatement(UPDATE_DAMAGE_DAMAGEBILL);
            stm1.setInt(1, damageCost);
            stm1.setString(2, damage);
            stm1.setInt(3, id);
            stm1.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                throw new DAOException("DAOException while OrderDaoImpl.returnDamage().rollback()", ex1);
            }
            throw new DAOException("DAOException while OrderDaoImpl.returnDamage()", ex);
        } finally {
            closePS(stm);
            closePS(stm1);
            pool.returnConnection(connection);
        }

    }

    /**
     *
     * @return @throws DAOException
     */
    @Override
    public List getAll() throws DAOException {
        List<Order> list = new ArrayList<>();
        LOG.info("OrderDaoImpl.getALL()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_ALL_ORDERS);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setPaid(true);
                list.add(order);
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

}
