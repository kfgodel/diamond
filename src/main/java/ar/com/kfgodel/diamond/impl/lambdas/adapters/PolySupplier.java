package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

import java.util.function.Supplier;

/**
 * This type represents a poly invokable adapter around a supplier instance
 * Created by kfgodel on 02/02/15.
 */
public class PolySupplier implements PolymorphicInvokable {

    private Supplier supplier;

    @Override
    public Object invoke(Object... arguments) {
        return get();
    }

    @Override
    public Object get() {
        return supplier.get();
    }

    public static PolySupplier create(Supplier supplier) {
        PolySupplier polySupplier = new PolySupplier();
        polySupplier.supplier = supplier;
        return polySupplier;
    }

}
