package ar.com.kfgodel.diamond.impl.types.description.support;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.kinds.KindOf;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.natives.RawClassExtractor;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.NoInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.NoBoundsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.NoComponentTypeSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.constructors.NonInstantiableConstructorSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.fields.ClassFieldSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.methods.ClassMethodSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.NoTypeArgumentsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.NoTypeParametersSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type serves as base class for unannotated types
 * Created by kfgodel on 29/09/14.
 */
public abstract class UnannotatedTypeDescriptionSupport implements TypeDescription {

    private Class<?> rawClass;
    private Set<Class<?>> rawClasses;

    /**
     * The set of classes that define the behavior of this type.<br>
     *     It can be more than one if this is a multiple upper bounded type description.<br>
     *     The behavior of this type is then defined as the join of the upper bounds (it's a type that subclasses
     *     all this behavioral classes).<br>
     *     It can be just one class if this description represents a fixed type
     * @return The list of raw classes that define this type behavior description
     */
    protected Set<Class<?>> getBehavioralClasses() {
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
            rawClass = RawClassExtractor.coalesce(getBehavioralClasses());
        }
        return rawClass;
    }

    protected abstract Type getNativeType();


    @Override
    public Supplier<Nary<Annotation>> getAnnotations() {
        return NoAnnotationsSupplier.INSTANCE;
    }

    @Override
    public InheritanceDescription getInheritanceDescription() {
        return NoInheritanceDescription.INSTANCE;
    }

    @Override
    public Supplier<Nary<TypeInstance>> getTypeArguments() {
        return NoTypeArgumentsSupplier.INSTANCE;
    }

    @Override
    public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
        return NoTypeParametersSupplier.INSTANCE;
    }

    @Override
    public Supplier<Nary<TypeInstance>> getComponentType() {
        return NoComponentTypeSupplier.INSTANCE;
    }

    @Override
    public Supplier<TypeBounds> getBounds() {
        return NoBoundsSupplier.INSTANCE;
    }

    @Override
    public Supplier<Nary<TypeMethod>> getTypeMethods() {
        return ClassMethodSupplier.create(getBehavioralClasses());
    }

    @Override
    public Supplier<Nary<TypeField>> getTypeFields() {
        return ClassFieldSupplier.create(getBehavioralClasses());
    }

    @Override
    public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
        return NonInstantiableConstructorSupplier.INSTANCE;
    }

    @Override
    public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
        return NaryFromCollectionSupplier.from(getBehavioralClasses());
    }

    @Override
    public Function<TypeInstance, Object> getIdentityToken() {
        return CachedTokenCalculator.create(TypeEquality.INSTANCE::calculateTokenFor);
    }

    @Override
    public Supplier<Nary<Kind>> getKindsFor(TypeInstance type) {
        return NaryFromCollectionSupplier.lazilyBy(()-> Arrays.stream(KindOf.values())
                .filter((kind)-> kind.contains(type))
                .collect(Collectors.toList()));
    }
}
