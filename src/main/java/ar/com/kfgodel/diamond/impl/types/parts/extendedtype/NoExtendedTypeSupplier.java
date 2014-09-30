package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the supplier of type without extended super type
 * Created by kfgodel on 28/09/14.
 */
public class NoExtendedTypeSupplier implements Supplier<Optional<TypeInstance>> {

    public static final NoExtendedTypeSupplier INSTANCE = new NoExtendedTypeSupplier();

    @Override
    public Optional<TypeInstance> get() {
        return Optional.empty();
    }

}
