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
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

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
    public Supplier<Nary<Annotation>> getAnnotations() {
        return getDelegateDescription().getAnnotations();
    }

    @Override
    public InheritanceDescription getInheritanceDescription() {
        return getDelegateDescription().getInheritanceDescription();
    }

    @Override
    public Supplier<Nary<TypeInstance>> getTypeArguments() {
        return getDelegateDescription().getTypeArguments();
    }

    @Override
    public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
        return getDelegateDescription().getTypeParametersSupplier();
    }

    @Override
    public Supplier<Nary<TypeInstance>> getComponentType() {
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
    public Supplier<Nary<TypeMethod>> getTypeMethods() {
        return getDelegateDescription().getTypeMethods();
    }

    @Override
    public Supplier<Nary<TypeField>> getTypeFields() {
        return getDelegateDescription().getTypeFields();
    }

    @Override
    public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
        return getDelegateDescription().getTypeConstructors();
    }

    @Override
    public Supplier<Nary<TypePackage>> getDeclaredPackage() {
        return getDelegateDescription().getDeclaredPackage();
    }

    protected abstract TypeDescription getDelegateDescription();

    @Override
    public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
        return getDelegateDescription().getRawClassesSupplier();
    }
}
