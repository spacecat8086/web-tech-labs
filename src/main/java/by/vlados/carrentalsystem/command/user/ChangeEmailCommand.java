package by.vlados.carrentalsystem.command.user;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.ClientDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import by.vlados.carrentalsystem.util.EnteredInfoValidator;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * processing request to change user's email
 */
public class ChangeEmailCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangeEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        int id = (int) request.getSession().getAttribute("userId");
        String oldEmail = (String) request.getSession().getAttribute("userEmail");
        String email = (String) request.getParameter("newemail");
        if (EnteredInfoValidator.emailVal(email)) {
            request.setAttribute("cpError", "1");
            page = ConfigurationManager.getProperty("path.page.account");
            return page;
        }
        
        if (EnteredInfoValidator.emailValSame(email, oldEmail)) {
            request.setAttribute("cpSame", "1");
            page = ConfigurationManager.getProperty("path.page.account");
            return page;
        }

        ClientDao clientDao;

        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            flag = clientDao.changeEmail(id, email);
            if (flag) {
                request.setAttribute("cpSuccess", "1");
                request.getSession().setAttribute("userEmail", email);
                page = ConfigurationManager.getProperty("path.page.account");
                return page;
            } else {
                request.setAttribute("cpError", "1");
                page = ConfigurationManager.getProperty("path.page.account");
                return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangePasswordConfCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }

    }
}
