package by.vlados.carrentalsystem.dao.impl;

import by.vlados.carrentalsystem.connectionpool.ConnectionPool;
import by.vlados.carrentalsystem.dao.CarDao;
import by.vlados.carrentalsystem.entity.Car;
import by.vlados.carrentalsystem.exception.CarException;
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
 * DAO implementation for CarDao Interface
 */
public class CarDaoImpl implements CarDao {

    private Connection connection;
    private ConnectionPool pool;
    private static final Logger LOG = Logger.getLogger(CarDaoImpl.class);
    private static final String SELECT_ALL = "SELECT * FROM CAR;";
    private static final String SELECT_ALL_ACTIVE = "SELECT * FROM CAR WHERE CAR.`active`=1;";
    private static final String UPDATE_CAR_NAME = "UPDATE `CAR` SET CAR.`carname`=?"
            + " where CAR.`car_id`=?;";
    private static final String UPDATE_CAR_PRICE = "UPDATE `CAR` SET CAR.`price`=?"
            + " where CAR.`car_id`=?;";
    private static final String UPDATE_CAR_IMAGE = "UPDATE `CAR` SET CAR.`image`=?"
            + " where CAR.`car_id`=?;";
    private static final String SET_INACTIVE = "UPDATE `CAR` SET CAR.`active`=0"
            + " where CAR.`car_id`=?;";
    private static final String SET_ACTIVE = "UPDATE `CAR` SET CAR.`active`=1"
            + " where CAR.`car_id`=?;";
    private static final String LOOK_FOR_CAR = "SELECT * FROM CAR WHERE CAR.`car_id`=?;";
    private static final String DELETE_FROM_CAR = "DELETE FROM CAR WHERE CAR.`car_id`=?;";
    private static final String ADD_NEW_CAR = "INSERT INTO CAR(CARNAME, PRICE, IMAGE, ACTIVE) "
            + "VALUES(?, ?, ?, 0)";

    /**
     *
     * @throws by.vlados.carrentalsystem.exception.DAOException
     */
    public CarDaoImpl() throws DAOException {
        this.pool = ConnectionPool.getInstance();
        this.connection = pool.getConnection();
    }

    /**
     *
     * @return list of all cars (both active and inactive)
     * @throws DAOException
     */
    @Override
    public List<Car> getAll() throws DAOException {
        PreparedStatement stm = null;
        try {
            LOG.info("CarDaoImpl.getAll()");
            stm = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = stm.executeQuery();
            List<Car> list = new ArrayList<>();
            while (rs.next()) {
                Car car;
                car = new Car();
                car.setCarname(rs.getString("carname"));
                car.setPrice(rs.getInt("price"));
                car.setImage(rs.getString("image"));
                car.setActive(rs.getInt("active"));
                car.setId(rs.getInt("car_id"));
                list.add(car);
            }
            return list;
        } catch (SQLException | CarException ex) {
            throw new DAOException("DAOException while CarDaoImpl.getAll()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @return list of all cars visible for user
     * @throws DAOException
     */
    @Override
    public List<Car> getAllForUser() throws DAOException {
        PreparedStatement stm = null;
        try {
            LOG.info("CarDaoImpl.getAll()");
            stm = connection.prepareStatement(SELECT_ALL_ACTIVE);
            ResultSet rs = stm.executeQuery();
            List<Car> list = new ArrayList<>();
            while (rs.next()) {
                Car car;
                car = new Car();
                car.setCarname(rs.getString("carname"));
                car.setPrice(rs.getInt("price"));
                car.setImage(rs.getString("image"));
                car.setId(rs.getInt("car_id"));
                list.add(car);
            }
            return list;
        } catch (SQLException | CarException ex) {
            throw new DAOException("DAOException while CarDaoImpl.getAllForUser()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     * not yet implemented
     *
     * @param id
     * @return car by id
     * @throws DAOException
     */
    public Car read(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param carname - new carname
     * @param id - id of the car
     * @return true if carname was changed, otherwise
     * @throws DAOException
     */
    public boolean changeCarname(String carname, int id) throws DAOException {
        LOG.info("CarDaoImpl.changeCarname()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(UPDATE_CAR_NAME);
            stm.setString(1, carname);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while CarDaoImpl.changeCarname()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param carprice - new price
     * @param id - id of the car
     * @return true if car price was changed, otherwise
     * @throws DAOException
     */
    public boolean changeCarprice(int carprice, int id) throws DAOException {
        LOG.info("CarDaoImpl.changeCarprice()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(UPDATE_CAR_PRICE);
            stm.setInt(1, carprice);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while CarDaoImpl.changeCarprice", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param carimage - new path to car image
     * @param id - id of the car
     * @return true if the image was changed, otherwise
     * @throws DAOException
     */
    public boolean changeCarimage(String carimage, int id) throws DAOException {
        LOG.info("CarDaoImpl.changeCarimage()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(UPDATE_CAR_IMAGE);
            stm.setString(1, carimage);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while CarDaoImpl.changeCarimage()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param active - whether car is visible to users or not
     * @param id - id of the car
     * @return true if the state was changed
     * @throws DAOException
     */
    public boolean changeActive(int active, int id) throws DAOException {
        LOG.info("CarDaoImpl.changeActive()");
        PreparedStatement stm = null;
        try {
            if (active == 1) {
                stm = connection.prepareStatement(SET_INACTIVE);
                stm.setInt(1, id);
                stm.executeUpdate();
                return true;
            } else {
                stm = connection.prepareStatement(SET_ACTIVE);
                stm.setInt(1, id);
                stm.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new DAOException("DAOException while CarDaoImpl.changeActive()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id of the car to delete
     * @return true if this car was deleted, if it wasn't return false
     * @throws DAOException
     */
    @Override
    public boolean deleteCar(int id) throws DAOException {
        LOG.info("CarDaoImpl.deleteCar()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(LOOK_FOR_CAR);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            if (!rs.first()) {
                return false;
            } else {
                stm = connection.prepareStatement(DELETE_FROM_CAR);
                stm.setInt(1, id);
                stm.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new DAOException("DAOException while CarDaoImpl.deleteCar()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);

        }
    }

    /**
     *
     * @param car - car object made froom request
     * @return true if car was inserted into database, otherwise
     * @throws DAOException
     */
    @Override
    public boolean create(Car car) throws DAOException {
        PreparedStatement stm = null;

        try {
            LOG.info("CarDaoImpl.createCar()");
            String carname = car.getCarname();
            String image = car.getImage();
            int price = car.getPrice();
            stm = connection.prepareStatement(ADD_NEW_CAR);
            stm.setString(1, carname);
            stm.setInt(2, price);
            stm.setString(3, image);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

}
