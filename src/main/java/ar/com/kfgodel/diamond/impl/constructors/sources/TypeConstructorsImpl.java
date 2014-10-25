package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.optionals.OptionalFromStream;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.Arrays;
import java.util.Optional;
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

    @Override
    public Optional<TypeConstructor> niladic() {
        return declaredFor();
    }

    @Override
    public Optional<TypeConstructor> declaredFor(TypeInstance... paramTypes) {
        return OptionalFromStream.using(all()
                .filter((constructor)-> StreamEquality.INSTANCE.areEquals(constructor.parameterTypes(), Arrays.stream(paramTypes))));
    }

    @Override
    public TypeConstructor existingDeclaredFor(TypeInstance... paramTypes) throws DiamondException {
        return declaredFor(paramTypes).orElseGet(() -> NoConstructors.INSTANCE.existingDeclaredFor(paramTypes));
    }

    public static TypeConstructorsImpl create(Supplier<Stream<TypeConstructor>> constructorSupplier) {
        TypeConstructorsImpl methodSource = new TypeConstructorsImpl();
        methodSource.typeConstructors = constructorSupplier;
        return methodSource;
    }

}
