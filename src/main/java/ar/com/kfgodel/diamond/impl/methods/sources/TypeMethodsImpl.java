package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the set of methods for a type stored as an immutable set
 * Created by kfgodel on 19/09/14.
 */
public class TypeMethodsImpl implements TypeMethods {

    private Supplier<Stream<TypeMethod>> typeMethods;

    @Override
    public Stream<TypeMethod> all() {
        return typeMethods.get();
    }

    public static TypeMethodsImpl create(Supplier<Stream<TypeMethod>> classMethods) {
        TypeMethodsImpl methodSource = new TypeMethodsImpl();
        methodSource.typeMethods = classMethods;
        return methodSource;
    }

}
