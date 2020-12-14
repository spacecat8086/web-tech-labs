package by.vlados.carrentalsystem.exception;

/**
 *
 * @author vlados
 * 
 * Exception which is called for while creating a Client object
 */
public class ClientException extends EntityException {

    public ClientException() {
    }

    public ClientException(String msg) {
        super(msg);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    protected ClientException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
