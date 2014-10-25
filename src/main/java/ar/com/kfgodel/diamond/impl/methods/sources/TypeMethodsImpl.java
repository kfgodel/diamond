package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.impl.named.NamedSourceSupport;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the set of methods for a type stored as an immutable set
 * Created by kfgodel on 19/09/14.
 */
public class TypeMethodsImpl extends NamedSourceSupport<TypeMethod> implements TypeMethods {

    private Supplier<Stream<TypeMethod>> typeMethods;

    @Override
    public Stream<TypeMethod> all() {
        return typeMethods.get();
    }

    @Override
    protected Stream<TypeMethod> getAll() {
        return all();
    }

    @Override
    protected Optional<TypeMethod> whenExpectingOneAndFoundMore(String methodName, DiamondException e) {
        throw new DiamondException("There's more than one method named \""+methodName+"\"",e);
    }

    @Override
    protected TypeMethod whenExpectingOneAnNoneFound(String methodName) {
        // NoMethods is the error case
        return NoMethods.INSTANCE.existingNamed(methodName);
    }

    public static TypeMethodsImpl create(Supplier<Stream<TypeMethod>> classMethods) {
        TypeMethodsImpl methodSource = new TypeMethodsImpl();
        methodSource.typeMethods = classMethods;
        return methodSource;
    }


}
