
package by.vlados.carrentalsystem.dao;

import by.vlados.carrentalsystem.entity.RepairBill;
import by.vlados.carrentalsystem.exception.DAOException;
import java.util.List;

/**
 *
 * @author vlados
 */
public interface RepairBillDao extends IDao{
    
    /**
     *
     * @return
     * @throws DAOException
     */
    @Override
    List<RepairBill> getAll() throws DAOException;
    
    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean repair(int id) throws DAOException;
}
