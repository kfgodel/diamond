package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.NoBoundsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.NoComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.NoExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.NoSuperclassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.NoTypeArgumentsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.NoTypeParametersSupplier;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type serves as base class for unannotated types
 * Created by kfgodel on 29/09/14.
 */
public abstract class UnannotatedTypeDescriptionSupport implements TypeDescription {

    @Override
    public Supplier<Annotation[]> getAnnotations() {
        return NoAnnotationsSupplier.INSTANCE;
    }

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return NoSuperclassSupplier.INSTANCE;
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return NoExtendedTypeSupplier.INSTANCE;
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeArguments() {
        return NoTypeArgumentsSupplier.INSTANCE;
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeParametersSupplier() {
        return NoTypeParametersSupplier.INSTANCE;
    }

    @Override
    public Supplier<Optional<TypeInstance>> getComponentType() {
        return NoComponentTypeSupplier.INSTANCE;
    }

    @Override
    public Supplier<TypeBounds> getBounds() {
        return NoBoundsSupplier.INSTANCE;
    }
}
