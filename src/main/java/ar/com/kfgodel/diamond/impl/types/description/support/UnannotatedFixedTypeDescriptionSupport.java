package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fragments.RawClassExtractor;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.ExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.FixedTypeNameSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.SuperClassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.TypeParametersSupplier;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a base class for unannotated fixed types
 * Created by kfgodel on 29/09/14.
 */
public abstract class UnannotatedFixedTypeDescriptionSupport extends UnannotatedTypeDescriptionSupport {

    private Class<?> rawClass;

    protected Class<?> getRawClass(){
        if (rawClass == null) {
            rawClass = RawClassExtractor.fromUnspecific(getNativeType());
        }
        return rawClass;
    }


    @Override
    public Supplier<TypeNames> getNames() {
        return FixedTypeNameSupplier.create(getRawClass(), getNativeType());
    }

    @Override
    public Supplier<Optional<ClassInstance>> getSuperclassSupplier() {
        return SuperClassSupplier.create(getRawClass());
    }

    @Override
    public Supplier<Optional<ClassInstance>> getExtendedTypeSupplier() {
        return ExtendedTypeSupplier.create(getRawClass(), this.getTypeArguments().get());
    }

    @Override
    public Supplier<List<TypeInstance>> getTypeParametersSupplier() {
        return TypeParametersSupplier.create(getRawClass());
    }

    protected abstract Type getNativeType();


}
