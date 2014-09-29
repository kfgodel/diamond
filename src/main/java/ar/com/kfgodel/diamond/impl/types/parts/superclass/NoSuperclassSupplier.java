package ar.com.kfgodel.diamond.impl.types.parts.superclass;

import ar.com.kfgodel.diamond.api.ClassInstance;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a supplier for types that have no super class
 * Created by kfgodel on 28/09/14.
 */
public class NoSuperclassSupplier implements Supplier<Optional<ClassInstance>> {

    public static final NoSuperclassSupplier INSTANCE = new NoSuperclassSupplier();

    @Override
    public Optional<ClassInstance> get() {
        return Optional.empty();
    }
}
