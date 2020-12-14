package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.CarDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.entity.Car;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados 
 * 
 * returns page with all (active and inactive) cars for admin
 */
public class AllCarsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AllCarsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        CarDao carDao;
        List<Car> cars;
        carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        try {
            cars = carDao.getAll();
            request.setAttribute("lst", cars);
            page = ConfigurationManager.getProperty("path.page.allcars");
        } catch (DAOException ex) {
            LOG.error("DAOException while carDao.getAll()." + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
