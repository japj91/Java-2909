package A00980851.io;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab3
 * Class  Name     ApplicationException
 * Date            2017-05-10
 * Class to handle exceptions
 */
public class ApplicationException extends Exception {

    /**
     * Default constructor
     */
    public ApplicationException() {
        super();
    }

    /**
     *  takes a string sends to super
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * takes a string sends to super and throwable
     * @param message
     * @param cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * takes a cause and sends to super
     * @param cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * takes message casue enableSUppression and wireableStackTrace
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
