package by.vlados.carrentalsystem.command.user;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * defining order and adding period of rent
 */
public class DefineOrderCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DefineOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        
        String carname = (String) request.getParameter("carName");
        String carprice = (String) request.getParameter("carPrice");
        String carid = (String) request.getParameter("carId");
        String carimage = (String) request.getParameter("carImage");
        
        request.getSession().setAttribute("carId", carid);
        request.getSession().setAttribute("carName", carname);
        request.getSession().setAttribute("carImage", carimage);
        request.getSession().setAttribute("carPrice", carprice);
        
        LOG.debug("->define-order");
        page = ConfigurationManager.getProperty("path.page.define");
        return page;

    }
}
