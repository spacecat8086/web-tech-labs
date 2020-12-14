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
 * getting admin to page with all paid orders
 */
public class PaidOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PaidOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        String page;
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getPaidOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getPaidApplications()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lst", orders);
        LOG.debug("->paidorders");
        page = ConfigurationManager.getProperty("path.page.paidorders");
        return page;

    }

}
