package by.vlados.carrentalsystem.command;

import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vlados
 * in case the command wasn't specified
 */
public class EmptyCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.error");
        request.getSession().invalidate();
        return page;
    }
    
}
