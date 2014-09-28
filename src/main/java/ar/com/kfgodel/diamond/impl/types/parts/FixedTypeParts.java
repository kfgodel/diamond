package ar.com.kfgodel.diamond.impl.types.parts;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the parts needed to build a FixedTypeInstance
 * Created by kfgodel on 28/09/14.
 */
public interface FixedTypeParts extends CommonTypeParts {

    Supplier<Optional<ClassInstance>> getSuperclassSupplier();

    Supplier<Optional<ClassInstance>> getExtendedTypeSupplier();

    List<TypeInstance> getTypeArguments();

    Optional<TypeInstance> getComponentType();

    Supplier<List<TypeInstance>> getTypeParametersSupplier();
}
