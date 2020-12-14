package by.vlados.carrentalsystem.command.user;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * processing user's request to get to changepassword.jsp
 */
public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.debug("->changepassword.jsp");
        String page;
        page = ConfigurationManager.getProperty("path.page.changepass");
        return page;

    }

}
