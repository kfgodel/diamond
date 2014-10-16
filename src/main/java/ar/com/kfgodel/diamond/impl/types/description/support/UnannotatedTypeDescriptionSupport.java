package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.impl.natives.RawClassExtractor;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.NoBoundsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.NoComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.NonInstantiableConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.extendedtype.NoExtendedTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.fields.ClassFieldSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.methods.ClassMethodSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.superclass.NoSuperclassSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.NoTypeArgumentsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.NoTypeParametersSupplier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type serves as base class for unannotated types
 * Created by kfgodel on 29/09/14.
 */
public abstract class UnannotatedTypeDescriptionSupport implements TypeDescription {

    private Class<?> rawClass;
    private Set<Class<?>> rawClasses;

    /**
     * The list of classes that for the raw version of this type.<br>
     *     More than one if this is a multiple upper bounded type description
     * @return The list of raw classes for this type description
     */
    protected Set<Class<?>> getRawClasses() {
        if (rawClasses == null) {
            rawClasses = RawClassExtractor.fromUnspecific(getNativeType());
        }
        return rawClasses;
    }

    /**
     * @return The class that represents this type without any annotations or generics
     */
    protected Class<?> getRawClass(){
        if (rawClass == null) {
            rawClass = RawClassExtractor.coalesce(getRawClasses());
        }
        return rawClass;
    }

    protected abstract Type getNativeType();


    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
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

    @Override
    public Supplier<Stream<TypeMethod>> getTypeMethods() {
        return ClassMethodSupplier.create(getRawClasses());
    }

    @Override
    public Supplier<Stream<TypeField>> getTypeFields() {
        return ClassFieldSupplier.create(getRawClasses());
    }

    @Override
    public Supplier<Stream<TypeConstructor>> getTypeConstructors() {
        return NonInstantiableConstructorSupplier.INSTANCE;
    }
}
