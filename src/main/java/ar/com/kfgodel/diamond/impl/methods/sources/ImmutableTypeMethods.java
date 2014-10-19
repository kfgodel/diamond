package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the set of methods for a type stored as an immutable set
 * Created by kfgodel on 19/09/14.
 */
public class ImmutableTypeMethods implements TypeMethods {

    private LazyValue<List<TypeMethod>> typeMethods;

    @Override
    public Stream<TypeMethod> all() {
        return typeMethods.get().stream();
    }

    public static ImmutableTypeMethods create(Supplier<Stream<TypeMethod>> classMethods) {
        ImmutableTypeMethods methodSource = new ImmutableTypeMethods();
        // Here we cache assuming methods don't change in runtime
        methodSource.typeMethods = SuppliedValue.from(() -> classMethods.get().collect(Collectors.toList()));
        return methodSource;
    }

}
