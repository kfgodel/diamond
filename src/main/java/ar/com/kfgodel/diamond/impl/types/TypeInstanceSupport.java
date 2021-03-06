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
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.compile.CompileTimeHierarchy;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.api.types.runtime.TypeRuntime;
import ar.com.kfgodel.diamond.impl.constructors.sources.NoConstructors;
import ar.com.kfgodel.diamond.impl.fields.sources.NoFields;
import ar.com.kfgodel.diamond.impl.fields.sources.TypeFieldsImpl;
import ar.com.kfgodel.diamond.impl.methods.sources.NoMethods;
import ar.com.kfgodel.diamond.impl.methods.sources.TypeMethodsImpl;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;
import ar.com.kfgodel.diamond.impl.types.compile.DefaultCompileHierarchy;
import ar.com.kfgodel.diamond.impl.types.equality.TypeEquality;
import ar.com.kfgodel.diamond.impl.types.is.DefaultTypeTests;
import ar.com.kfgodel.diamond.impl.types.runtime.DefaultTypeRuntime;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

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
  private Supplier<? extends Nary<Annotation>> annotations = Nary::empty;
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
  private CompileTimeHierarchy inheritance;

  private Supplier<Unary<TypePackage>> typePackage;

  private Function<TypeInstance, Object> identityToken;

  private Supplier<? extends Nary<TypeCategory>> categories;

  private TypeGenerics generics;

  private Supplier<Unary<Object>> reflectionTypeSupplier;

  private TypeRuntime runtime;

  /**
   * Use this to override default creation with no annotations
   *
   * @param annotationSupplier The new annotations
   */
  protected void setAnnotations(Supplier<? extends Nary<Annotation>> annotationSupplier) {
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
  public TypeGenerics generic() {
    return generics;
  }

  @Override
  public TypeMethods methods() {
    return methods;
  }

  protected void setMethods(Supplier<? extends Nary<TypeMethod>> typeMethods) {
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

  protected void setFields(Supplier<? extends Nary<TypeField>> typeFields) {
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
  public Unary<TypeInstance> componentType() {
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
  public CompileTimeHierarchy hierarchy() {
    return inheritance;
  }

  @Override
  public Unary<TypePackage> declaredPackage() {
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
  public Nary<TypeCategory> categories() {
    return categories.get();
  }

  protected void initializeSuper(TypeDescription description) {
    this.setNames(description.getNamesCalculator().apply(this));
    this.setAnnotations(description.getAnnotations());
    this.setMethods(description.getTypeMethods());
    this.setFields(description.getTypeFields());
    this.reflectionTypeSupplier = description.getReflectionTypeSupplier();
    this.runtime = DefaultTypeRuntime.create(
      this,
      description.getRuntimeClasses(),
      description.getRuntimeType(),
      description.getInheritanceDescription()
    );
    this.typePackage = description.getDeclaredPackage();
    this.inheritance = DefaultCompileHierarchy.create(this, description.getInheritanceDescription());
    this.identityToken = description.getIdentityToken();
    this.categories = description.getCategoriesCalculator().apply(this);
    this.tests = DefaultTypeTests.create(this,
      description.getAssignabilityPredicate(),
      description.getInstancePredicate()
    );
    this.generics = createGenericsInfoFrom(description);
  }

  protected abstract TypeGenerics createGenericsInfoFrom(TypeDescription description);

  @Override
  public <T> Unary<T> reflectedAs(Class<T> expectedType) {
    return reflectionTypeSupplier.get()
      .map(expectedType::cast); // If this fails, a poor error message is thrown. Can be improve it?
  }
}
