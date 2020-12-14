package by.vlados.carrentalsystem.command.admin;

import by.vlados.carrentalsystem.command.ActionCommand;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * sends admin to cars.jsp
 */
public class CarsAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CarsAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.debug("->cars");
        String page = ConfigurationManager.getProperty("path.page.carsadmin");
        return page;

    }

}
