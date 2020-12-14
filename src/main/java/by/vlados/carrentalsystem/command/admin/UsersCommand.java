package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.ClientDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.impl.ClientDaoImpl;
import by.vlados.carrentalsystem.entity.Client;
import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * gets admin to the page with the list of all users
 */
public class UsersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ClientDao clientDao;
        String page;
        List<Client> clients;
        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clients = clientDao.getAllUsers();
            request.setAttribute("lst", clients);
            page = ConfigurationManager.getProperty("path.page.allusers");
        } catch (DAOException ex) {
            LOG.error("DAOException while clientDao.getAll()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
