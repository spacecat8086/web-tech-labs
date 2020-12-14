package by.vlados.carrentalsystem.command;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vlados
 * Interface for all commands
 */
public interface ActionCommand {

    /**
     *
     * @param request from jsp
     * @return page address
     */
    String execute(HttpServletRequest request);

}
