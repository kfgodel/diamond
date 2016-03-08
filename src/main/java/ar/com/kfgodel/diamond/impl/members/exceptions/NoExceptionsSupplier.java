package ar.com.kfgodel.diamond.impl.members.exceptions;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the supplier for types that have no exceptions
 * Created by kfgodel on 02/11/14.
 */
public class NoExceptionsSupplier {

    public static Nary<TypeInstance> create() {
        return Nary.empty();
    }
}
