package by.vlados.carrentalsystem.command.user;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.ClientDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import by.vlados.carrentalsystem.util.EnteredInfoValidator;
import by.vlados.carrentalsystem.util.PasswordHashing;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * processing request to change user's password
 */
public class ChangePasswordConfCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangePasswordConfCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        int id = (int) request.getSession().getAttribute("userId");
        String password = (String) request.getParameter("pass");
        String newpassword = (String) request.getParameter("newpass");
        if (EnteredInfoValidator.passwordVal(newpassword)) {
            request.setAttribute("cpError", "1");
            page = ConfigurationManager.getProperty("path.page.changepass");
            return page;
        }
        if (EnteredInfoValidator.passwordValSame(newpassword, password)) {
            request.setAttribute("cpSame", "1");
            page = ConfigurationManager.getProperty("path.page.changepass");
            return page;
        }
        password = PasswordHashing.getHashValue(password);
        newpassword = PasswordHashing.getHashValue(newpassword);
        ClientDao clientDao;

        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            flag = clientDao.changePassword(id, password, newpassword);
            if (flag) {
                request.setAttribute("cpSuccess", "1");
                page = ConfigurationManager.getProperty("path.page.changepass");
                return page;
            } else {
                request.setAttribute("cpError", "1");
                page = ConfigurationManager.getProperty("path.page.changepass");
                return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangePasswordConfCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }

    }
}
