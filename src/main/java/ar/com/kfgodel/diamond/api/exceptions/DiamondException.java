package ar.com.kfgodel.diamond.api.exceptions;

/**
 * This type represents an error in one operation with diamond reflection
 * Created by kfgodel on 18/09/14.
 */
public class DiamondException extends RuntimeException {

    public DiamondException(String message) {
        super(message);
    }

    public DiamondException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiamondException(Throwable cause) {
        super(cause);
    }
}
