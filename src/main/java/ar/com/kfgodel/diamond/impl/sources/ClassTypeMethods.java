package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.sources.TypeMethods;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the class searcher for methods
 * Created by kfgodel on 19/09/14.
 */
public class ClassTypeMethods implements TypeMethods {

    private LazyValue<List<ClassMethod>> typeMethods;

    @Override
    public Stream<ClassMethod> all() {
        return typeMethods.get().stream();
    }

    public static ClassTypeMethods create(Supplier<Stream<ClassMethod>> classMethods) {
        ClassTypeMethods methodSource = new ClassTypeMethods();
        // Here we cache assuming methods don't change in runtime
        methodSource.typeMethods = SuppliedValue.create(()-> classMethods.get().collect(Collectors.toList()));
        return methodSource;
    }

}
