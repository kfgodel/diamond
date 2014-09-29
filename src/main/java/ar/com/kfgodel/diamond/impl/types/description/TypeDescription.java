package ar.com.kfgodel.diamond.impl.types.description;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a description of a type and serves as a common interface for every native and extend types
 * Created by kfgodel on 28/09/14.
 */
public interface TypeDescription {

    Supplier<TypeNames> getNames();

    Supplier<Annotation[]> getAnnotations();

    Supplier<Optional<ClassInstance>> getSuperclassSupplier();

    Supplier<Optional<ClassInstance>> getExtendedTypeSupplier();

    Supplier<List<TypeInstance>> getTypeArguments();

    Supplier<List<TypeInstance>> getTypeParametersSupplier();

    Supplier<Optional<TypeInstance>> getComponentType();

    Supplier<TypeBounds> getBounds();


}
