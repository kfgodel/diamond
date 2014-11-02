package ar.com.kfgodel.diamond.impl.members.exceptions;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents the supplier for types that have no exceptions
 * Created by kfgodel on 02/11/14.
 */
public class NoExceptionsSupplier {

    public static Stream<TypeInstance> create() {
        return Stream.empty();
    }
}
