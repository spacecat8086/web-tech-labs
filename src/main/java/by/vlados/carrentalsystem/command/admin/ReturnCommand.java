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
 * marking the order as returned without any damage
 */
public class ReturnCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;
        String order = (String) request.getParameter("applId");
        int orderId = Integer.parseInt(order);
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.returnCar(orderId);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getPaidOrders();
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.paidorders");
        } catch (DAOException ex) {
            LOG.error("DaoException while ReturnCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        LOG.debug("->paidorders.jsp");
        return page;

    }

}
