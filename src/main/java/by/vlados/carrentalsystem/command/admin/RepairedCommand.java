package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.RepairBillDao;
import by.vlados.carrentalsystem.entity.RepairBill;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * marking the chosen repaibill as paid and getting admin to the page with the
 * rest of unpaid bills
 */
public class RepairedCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RepairedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        RepairBillDao billDao;
        String order = (String) request.getParameter("applId");
        int ordeId = Integer.parseInt(order);
        List<RepairBill> bills;

        try {
            billDao = (RepairBillDao) DaoFactory.getDao(DaoType.REPAIR_BILL);
            flag = billDao.repair(ordeId);
            
            billDao = (RepairBillDao) DaoFactory.getDao(DaoType.REPAIR_BILL);
            bills = billDao.getAll();
            request.setAttribute("lst", bills);
        } catch (DAOException ex) {
            LOG.error("DAOException while repairedComand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
        } else {
            request.setAttribute("cfail", "1");
        }
        page = ConfigurationManager.getProperty("path.page.repairbills");
        return page;

    }

}
