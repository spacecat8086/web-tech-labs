package by.vlados.carrentalsystem.command.user;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import by.vlados.carrentalsystem.util.StatisticsTagHandler;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * getting user to main.jsp
 */
public class MainRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MainRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("userId");
        // StatisticsTagHandler shows how many (if any) orders users have to pay
        int size = StatisticsTagHandler.getUsersOrders(id).size();
        if (size > 0) {
            request.setAttribute("flag", "1");
            request.setAttribute("rw", size);
        }

        LOG.debug("->main.jsp");
        String page = ConfigurationManager.getProperty("path.page.main");
        return page;

    }

}
