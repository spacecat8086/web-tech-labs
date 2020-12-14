package by.vlados.carrentalsystem.command;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * Commands which swithes user between Russian and English interfaces
 */
public class LanguageCommand implements ActionCommand {
    private final static Logger LOG = Logger.getLogger(LanguageCommand.class);

    /**
     * default constructor
     */
    public LanguageCommand() {
    }

    /**
     *
     * @param request
     * @return login.jsp in a selected language
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String command = request.getParameter("language");
        Locale locale = null;
        switch (command.toUpperCase()) {
            case "EN":
                locale = new Locale("en");
                break;
            case "RU":
                locale = new Locale("ru");
                break;
        }
        session.setAttribute("locale", locale);
        String  page = null;
        if (null == page) {
            page = request.getQueryString();
        }
        return page;
    }

}
