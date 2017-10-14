package A00980851.util;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    AssignmentOne
 * Class  Name     ApplicationException
 * Date            2017-05-28
 */
public class ApplicationException extends Exception {

    /**
     * Default constructor
     */
    public ApplicationException() {
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
