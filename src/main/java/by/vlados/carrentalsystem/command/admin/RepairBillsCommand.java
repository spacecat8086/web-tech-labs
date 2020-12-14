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
 * gets admin to page with all unpaid repair bills
 */
public class RepairBillsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RepairBillsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        RepairBillDao billDao;
        List<RepairBill> bills;
        try {
            billDao = (RepairBillDao) DaoFactory.getDao(DaoType.REPAIR_BILL);
            bills = billDao.getAll();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getRepairBills()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lst", bills);
        LOG.debug("->repairbills");
        page = ConfigurationManager.getProperty("path.page.repairbills");
        return page;
    }

}
