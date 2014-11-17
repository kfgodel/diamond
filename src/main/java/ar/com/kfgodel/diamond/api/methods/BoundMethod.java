package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

/**
 * This type represents a TypeMethod bound to a specific instance as the "this" reference
 * Created by kfgodel on 16/11/14.
 */
public interface BoundMethod extends PolymorphicInvokable {


    /**
     * @return The type method this instance binds
     */
    TypeMethod typeMethod();

    /**
     * @return The object to which the method is bound to
     */
    Object instance();
}
