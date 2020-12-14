package by.vlados.carrentalsystem.dao;

import by.vlados.carrentalsystem.entity.Car;
import by.vlados.carrentalsystem.exception.DAOException;
import java.util.List;

/**
 *
 * @author vlados
 * @param <T>
 */
public interface CarDao extends IDao {

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
   Car read(int id) throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    @Override
    List<Car> getAll() throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    List<Car> getAllForUser() throws DAOException;

    /**
     *
     * @param carname
     * @param id
     * @return
     * @throws DAOException
     */
    boolean changeCarname(String carname, int id) throws DAOException;

    /**
     *
     * @param carprice
     * @param id
     * @return
     * @throws DAOException
     */
    boolean changeCarprice(int carprice, int id) throws DAOException;

    /**
     *
     * @param carimage
     * @param id
     * @return
     * @throws DAOException
     */
    boolean changeCarimage(String carimage, int id) throws DAOException;

    /**
     *
     * @param active
     * @param id
     * @return
     * @throws DAOException
     */
    abstract public boolean changeActive(int active, int id) throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
   boolean deleteCar(int id) throws DAOException;

    /**
     *
     * @param car
     * @return
     * @throws DAOException
     */
    boolean create(Car car) throws DAOException;

}
