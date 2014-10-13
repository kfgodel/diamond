package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.methods.ClassMethod;
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

    private LazyValue<List<ClassMethod>> typeMethods;

    @Override
    public Stream<ClassMethod> all() {
        return typeMethods.get().stream();
    }

    public static ImmutableTypeMethods create(Supplier<Stream<ClassMethod>> classMethods) {
        ImmutableTypeMethods methodSource = new ImmutableTypeMethods();
        // Here we cache assuming methods don't change in runtime
        methodSource.typeMethods = SuppliedValue.create(()-> classMethods.get().collect(Collectors.toList()));
        return methodSource;
    }

}
