package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This type represents the supplier for a type that has been extended with actual type arguments
 * Created by kfgodel on 29/09/14.
 */
public class ExtendedTypeArgumentsSupplier implements Supplier<List<TypeInstance>> {

    private Supplier<List<TypeInstance>> originalSupplier;
    private Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer;

    @Override
    public List<TypeInstance> get() {
        List<TypeInstance> originalTypeArguments = originalSupplier.get();
        extendedTypeArgumentsReplacer.accept(originalTypeArguments);
        return originalTypeArguments;
    }

    public static ExtendedTypeArgumentsSupplier create(Supplier<List<TypeInstance>> originalSupplier, Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer) {
        ExtendedTypeArgumentsSupplier supplier = new ExtendedTypeArgumentsSupplier();
        supplier.extendedTypeArgumentsReplacer = extendedTypeArgumentsReplacer;
        supplier.originalSupplier = originalSupplier;
        return supplier;
    }

}
