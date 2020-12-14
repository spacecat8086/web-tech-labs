package by.vlados.carrentalsystem.filter;

import by.vlados.carrentalsystem.entity.ClientType;
import by.vlados.carrentalsystem.util.ConfigurationManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * 
 * filter for ensuring no unsanctioned access will be granted to guests who are
 * not authorised in the system
 */
public class ServletSecurityFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(ServletSecurityFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        ClientType type = (ClientType) session.getAttribute("userType");
        String type2 = (String) req.getParameter("userType");
        if (type == null & type2 == null) {
            type = ClientType.GUEST;
            session.setAttribute("userType", type);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"));
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
