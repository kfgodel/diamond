package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.classes.TypeLineage;
import ar.com.kfgodel.diamond.api.sources.TypeMethodSource;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.classes.SingleTypeLineage;
import ar.com.kfgodel.diamond.impl.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.sources.NoMethodsSource;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.NoNamesSupplier;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type serves as a base implementation for common TypeInstance behavior
 * Created by kfgodel on 20/09/14.
 */
public abstract class TypeInstanceSupport implements TypeInstance {

    /**
     * Attached type annotations
     */
    private LazyValue<Stream<Annotation>> annotations = SuppliedValue.create(NoAnnotationsSupplier.INSTANCE);
    /**
     * Variations on the name for this type
     */
    private LazyValue<TypeNames> names = SuppliedValue.create(NoNamesSupplier.create(this));

    /**
     * Use this to override default creation with no annotations
     * @param annotationSupplier The new annotations
     */
    protected void setAnnotations(Supplier<Stream<Annotation>> annotationSupplier) {
        this.annotations = SuppliedValue.create(annotationSupplier);
    }

    @Override
    public Stream<Annotation> annotations() {
        return this.annotations.get();
    }

    @Override
    public String name() {
        return this.names().shortName();
    }

    @Override
    public TypeNames names() {
        return this.names.get();
    }

    /**
     * Setter available to subclasses to define this instance names
     * @param namesSupplier The multiple names of this instance
     */
    protected void setNames(Supplier<TypeNames> namesSupplier) {
        this.names = SuppliedValue.create(namesSupplier);
    }

    /**
     * Default implementation with no bounds
     * @return A no bounds instance
     */
    @Override
    public TypeBounds bounds() {
        return NoBounds.INSTANCE;
    }

    /**
     * Default implementation with no component type
     * @return An empty optional
     */
    @Override
    public Optional<TypeInstance> componentType() {
        return Optional.empty();
    }

    /**
     * Default implementation with no arguments
     * @return An empty stream
     */
    @Override
    public Stream<TypeInstance> typeArguments() {
        return Stream.empty();
    }

    @Override
    public Stream<TypeInstance> typeParameters() {
        return Stream.empty();
    }

    @Override
    public Optional<TypeInstance> extendedType() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.names().declarationName();
    }

    @Override
    public boolean equals(Object obj) {
        return TypeEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public Optional<TypeInstance> superclass() {
        return Optional.empty();
    }

    @Override
    public TypeMethodSource methods() {
        return NoMethodsSource.INSTANCE;
    }

    @Override
    public TypeLineage typeLineage() {
        return SingleTypeLineage.create(this);
    }

    @Override
    public TypeLineage classLineage() {
        return SingleTypeLineage.create(this);
    }
}
