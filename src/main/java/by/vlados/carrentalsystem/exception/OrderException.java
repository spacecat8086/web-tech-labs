package by.vlados.carrentalsystem.exception;

/**
 *
 * @author vlados
 * 
 * Exception which is called for while creating an Order object
 */
public class OrderException extends EntityException {

    public OrderException() {
    }

    public OrderException(String msg) {
        super(msg);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }

    protected OrderException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
