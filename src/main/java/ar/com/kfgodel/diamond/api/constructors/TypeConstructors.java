package ar.com.kfgodel.diamond.api.constructors;

import java.util.stream.Stream;

/**
 * This type represents the source of constructors for a given type
 * Created by kfgodel on 15/10/14.
 */
public interface TypeConstructors {
    /**
     * @return All the constructors for a type
     */
    Stream<TypeConstructor> all();

}
