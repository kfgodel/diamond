package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;

import java.util.stream.Stream;

/**
 * This type represents the source of constructors for a type that has no constructors
 * Created by kfgodel on 15/10/14.
 */
public class NoConstructors implements TypeConstructors {

    public static final NoConstructors INSTANCE = new NoConstructors();

    @Override
    public Stream<TypeConstructor> all() {
        return Stream.empty();
    }
}
