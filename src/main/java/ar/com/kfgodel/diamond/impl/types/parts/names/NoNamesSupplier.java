package ar.com.kfgodel.diamond.impl.types.parts.names;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeNames;

import java.util.function.Supplier;

/**
 * This type represents the null names supplier for an uninitialized type
 * Created by kfgodel on 29/09/14.
 */
public class NoNamesSupplier  implements Supplier<TypeNames> {

    private Object ownerType;

    @Override
    public TypeNames get() {
        throw new DiamondException("The type["+this.ownerType+"] has no names source defined");
    }

    public static NoNamesSupplier create(Object unnamedType) {
        NoNamesSupplier supplier = new NoNamesSupplier();
        supplier.ownerType = unnamedType;
        return supplier;
    }

}
