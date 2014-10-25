package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents a source for a type that has no methods
 * Created by kfgodel on 30/09/14.
 */
public class NoMethods implements TypeMethods {

    public static final NoMethods INSTANCE = new NoMethods();

    @Override
    public Stream<TypeMethod> all() {
        return Stream.empty();
    }

    @Override
    public Stream<TypeMethod> named(String methodName) {
        return Stream.empty();
    }

    @Override
    public Optional<TypeMethod> uniqueNamed(String methodName) throws DiamondException {
        return Optional.empty();
    }

    @Override
    public TypeMethod existingNamed(String methodName) throws DiamondException {
        throw new DiamondException("There's no method named \""+methodName+"\"");
    }

}
