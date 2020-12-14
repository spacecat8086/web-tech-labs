package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.impl.OrderDaoImpl;
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
 * denies user's order
 */
public class DenyCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DenyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        OrderDaoImpl orderDao;
        String appl = (String) request.getParameter("applId");
        String reason = (String) request.getParameter("reasonForRefusal");
        if (EnteredInfoValidator.dataLength(reason)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> orders;

        int applId = Integer.parseInt(appl);
        try {
            orderDao = (OrderDaoImpl) DaoFactory.getDao(DaoType.ORDER);
            flag = orderDao.deny(applId, reason);
            
            orderDao = (OrderDaoImpl) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getNewOrders();
            request.setAttribute("lst", orders);

        } catch (DAOException ex) {
            LOG.error("DAOException in denyComand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        if (flag) {
            request.setAttribute("cfail", "1");
        }
        page = ConfigurationManager.getProperty("path.page.neworders");
        return page;
    }

}
