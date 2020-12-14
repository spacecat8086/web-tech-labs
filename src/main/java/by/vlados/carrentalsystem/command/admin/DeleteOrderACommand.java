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
 * deletes unpaid orders which admin chose
 */
public class DeleteOrderACommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteOrderACommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String idA = (String) request.getParameter("applId");
        int id = Integer.parseInt(idA);
        OrderDao orderDao;
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.delete(id);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getUnPaidOrders();
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.unpaidorders");
            return page;
        } catch (DAOException ex) {
            LOG.error("DAOException while DeleteOrderCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }

    }
}
