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
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.api.types.runtime.TypeRuntime;
import ar.com.kfgodel.diamond.impl.constructors.sources.NoConstructors;
import ar.com.kfgodel.diamond.impl.fields.sources.NoFields;
import ar.com.kfgodel.diamond.impl.fields.sources.TypeFieldsImpl;
import ar.com.kfgodel.diamond.impl.methods.sources.NoMethods;
import ar.com.kfgodel.diamond.impl.methods.sources.TypeMethodsImpl;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.inheritance.SuppliedTypesInheritance;
import ar.com.kfgodel.diamond.impl.types.is.DefaultTypeTests;
import ar.com.kfgodel.diamond.impl.types.names.TypeInstanceNames;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.types.runtime.DefaultTypeRuntime;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Function;
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
  private Supplier<TypeNames> names;

  /**
   * This type callable methods
   */
  private TypeMethods methods = NoMethods.INSTANCE;

  /**
   * This type state fields
   */
  private TypeFields fields = NoFields.INSTANCE;

  /**
   * Verifications against this type
   */
  private TypeTests tests;

  /**
   * Inheritance information
   */
  private TypeInheritance inheritance;

  private Supplier<Nary<TypePackage>> typePackage;

  private Function<TypeInstance, Object> identityToken;

  private Supplier<Nary<Kind>> kinds;

  private TypeGenerics generics;

  private Supplier<Nary<Object>> reflectionTypeSupplier;

  private TypeRuntime runtime;

  /**
   * Use this to override default creation with no annotations
   *
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
   *
   * @param namesSupplier The multiple names of this instance
   */
  protected void setNames(Supplier<TypeNames> namesSupplier) {
    this.names = namesSupplier;
  }

  @Override
  public TypeGenerics generics() {
    return generics;
  }

  @Override
  public TypeMethods methods() {
    return methods;
  }

  protected void setMethods(Supplier<Nary<TypeMethod>> typeMethods) {
    this.methods = TypeMethodsImpl.create(typeMethods);
  }

  @Override
  public TypeRuntime runtime() {
    return runtime;
  }

  @Override
  public TypeFields fields() {
    return fields;
  }

  protected void setFields(Supplier<Nary<TypeField>> typeFields) {
    this.fields = TypeFieldsImpl.create(typeFields);
  }

  @Override
  public TypeConstructors constructors() {
    return NoConstructors.INSTANCE;
  }

  /**
   * Default implementation with no component type
   *
   * @return An empty optional
   */
  @Override
  public Nary<TypeInstance> componentType() {
    return Nary.empty();
  }

  @Override
  public String declaration() {
    // As we can't generate the full source, we use the complete name as the closes declaration we have
    return names().completeName();
  }

  @Override
  public String toString() {
    return DebugPrinter.print(this);
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
      .map((constructor) -> constructor.invoke())
      .orElseThrow(() -> new DiamondException("Type[" + this + "] doesn't have a no-arg constructor " +
        "to create the instance from"));
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
    // Need to cast to an upper type of element
    Nary<TypeMember> fields = (Nary) fields().all();
    return fields
      .concat(methods().all())
      .concat(constructors().all());
  }

  @Override
  public Object getIdentityToken() {
    return identityToken.apply(this);
  }

  @Override
  public TypeTests is() {
    return this.tests;
  }

  @Override
  public Nary<Kind> kinds() {
    return kinds.get();
  }

  protected void initializeSuper(TypeDescription description) {
    this.setNames(CachedValue.lazilyBy(()-> TypeInstanceNames.create(this, description.getNamesDescription())));
    this.setAnnotations(description.getAnnotations());
    this.setMethods(description.getTypeMethods());
    this.setFields(description.getTypeFields());
    this.reflectionTypeSupplier = description.getReflectionTypeSupplier();
    this.runtime = DefaultTypeRuntime.create(
      description.getRuntimeClasses(),
      description.getRuntimeType(),
      description.getInheritanceDescription()
    );
    this.typePackage = description.getDeclaredPackage();
    this.inheritance = SuppliedTypesInheritance.create(this, description.getInheritanceDescription());
    this.identityToken = description.getIdentityToken();
    this.kinds = description.getKindsFor(this);
    this.tests = DefaultTypeTests.create(this,
      description.getAssignabilityPredicate(),
      description.getTypeForPredicate()
    );
    this.generics = createGenericsInfoFrom(description);
  }

  protected abstract TypeGenerics createGenericsInfoFrom(TypeDescription description);

  @Override
  public Nary<Object> reflectionType() {
    return reflectionTypeSupplier.get();
  }
}
