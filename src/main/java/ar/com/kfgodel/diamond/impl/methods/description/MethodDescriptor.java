package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.methods.MethodDescription;

import java.lang.reflect.Method;

/**
 * This type represents a method descriptor that can transform native methods into
 * diamond method descriptions
 * Created by kfgodel on 07/10/14.
 */
public class MethodDescriptor {

    public static final MethodDescriptor INSTANCE = MethodDescriptor.create();

    /**
     * Creates a description of the given native method based on its features
     * @param nativeMethod The method to describe
     * @return The description for diamond
     */
    public MethodDescription describe(Method nativeMethod){
        return NativeMethodDescription.create(nativeMethod);
    }

    public static MethodDescriptor create() {
        MethodDescriptor descriptor = new MethodDescriptor();
        return descriptor;
    }

}
