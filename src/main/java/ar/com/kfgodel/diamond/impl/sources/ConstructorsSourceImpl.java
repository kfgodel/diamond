package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.sources.ConstructorSources;
import ar.com.kfgodel.diamond.impl.constructors.TypeConstructorInstance;
import ar.com.kfgodel.diamond.impl.constructors.description.ConstructorDescriptor;

import java.lang.reflect.Constructor;

/**
 * This type is the implementation for constructor sources
 * Created by kfgodel on 15/10/14.
 */
public class ConstructorsSourceImpl implements ConstructorSources {

    public static ConstructorsSourceImpl create() {
        ConstructorsSourceImpl source = new ConstructorsSourceImpl();
        return source;
    }

    @Override
    public TypeConstructor from(Constructor<?> nativeConstructor) {
        ConstructorDescription constructorDescription = ConstructorDescriptor.INSTANCE.describe(nativeConstructor);
        return from(constructorDescription);
    }

    @Override
    public TypeConstructor from(ConstructorDescription constructorDescription) {
        // This is the place to cache instances
        return createConstructorFrom(constructorDescription);
    }

    @Override
    public TypeConstructors in(Class<?> nativeClass) {
        return Diamond.of(nativeClass).constructors();
    }

    /**
     * Creates a new instances of a type constructor from the given description
     */
    private TypeConstructor createConstructorFrom(ConstructorDescription constructorDescription) {
        return TypeConstructorInstance.create(constructorDescription);
    }
}
