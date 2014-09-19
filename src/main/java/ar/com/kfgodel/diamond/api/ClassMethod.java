package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.methods.MethodSignature;
import ar.com.kfgodel.diamond.api.naming.Named;
import sun.reflect.generics.tree.MethodTypeSignature;

/**
 * This type represents a method of a class.<br> Similar to Method
 * Created by kfgodel on 18/09/14.
 */
public interface ClassMethod extends Named {

    /**
     * @return The name of the method
     */
    @Override
    String name();

    /**
     * @return The signature of this method
     */
    MethodSignature signature();
}
