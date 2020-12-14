package by.vlados.carrentalsystem.command;

import by.vlados.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * Factory for all commands
 */
public class ActionFactory {

    /**
     * log4j logger
     */
    public static final Logger LOG = Logger.getLogger(ActionFactory.class.getName());

    /**
     *
     * @param request from jsp
     * @return choosen command
     */
    public ActionCommand defineCommand(HttpServletRequest request){
       ActionCommand current = new EmptyCommand();
       String action = request.getParameter("command");
       if(action == null || action.isEmpty()){
           return current;
       }
       try {
           CommandEnum currentEnum =CommandEnum.valueOf(action.toUpperCase());
           current = currentEnum.getCurrentCommand();
           LOG.debug("User has chosen command:" + currentEnum.name());
       } catch (IllegalArgumentException ex) {
           LOG.error("IllegalArgumentException while ActionFactory.defineCommand()" + ex);
          request.setAttribute("wrongAtion", action + ConfigurationManager.getProperty("message.wrongaction"));
       }
       return current;
    }
    
}
