package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.sources.MethodSources;
import ar.com.kfgodel.diamond.impl.methods.TypeMethodInstance;
import ar.com.kfgodel.diamond.impl.methods.description.MethodDescriptor;

import java.lang.reflect.Method;

/**
 * This type is the non fluent implementation of the class method source
 * Created by kfgodel on 18/09/14.
 */
public class MethodSourceImpl implements MethodSources {

    private DiamondCache cache;

    public static MethodSourceImpl create(DiamondCache cache) {
        MethodSourceImpl source = new MethodSourceImpl();
        source.cache = cache;
        return source;
    }

    @Override
    public TypeMethods in(Class<?> objectClass) {
        return Diamond.of(objectClass).methods();
    }

    /**
     * Retrieves the diamond instance of the given native method instance
     * @param nativeMethod The native method instance
     * @return The diamond representation
     */
    public TypeMethod from(Method nativeMethod){
        return cache.reuseOrCreateRepresentationFor(nativeMethod, ()-> fromDescription(MethodDescriptor.INSTANCE.describe(nativeMethod)));
    }

    @Override
    public TypeMethod fromDescription(MethodDescription methodDescription) {
        return createMethodFrom(methodDescription);
    }

    /**
     * Creates a new class method instance from a its method description
     * @param methodDescription The description of the method features
     * @return The instances that represents a method
     */
    private TypeMethodInstance createMethodFrom(MethodDescription methodDescription) {
        return TypeMethodInstance.create(methodDescription);
    }

}
