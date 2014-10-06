package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a description that delegates part of it to another description
 * Created by kfgodel on 29/09/14.
 */
public abstract class DelegatedDescriptionSupport implements TypeDescription {
    
    @Override
    public Supplier<TypeNames> getNames() {
        return getDelegateDescription().getNames();
    }

    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
        return getDelegateDescription().getAnnotations();
    }

    @Override
    public Supplier<Optional<TypeInstance>> getSuperclassSupplier() {
        return getDelegateDescription().getSuperclassSupplier();
    }

    @Override
    public Supplier<Optional<TypeInstance>> getExtendedTypeSupplier() {
        return getDelegateDescription().getExtendedTypeSupplier();
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeArguments() {
        return getDelegateDescription().getTypeArguments();
    }

    @Override
    public Supplier<Stream<TypeInstance>> getTypeParametersSupplier() {
        return getDelegateDescription().getTypeParametersSupplier();
    }

    @Override
    public Supplier<Optional<TypeInstance>> getComponentType() {
        return getDelegateDescription().getComponentType();
    }

    @Override
    public Supplier<TypeBounds> getBounds() {
        return getDelegateDescription().getBounds();
    }

    @Override
    public boolean isForVariableType() {
        return getDelegateDescription().isForVariableType();
    }

    @Override
    public Supplier<Stream<ClassMethod>> getTypeMethods() {
        return getDelegateDescription().getTypeMethods();
    }

    protected abstract TypeDescription getDelegateDescription();
}
