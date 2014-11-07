package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.executables.FilterByParameterType;
import ar.com.kfgodel.nary.api.Nary;

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
    public Nary<TypeConstructor> niladic() {
        return withParameters();
    }

    @Override
    public Nary<TypeConstructor> withParameters(TypeInstance... paramTypes) {
        return FilterByParameterType.create(all(), paramTypes);
    }

    public static TypeConstructorsImpl create(Supplier<Nary<TypeConstructor>> constructorSupplier) {
        TypeConstructorsImpl methodSource = new TypeConstructorsImpl();
        methodSource.typeConstructors = constructorSupplier;
        return methodSource;
    }

}
