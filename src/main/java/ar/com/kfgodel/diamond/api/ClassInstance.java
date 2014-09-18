package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.naming.Named;

/**
 * This type represents a class
 * Created by kfgodel on 17/09/14.
 */
public interface ClassInstance extends Named {
    /**
     * The name of the class without a package prefix
     * @return THe name that identifies this class
     */
    public String getName();
}
