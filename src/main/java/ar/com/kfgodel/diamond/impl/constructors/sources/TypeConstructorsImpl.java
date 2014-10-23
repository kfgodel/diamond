package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a fixed set of constructors for a type
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorsImpl implements TypeConstructors {

    private Supplier<Stream<TypeConstructor>> typeConstructors;

    @Override
    public Stream<TypeConstructor> all() {
        return typeConstructors.get();
    }

    public static TypeConstructorsImpl create(Supplier<Stream<TypeConstructor>> constructorSupplier) {
        TypeConstructorsImpl methodSource = new TypeConstructorsImpl();
        methodSource.typeConstructors = constructorSupplier;
        return methodSource;
    }

}
