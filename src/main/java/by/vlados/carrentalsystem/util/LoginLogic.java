package by.vlados.carrentalsystem.util;

import by.vlados.carrentalsystem.entity.Client;
import by.vlados.carrentalsystem.entity.ClientType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vlados
 * 
 * Logic for LoginCommand
 */
public class LoginLogic {

    /**
     *
     * @param clients from database
     * @param login entered login
     * @param password entered password (already hashed)
     * @param request 
     * @return result of checking login-password combination
     */
    public static int checkLogin(List<Client> clients, String login, String password, HttpServletRequest request) {
        String login2;
        String password2;
        HttpSession httpSession = request.getSession();

        if (clients != null) {
            for (Client client : clients) {
                if (client != null) {
                    login2 = client.getLogin();
                    password2 = client.getPassword();
                    if (login.equals(login2) & password.equals(password2)) {
                        if (client.getActive() == 0) {
                            return 1;
                        }
                        
                        httpSession.setAttribute("user", client);
                        httpSession.setAttribute("userType", client.getType());
                        httpSession.setAttribute("userName", client.getLogin());
                        httpSession.setAttribute("userEmail", client.getEmail());
                        httpSession.setAttribute("userId", client.getId());
                        httpSession.setAttribute("userPassNum", client.getPassNum());
                        httpSession.setAttribute("userSurname", client.getSurname());
                        httpSession.setAttribute("userRealName", client.getName());
                        httpSession.setAttribute("credit", client.getCredit());
                        
                        if (client.getType().equals(ClientType.ADMIN)) {
                            return 2;
                        } else {
                            return 3;
                        }
                    }

                }
            }
        }
        return 0;
    }

}
