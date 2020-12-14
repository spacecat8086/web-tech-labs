package by.vlados.carrentalsystem.command.user;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * 
 * getting user to contacts page
 */
public class ContactsRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ContactsRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.debug("->contacts");
        page = ConfigurationManager.getProperty("path.page.contacts");
        return page;

    }

}