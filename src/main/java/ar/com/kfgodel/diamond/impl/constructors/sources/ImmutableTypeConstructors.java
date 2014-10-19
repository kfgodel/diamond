package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a fixed set of constructors for a type
 * Created by kfgodel on 15/10/14.
 */
public class ImmutableTypeConstructors implements TypeConstructors {

    private LazyValue<List<TypeConstructor>> typeConstructors;

    @Override
    public Stream<TypeConstructor> all() {
        return typeConstructors.get().stream();
    }

    public static ImmutableTypeConstructors create(Supplier<Stream<TypeConstructor>> constructorSupplier) {
        ImmutableTypeConstructors methodSource = new ImmutableTypeConstructors();
        // Here we cache assuming methods don't change in runtime
        methodSource.typeConstructors = SuppliedValue.from(() -> constructorSupplier.get().collect(Collectors.toList()));
        return methodSource;
    }

}
