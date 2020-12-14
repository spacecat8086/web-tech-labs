package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * 
 * gets admin to page where they can manage users
 */
public class UsersWCommand implements ActionCommand{
    private static final Logger LOG = Logger.getLogger(UsersWCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.debug("->usersW");
        String page = ConfigurationManager.getProperty("path.page.users");
        return page;
    
    }
    
}
