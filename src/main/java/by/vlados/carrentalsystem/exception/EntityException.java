package by.vlados.carrentalsystem.exception;

/**
 *
 * @author vlados
 * 
 * Exception which is called for while creating an Entity object
 */
public abstract class EntityException extends Exception {

    public EntityException() {
    }

    public EntityException(String msg) {
        super(msg);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityException(Throwable cause) {
        super(cause);
    }

    protected EntityException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
