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
 * showing user their basket with confirmed orders waiting to be paid for
 */
public class BasketCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(BasketCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            int id = (int) request.getSession().getAttribute("userId");
            orders = orderDao.getUByUserId(id);
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.basket");
        } catch (DAOException ex) {
            LOG.error("DaoException while getUByUserId()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        
        LOG.debug("->basket");
        return page;
    }
}
