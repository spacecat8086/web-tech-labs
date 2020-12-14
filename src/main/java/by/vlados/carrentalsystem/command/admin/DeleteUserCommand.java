package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.dao.ClientDao;
import by.vlados.carrentalsystem.dao.DaoFactory;
import by.vlados.carrentalsystem.dao.DaoType;
import by.vlados.carrentalsystem.dao.impl.ClientDaoImpl;
import by.vlados.carrentalsystem.entity.Client;
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
 *
 * deletes user / not reccomended
 */
public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt((String) request.getParameter("user_id"));
        int active = Integer.parseInt((String) request.getParameter("active"));
        ClientDao clientDao;
        List<Client> clients;
        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clientDao.changeActive(id, active);
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clients = clientDao.getAllUsers();
            request.setAttribute("lst", clients);
            page = ConfigurationManager.getProperty("path.page.allusers");
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangeActiveCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;

    }

}
