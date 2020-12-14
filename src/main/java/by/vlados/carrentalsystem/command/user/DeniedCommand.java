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
 * showing user the list of denied orders (with reasons, why)
 */
public class DeniedCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeniedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;
        List<Order> orderDenied;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            int id = (int) request.getSession().getAttribute("userId");
            orderDenied = orderDao.getDByUserId(id);
            request.setAttribute("lstR", orderDenied);
            page = ConfigurationManager.getProperty("path.page.ordersdenied");
        } catch (DAOException ex) {
            LOG.error("DAOException while getDByUserId(id)" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
