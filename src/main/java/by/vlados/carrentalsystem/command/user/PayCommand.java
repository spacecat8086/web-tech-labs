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
 * user paying for the order
 */
public class PayCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PayCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        List<Order> orders;

        String idOrderS = (String) request.getParameter("applid");
        String idUserS = (String) request.getParameter("userId");
        int idUser = Integer.parseInt(idUserS);
        int idOrder = Integer.parseInt(idOrderS);
        String sumO = (String) request.getParameter("sumToPay");
        int sum = Integer.parseInt(sumO);
        OrderDao orderDao;

        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            flag = orderDao.pay(idUser, idOrder, sum);
            if (flag) {
                request.setAttribute("success", "1");
            } else {
                request.setAttribute("fail", "1");
            }
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getUByUserId(idUser);
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.basket");
        } catch (DAOException ex) {
            LOG.error("DAOException while PayCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
