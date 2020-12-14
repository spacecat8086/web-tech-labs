package by.vlados.carrentalsystem.entity;

import by.vlados.carrentalsystem.exception.ClientException;
import java.io.Serializable;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 */
public final class Client extends Entity implements Serializable{

    private static final Logger LOG = Logger.getLogger(Client.class);

    private String login;
    private String password;
    private String name;
    private String surname;
    private String passNum;
    private String email;
    private ClientType type;
    private int active;
    private int credit;

    /**
     *
     */
    public Client() {
        super();
    }

    /**
     *
     * @param login
     * @param password
     * @param name
     * @param surname
     * @param passNum
     * @param id
     * @param type
     */
    public Client(String login, String password, String name, String surname, String passNum, int id, ClientType type) {
        super(id);
        try {
            setLogin(login);
            setPassword(password);
            setName(name);
            setSurname(surname);
            setPassNum(passNum);
            this.type = type;
        } catch (ClientException ex) {
            LOG.error("ClientException while creating Client:" + ex);
        }
    }

    /**
     *
     * @param login
     * @param password
     * @param name
     * @param surname
     * @param passNum
     * @param type
     * @param email
     * @param active
     * @param credit
     */
    public Client(String login, String password, String name, String surname, String passNum, String type, String email, int active, int credit) {
        super();
        try {
            setLogin(login);
            setPassword(password);
            setName(name);
            setSurname(surname);
            setPassNum(passNum);
            setEmail(email);
            setType(type);
            setActive(active);
            setCredit(credit);
        } catch (ClientException ex) {
            LOG.error("ClientException while creating Client:" + ex);
        }
    }

    /**
     *
     * @return
     */
    public int getCredit() {
        return credit;
    }

    /**
     *
     * @param credit
     */
    public void setCredit(int credit) {
        if(credit>=0){
            this.credit = credit;
        } else {
            credit = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(int active) {
        if (active == 1) {
            this.active = active;
        } else {
            this.active = 0;
        }
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * @throws ClientException
     */
    public void setEmail(String email) throws ClientException {
        if (!email.isEmpty()) {
            this.email = email;
        } else {
            throw new ClientException("Email can't be null");
        }
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * @throws ClientException
     */
    public void setName(String name) throws ClientException {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            throw new ClientException("Name can't be null");
        }
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     * @throws ClientException
     */
    public void setSurname(String surname) throws ClientException {
        if (!surname.isEmpty()) {
            this.surname = surname;
        } else {
            throw new ClientException("Surname can't be null");
        }
    }

    /**
     *
     * @return
     */
    public String getPassNum() {
        return passNum;
    }

    /**
     *
     * @param passNum
     * @throws ClientException
     */
    public void setPassNum(String passNum) throws ClientException {
        if (!passNum.isEmpty()) {
            this.passNum = passNum;
        } else {
            throw new ClientException("Passport number can't be null");
        }
    }

    /**
     *
     * @return
     */
    public ClientType getType() {
        return type;
    }

    /**
     *
     * @param s
     */
    public void setType(String s) {
        if (s.toUpperCase().equals("ADMIN")) {
            this.type = ClientType.ADMIN;
        } else {
            this.type = ClientType.USER;
        }
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * @throws ClientException
     */
    public void setLogin(String login) throws ClientException {
        if (!login.isEmpty()) {
            this.login = login;
        } else {
            throw new ClientException("Login can't be null");
        }
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * @throws ClientException
     */
    public void setPassword(String password) throws ClientException {

        if (!password.isEmpty()) {
            this.password = password;
        } else {
            throw new ClientException("Login can't be null");
        }

    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.surname);
        hash = 67 * hash + Objects.hashCode(this.passNum);
        hash = 67 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.passNum, other.passNum)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Client{" + "login=" + login + ", password=" + password + ","
                + " name=" + name + ", surname=" + surname + ", passport number="
                + passNum + ", e-mail=" + email + ", type=" + type + '}';
    }

}
