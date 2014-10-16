package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;

import java.lang.reflect.Constructor;

/**
 * This type represents a descriptor of native constructor to get diamond description of them
 * Created by kfgodel on 15/10/14.
 */
public class ConstructorDescriptor {

    public static final ConstructorDescriptor INSTANCE = ConstructorDescriptor.create();

    /**
     * Creates a description of the given native constructor based on its features
     * @param nativeConstructor The constructor to describe
     * @return The description for diamond
     */
    public ConstructorDescription describe(Constructor<?> nativeConstructor){
        return NativeConstructorDescription.create(nativeConstructor);
    }

    public static ConstructorDescriptor create() {
        ConstructorDescriptor descriptor = new ConstructorDescriptor();
        return descriptor;
    }

}
