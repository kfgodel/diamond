package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.optionals.OptionalFromStream;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a fixed set of constructors for a type
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorsImpl implements TypeConstructors {

    private Supplier<Nary<TypeConstructor>> typeConstructors;

    @Override
    public Nary<TypeConstructor> all() {
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

    public static TypeConstructorsImpl create(Supplier<Nary<TypeConstructor>> constructorSupplier) {
        TypeConstructorsImpl methodSource = new TypeConstructorsImpl();
        methodSource.typeConstructors = constructorSupplier;
        return methodSource;
    }

}
