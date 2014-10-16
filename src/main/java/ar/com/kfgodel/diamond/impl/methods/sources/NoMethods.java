package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;

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

}
