package by.vlados.carrentalsystem.command.user;

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
 * deleting not wanted order
 */
public class DeleteOrderCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<Order> orders;

        String orderIdS = (String) request.getParameter("applid");
        int orderId = Integer.parseInt(orderIdS);
        OrderDao orderDao;

        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.delete(orderId);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            int id = (int) request.getSession().getAttribute("userId");
            orders = orderDao.getUByUserId(id);
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.basket");
        } catch (DAOException ex) {
            LOG.error("DAOException while PayCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        
        return page;
    }
}
