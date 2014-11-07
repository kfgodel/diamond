package ar.com.kfgodel.nary.api;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

/**
 * This type represents an exception due to a Nary used as an Optional but having more than one element to return
 * Created by kfgodel on 06/11/14.
 */
public class MoreThanOneElementException extends DiamondException {

    public MoreThanOneElementException(String message) {
        super(message);
    }

    public MoreThanOneElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
