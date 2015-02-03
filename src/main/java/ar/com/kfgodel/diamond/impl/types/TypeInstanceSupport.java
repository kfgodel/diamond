package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.constructors.sources.NoConstructors;
import ar.com.kfgodel.diamond.impl.fields.sources.NoFields;
import ar.com.kfgodel.diamond.impl.fields.sources.TypeFieldsImpl;
import ar.com.kfgodel.diamond.impl.methods.sources.NoMethods;
import ar.com.kfgodel.diamond.impl.methods.sources.TypeMethodsImpl;
import ar.com.kfgodel.diamond.impl.types.declaration.TypeDeclaration;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.generics.UnGenerifiedTypeGenerics;
import ar.com.kfgodel.diamond.impl.types.inheritance.SuppliedTypesInheritance;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.names.NoNamesSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.lang.annotation.Annotation;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This type serves as a base implementation for common TypeInstance behavior
 * Created by kfgodel on 20/09/14.
 */
public abstract class TypeInstanceSupport implements TypeInstance {

    /**
     * Attached type annotations
     */
    private Supplier<Nary<Annotation>> annotations = NoAnnotationsSupplier.INSTANCE;
    /**
     * Variations on the name for this type
     */
    private Supplier<TypeNames> names = NoNamesSupplier.create(this);

    /**
     * This type callable methods
     */
    private TypeMethods methods = NoMethods.INSTANCE;

    /**
     * This type state fields
     */
    private TypeFields fields = NoFields.INSTANCE;

    /**
     * Inheritance information
     */
    private TypeInheritance inheritance;

    private Supplier<Nary<TypePackage>> typePackage;

    private Supplier<Nary<Class<?>>> rawClasses;

    private Function<TypeInstance,Object> identityToken;
    
    private Supplier<Nary<Kind>> kinds;
    
    private Predicate<Object> typeForPredicate;

    /**
     * Use this to override default creation with no annotations
     * @param annotationSupplier The new annotations
     */
    protected void setAnnotations(Supplier<Nary<Annotation>> annotationSupplier) {
        this.annotations = annotationSupplier;
    }

    @Override
    public Nary<Annotation> annotations() {
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
        this.names = namesSupplier;
    }

    @Override
    public TypeGenerics generics() {
        return UnGenerifiedTypeGenerics.INSTANCE;
    }

    @Override
    public TypeMethods methods() {
        return methods;
    }

    protected void setMethods(Supplier<Nary<TypeMethod>> typeMethods){
        this.methods = TypeMethodsImpl.create(typeMethods);
    }

    @Override
    public TypeFields fields() {
        return fields;
    }

    protected void setFields(Supplier<Nary<TypeField>> typeFields){
        this.fields = TypeFieldsImpl.create(typeFields);
    }

    @Override
    public TypeConstructors constructors() {
        return NoConstructors.INSTANCE;
    }

    /**
     * Default implementation with no component type
     * @return An empty optional
     */
    @Override
    public Nary<TypeInstance> componentType() {
        return NaryFromNative.empty();
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
    public int hashCode() {
        return getIdentityToken().hashCode();
    }

    @Override
    public Object newInstance() {
        return constructors().niladic()
                .mapOptional((constructor) -> constructor.invoke())
                .orElseThrow(() -> new DiamondException("Type[" + this + "] doesn't have a no-arg constructor to create the instance from"));
    }

    @Override
    public Object get() {
        return newInstance();
    }

    @Override
    public TypeInheritance inheritance() {
        return inheritance;
    }

    @Override
    public Nary<TypePackage> declaredPackage() {
        return typePackage.get();
    }

    @Override
    public Nary<TypeMember> members() {
        return fields().all().joinedWith(methods().all()).joinedWith(constructors().all());
    }

    @Override
    public Nary<Class<?>> nativeTypes() {
        return rawClasses.get();
    }

    @Override
    public Object getIdentityToken() {
        return identityToken.apply(this);
    }


    @Override
    public Nary<Kind> kinds() {
        return kinds.get();
    }

    @Override
    public boolean isA(Kind testedKind) {
        return this.kinds().anyMatch(testedKind::equals);
    }


    @Override
    public boolean isTypeFor(Object anObject) {
        return typeForPredicate.test(anObject);
    }

    protected void initializeSuper(TypeDescription description){
        this.setNames(description.getNames());
        this.setAnnotations(description.getAnnotations());
        this.setMethods(description.getTypeMethods());
        this.setFields(description.getTypeFields());
        this.rawClasses = description.getRawClassesSupplier();
        this.typePackage = description.getDeclaredPackage();
        this.inheritance = SuppliedTypesInheritance.create(this, description.getInheritanceDescription());
        this.identityToken = description.getIdentityToken();
        this.kinds = description.getKindsFor(this);
        this.typeForPredicate = description.getTypeForPredicate();
    };
}
