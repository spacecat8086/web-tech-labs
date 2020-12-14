package by.vlados.carrentalsystem.util;

import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 *
 * server-side validation of information, which users enter into the fields
 */
public class EnteredInfoValidator {

    private static final Logger LOG = Logger.getLogger(EnteredInfoValidator.class);

    public static final String LOGIN_REGEX = "^[a-z0-9]{3,16}$";
    public static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String PASSWORD_REGEX = "^[a-z0-9_-]{6,16}$";
    public static final String PASSNUM_REGEX = "^[A-Z0-9]{7,14}$";
    public static final int MIN_ID = 1;
    public static final int MAX_ID = 10000;
    public static final int MIN_RENT = 1;
    public static final int MAX_RENT = 10000;
    public static final int MAX_DATA_LENGTH = 200;
    public static final int MIN_PRICE = 10;
    public static final int MAX_PRICE = 100000;
    public static final int MIN_PERIOD = 1;
    public static final int MAX_PERIOD = 30;

    /**
     *
     * @param login
     * @param email
     * @param passNum
     * @param password
     * @return true if there are problems with entered info
     */
    public static boolean validateRegistrationInfo(String login, String email, String passNum, String password) {
        LOG.debug("total val: " + (loginVal(login) | passwordVal(password) | passNumVal(passNum)));
        return loginVal(login) | passwordVal(password) | passNumVal(passNum);
    }

    /**
     *
     * @param login
     * @param password
     * @return true if there are problems with entered info
     */
    public static boolean validateLoginInfo(String login, String password) {
        return loginValE(login) || passwordValE(password);
    }

    /**
     *
     * @param login
     * @return true if login doesn't match the pattern
     */
    public static boolean loginVal(String login) {
        LOG.debug("loginVal" + !Pattern.matches(LOGIN_REGEX, login));
        return !Pattern.matches(LOGIN_REGEX, login);
    }

    /**
     *
     * @param login
     * @return true if login is empty
     */
    public static boolean loginValE(String login) {
        return !login.isEmpty();
    }

    /**
     *
     * @param email
     * @return
     */
    public static boolean emailVal(String email) {
        LOG.debug("emailVal" + !Pattern.matches(EMAIL_REGEX, email));
        return !Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     *
     * @param email
     * @param oldEmail
     * @return true if emails are the same
     */
    public static boolean emailValSame(String email, String oldEmail) {
        return email.equals(oldEmail);
    }

    /**
     *
     * @param password
     * @return password doesn't match the pattern
     */
    public static boolean passwordVal(String password) {
        LOG.debug("passwordVal" + !Pattern.matches(PASSWORD_REGEX, password));
        return !Pattern.matches(PASSWORD_REGEX, password);
    }

    /**
     *
     * @param password
     * @param oldPassword
     * @return true if passwords match
     */
    public static boolean passwordValSame(String password, String oldPassword) {
        return password.equals(oldPassword);
    }

    /**
     *
     * @param password
     * @return true if password is empty
     */
    public static boolean passwordValE(String password) {
        return !password.isEmpty();
    }

    /**
     *
     * @param passNum
     * @return true if passNum doesn't match the regular expression
     */
    public static boolean passNumVal(String passNum) {
        LOG.debug("passNumVal" + !Pattern.matches(PASSNUM_REGEX, passNum));
        return !Pattern.matches(PASSNUM_REGEX, passNum);
    }

    /**
     *
     * @param userId
     * @return true if userIdVal is outside acceptable range
     */
    public static boolean userIdVal(int userId) {
        return (userId > MIN_ID) || (userId < MAX_ID);
    }

    /**
     *
     * @param text
     * @return true if text length is outside acceptable range
     */
    public static boolean dataLength(String text) {
        return (text.length() > MAX_DATA_LENGTH) || (text.isEmpty());
    }

    /**
     *
     * @param price
     * @return true if price is outside acceptable range
     */
    public static boolean priceVal(int price) {
        return (price > MIN_PRICE) || (price < MAX_PRICE);
    }

    /**
     *
     * @param period
     * @return true if period is outside acceptable range
     */
    public static boolean periodVal(int period) {
        return (period >= MIN_PERIOD) || (period <= MAX_PERIOD);
    }

    /**
     *
     * @param price
     * @return true if rent price is outside acceptable range
     */
    public static boolean rentPrice(int price) {
        return (price >= MIN_RENT) || (price <= MAX_RENT);
    }
}
