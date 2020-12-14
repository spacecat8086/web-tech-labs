package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.CarDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import by.vlados.carrentalsystem.util.EnteredInfoValidator;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados processing the request of changing the car name
 */
public class ChangeCarNameCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangeCarNameCommand.class);

    /**
     *
     * @param request
     * @return page CarInfo.js after the attempt of changing car name has been
     * made
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        String carid = (String) request.getParameter("carid");
        int id = Integer.parseInt(carid);
        String carname = (String) request.getParameter("newcarname");
        if (EnteredInfoValidator.dataLength(carname)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        CarDao carDao;
            carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        try {
            flag = carDao.changeCarname(carname, id);

            if (flag) {
                request.setAttribute("success", "1");
                request.getSession().setAttribute("carname", carname);
            } else {
                request.setAttribute("fail", "1");
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangeCarNameCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.carchange");
        return page;

    }

}
