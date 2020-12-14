package by.vlados.carrentalsystem.dao;

import by.vlados.carrentalsystem.dao.impl.CarDaoImpl;
import by.vlados.carrentalsystem.dao.impl.ClientDaoImpl;
import by.vlados.carrentalsystem.dao.impl.OrderDaoImpl;
import by.vlados.carrentalsystem.dao.impl.RepairBillDaoImpl;
import by.vlados.carrentalsystem.exception.DAOException;

/**
 *
 * @author vlados
 *
 * Small factory for getting a needed type of dao
 */
public class DaoFactory {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DaoFactory.class);
    private static IDao iDao = null;

    public static IDao getDao(DaoType daoType) {
        switch (daoType) {
            case ORDER:
                try {
                    iDao = new OrderDaoImpl();
                } catch (DAOException ex) {
                    LOG.fatal("Couldn't establish the connection to the database" + ex);
                }
                return iDao;
            case CLIENT:
                try {
                    iDao = new ClientDaoImpl();
                } catch (DAOException ex) {
                    LOG.fatal("Couldn't establish the connection to the database" + ex);
                }
                return iDao;
            case CAR:
                try {
                    iDao = new CarDaoImpl();
                } catch (DAOException ex) {
                    LOG.fatal("Couldn't establish the connection to the database" + ex);
                }
                return iDao;
            case REPAIR_BILL:
                try {
                    iDao = new RepairBillDaoImpl();
                } catch (DAOException ex) {
                    LOG.fatal("Couldn't establish the connection to the database" + ex);
                }
                return iDao;
            default:
                return null;

        }

    }

}
