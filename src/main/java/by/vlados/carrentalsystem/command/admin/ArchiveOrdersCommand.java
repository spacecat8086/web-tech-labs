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
 * @author vlados sends admin to page with all archived orders
 */
public class ArchiveOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ArchiveOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        String page;
        orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
        List<Order> orders;
        
        try {
            orders = orderDao.getArchiveOrders();
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.archiveorders");
        } catch (DAOException ex) {
            LOG.error("DAOException while applicationDaoImpl.getAll()." + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        
        LOG.debug("-> archiveorders.jsp");
        return page;

    }

}
