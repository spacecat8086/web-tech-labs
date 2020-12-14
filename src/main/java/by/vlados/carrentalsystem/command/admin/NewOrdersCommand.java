package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.OrderDao;
import by.vlados.carrentalsystem.entity.Order;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados 
 * 
 * getting admin to the page with all new applications
 */
public class NewOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(NewOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao applDao;
        String page;
        List<Order> orders;
        try {
            applDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = applDao.getNewOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getNewApplications()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lst", orders);
        LOG.debug("->neworders");
        page = ConfigurationManager.getProperty("path.page.neworders");
        return page;

    }

}
