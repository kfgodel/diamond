package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.api.methods.ClassMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.naming.TypeNames;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.impl.declaration.TypeDeclaration;
import ar.com.kfgodel.diamond.impl.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.fields.sources.ClassTypeFields;
import ar.com.kfgodel.diamond.impl.fields.sources.NoFields;
import ar.com.kfgodel.diamond.impl.methods.sources.ClassTypeMethods;
import ar.com.kfgodel.diamond.impl.methods.sources.NoMethods;
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
    private Supplier<Stream<Annotation>> annotations = NoAnnotationsSupplier.INSTANCE;
    /**
     * Variations on the name for this type
     */
    private LazyValue<TypeNames> names = SuppliedValue.create(NoNamesSupplier.create(this));

    /**
     * This type callable methods
     */
    private TypeMethods methods = NoMethods.INSTANCE;

    /**
     * This type state fields
     */
    private TypeFields fields = NoFields.INSTANCE;

    /**
     * Use this to override default creation with no annotations
     * @param annotationSupplier The new annotations
     */
    protected void setAnnotations(Supplier<Stream<Annotation>> annotationSupplier) {
        this.annotations = annotationSupplier;
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

    @Override
    public TypeMethods methods() {
        return methods;
    }

    protected void setMethods(Supplier<Stream<ClassMethod>> typeMethods){
        this.methods = ClassTypeMethods.create(typeMethods);
    }

    @Override
    public TypeFields fields() {
        return fields;
    }

    protected void setFields(Supplier<Stream<ClassField>> typeFields){
        this.fields = ClassTypeFields.create(typeFields);
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

    protected void initializeSuper(TypeDescription description){
        this.setNames(description.getNames());
        this.setAnnotations(description.getAnnotations());
        this.setMethods(description.getTypeMethods());
        this.setFields(description.getTypeFields());
    };
}
