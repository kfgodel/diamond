package ar.com.kfgodel.diamond.impl.types.parts.componenttype;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the supplier of a type that has no component type
 * Created by kfgodel on 28/09/14.
 */
public class NoComponentTypeSupplier implements Supplier<Nary<TypeInstance>> {

    public static final NoComponentTypeSupplier INSTANCE = new NoComponentTypeSupplier();

    @Override
    public Nary<TypeInstance> get() {
        return Nary.empty();
    }
}
