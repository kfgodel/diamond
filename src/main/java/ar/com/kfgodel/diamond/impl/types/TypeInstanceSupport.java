package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.sources.TypeMethods;
import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.declaration.TypeDeclaration;
import ar.com.kfgodel.diamond.impl.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.sources.NoMethods;
import ar.com.kfgodel.diamond.impl.types.generics.NotGenerified;
import ar.com.kfgodel.diamond.impl.types.inheritance.NoParentsInheritance;
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

    @Override
    public TypeGenerics generics() {
        return NotGenerified.INSTANCE;
    }

    @Override
    public TypeInheritance inheritance() {
        return NoParentsInheritance.create(this);
    }


    /**
     * Default implementation with no component type
     * @return An empty optional
     */
    @Override
    public Optional<TypeInstance> componentType() {
        return Optional.empty();
    }



    @Override
    public String declaration() {
        return TypeDeclaration.create(this).asString();
    }
    @Override
    public String toString() {
        return this.declaration();
    }

    @Override
    public boolean equals(Object obj) {
        return TypeEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public TypeMethods methods() {
        return NoMethods.INSTANCE;
    }

}
