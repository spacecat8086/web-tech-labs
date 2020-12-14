package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.CarDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.impl.CarDaoImpl;
import by.vlados.carrentalsystem.entity.Car;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados returns page after trying to add new car to the database
 */
public class NewCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(NewCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        CarDao carDao;
        carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        boolean flag;
        try {
            String carname = (String) request.getParameter("carname");
            int price = Integer.parseInt((String) request.getParameter("price"));
            String carimage = (String) request.getParameter("image");
            carimage = "img/car/".concat(carimage).concat(".jpg");
            Car car = new Car(carname, price, carimage, 1);
            flag = carDao.create(car);
        } catch (DAOException ex) {
            LOG.error("DAOException while CarDao.create()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
        } else {
            request.setAttribute("cfail", "1");
        }
        page = ConfigurationManager.getProperty("path.page.addnewcar");
        return page;

    }

}
