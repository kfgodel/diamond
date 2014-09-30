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


    /**
     * Indicates if this description is for a type that cannot be statically determined at compile type in every scope
     * (wildcards and type variables) and for that reason its class representation may be bounded to other type.
     * @return true if this type represents a type variable, a wildcard or annotated version of them
     */
    boolean isForVariableType();
}
