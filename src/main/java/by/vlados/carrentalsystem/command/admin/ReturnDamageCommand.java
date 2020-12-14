package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.OrderDao;
import by.vlados.carrentalsystem.entity.Order;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import by.vlados.carrentalsystem.util.EnteredInfoValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * marking the order as returned with any damage and creating a damagebill
 */
public class ReturnDamageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnDamageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;

        String order = (String) request.getParameter("applId");
        String damage = (String) request.getParameter("damage");
        if (EnteredInfoValidator.dataLength(damage)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        String damageCost = (String) request.getParameter("damagecost");
        int applId = Integer.parseInt(order);
        int dCost = Integer.parseInt(damageCost);
        if (!EnteredInfoValidator.rentPrice(dCost)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.returnDamage(applId, damage, dCost);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getPaidOrders();
            request.setAttribute("lst", orders);
        } catch (DAOException ex) {
            LOG.error("DAOException while confirmComand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.paidorders");
        return page;

    }

}
