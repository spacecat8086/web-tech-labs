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
 * getting admin to page with all unpaid orders
 */
public class UnpaidOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UnpaidOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        String page;
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getUnPaidOrders();
            request.setAttribute("lst", orders);
        } catch (DAOException ex) {
            LOG.error("DAOException after applicationDaoImpl.getUnPaidApplications()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        LOG.debug("->unpaidorders");
        page = ConfigurationManager.getProperty("path.page.unpaidorders");
        return page;

    }

}
