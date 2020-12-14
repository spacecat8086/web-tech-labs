package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.CarDao;
import by.vlados.carrentalsystem.dao.ClientDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.OrderDao;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import by.vlados.carrentalsystem.util.StatisticsTagHandler;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados presents main page to admin
 */
public class MainAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MainAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        /* 
        * StatisticsTagHandler methods return lists of requested information, e.g.
        * number of users or unpaid orders
        */

        request.setAttribute("rw", StatisticsTagHandler.getOrderStats().size());
        request.setAttribute("us", StatisticsTagHandler.getUserStats().size());
        request.setAttribute("car", StatisticsTagHandler.getCarsStats().size());
        page = ConfigurationManager.getProperty("path.page.admin");
        return page;

    }

}
