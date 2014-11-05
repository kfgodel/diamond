package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;

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
    public InheritanceDescription getInheritanceDescription() {
        return getDelegateDescription().getInheritanceDescription();
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
    public Supplier<Stream<TypeMethod>> getTypeMethods() {
        return getDelegateDescription().getTypeMethods();
    }

    @Override
    public Supplier<Stream<TypeField>> getTypeFields() {
        return getDelegateDescription().getTypeFields();
    }

    @Override
    public Supplier<Stream<TypeConstructor>> getTypeConstructors() {
        return getDelegateDescription().getTypeConstructors();
    }

    @Override
    public Supplier<Optional<TypePackage>> getDeclaredPackage() {
        return getDelegateDescription().getDeclaredPackage();
    }

    protected abstract TypeDescription getDelegateDescription();
}
