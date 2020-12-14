package by.vlados.carrentalsystem.command.auth;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.ClientDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.entity.Client;
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
 * registering user in the system and returning him to login.jsp
 */
public class RegisterCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        String login = request.getParameter("login");
        String realname = request.getParameter("realname");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        if (!password.equals(passwordRepeat)) {
            request.setAttribute("errorPassword", "1");
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
        }
        String surname = request.getParameter("surname");
        String passport = request.getParameter("pass_num");
        String email = request.getParameter("email");
        String type = "USER";
        /* additional validation in case validation on jsp page doesn't work*/
        if (EnteredInfoValidator.validateRegistrationInfo(login, email, passport, password)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        } 
        int active = 1; //1 means active, 0 means inactive
        int credit = 1000; // standart sum, placeholder for the real billing
        password = PasswordHashing.getHashValue(password);
        ClientDao clientDao;
        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            boolean flag = clientDao.checkLogin(login);
            if (flag) {
                request.setAttribute("errorLogin", 1);
                page = ConfigurationManager.getProperty("path.page.register");
                return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while checkLogin()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }

        Client user = new Client(login, password, realname, surname, passport, type, email, active, credit);
        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clientDao.create(user);
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        } catch (DAOException ex) {
            LOG.error("Couldn't add the user to the database" + ex);
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
        }
    }
}
