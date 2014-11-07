package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.impl.named.NamedSourceSupport;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the set of methods for a type stored as an immutable set
 * Created by kfgodel on 19/09/14.
 */
public class TypeMethodsImpl extends NamedSourceSupport<TypeMethod> implements TypeMethods {

    private Supplier<Nary<TypeMethod>> typeMethods;

    @Override
    public Nary<TypeMethod> all() {
        return typeMethods.get();
    }

    @Override
    protected Stream<TypeMethod> getAll() {
        return all();
    }

    public static TypeMethodsImpl create(Supplier<Nary<TypeMethod>> classMethods) {
        TypeMethodsImpl methodSource = new TypeMethodsImpl();
        methodSource.typeMethods = classMethods;
        return methodSource;
    }


}
