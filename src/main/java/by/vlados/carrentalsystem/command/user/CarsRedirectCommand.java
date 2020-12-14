package by.vlados.carrentalsystem.command.user;

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
 * processing request to get to cars.jsp with list of all available to user cars
 */
public class CarsRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CarsRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        CarDao carDao;
        List<Car> cars;

        try {
            carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
            cars = carDao.getAllForUser();
        } catch (DAOException ex) {
            LOG.error("DAOException while carDao.getAll()." + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        
        request.setAttribute("lst", cars);
        LOG.debug("->cars");
        page = ConfigurationManager.getProperty("path.page.cars");
        return page;

    }

}
