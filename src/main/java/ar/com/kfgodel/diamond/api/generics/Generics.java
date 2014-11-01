package ar.com.kfgodel.diamond.api.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents the generics information available for a generified entity
 * Created by kfgodel on 01/11/14.
 */
public interface Generics {

    /**
     * @return The generic type parameters used to generify this entity (if any).
     *  (This is the list of type parameters used on the class, method, field, constructor, etc).
     */
    Stream<TypeInstance> parameters();
}
