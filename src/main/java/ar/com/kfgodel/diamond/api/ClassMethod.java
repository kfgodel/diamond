package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.naming.Named;

/**
 * This type represents a method of a class.<br> Similar to Method
 * Created by kfgodel on 18/09/14.
 */
public interface ClassMethod extends Named {

    /**
     * @return The method name selector
     */
    @Override
    String name();

}
