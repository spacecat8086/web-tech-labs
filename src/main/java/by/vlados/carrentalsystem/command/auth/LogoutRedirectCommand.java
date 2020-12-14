package by.vlados.carrentalsystem.command.auth;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * getting user to logout.jsp
 */
public class LogoutRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LogoutRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.debug("->logout.jsp");
        page = ConfigurationManager.getProperty("path.page.logout");
        return page;

    }

}
