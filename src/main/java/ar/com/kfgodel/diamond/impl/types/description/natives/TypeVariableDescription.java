package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.UnannotatedTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.VariableTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.TypeVariableBoundSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This type represents a description of an unannotated native type variable
 * Created by kfgodel on 28/09/14.
 */
public class TypeVariableDescription implements TypeDescription {

  private TypeVariable<?> typeVariable;

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return unnanotatedTypeDescriptor().getAnnotations();
  }

  @Override
  public Predicate<TypeInstance> getAssignabilityPredicate() {
    return unnanotatedTypeDescriptor().getAssignabilityPredicate();
  }

  /**
   * The set of classes that define the behavior of this type.<br>
   * It can be more than one if this is a multiple upper bounded type description.<br>
   * The behavior of this type is then defined as the join of the upper bounds (it's a type that subclasses
   * all this behavioral classes).<br>
   * It can be just one class if this description represents a fixed type
   *
   * @return The list of raw classes that define this type behavior description
   */
  protected Set<Class<?>> getBehavioralClasses() {
    return unnanotatedTypeDescriptor().getBehavioralClasses();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getComponentType() {
    return unnanotatedTypeDescriptor().getComponentType();
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return unannotatedVariableTypeDescriptor().getDeclaredPackage();
  }

  @Override
  public Function<TypeInstance, Object> getIdentityToken() {
    return unnanotatedTypeDescriptor().getIdentityToken();
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedVariableTypeDescriptor().getInheritanceDescription();
  }

  @Override
  public Supplier<Nary<Kind>> getKindsFor(TypeInstance type) {
    return unnanotatedTypeDescriptor().getKindsFor(type);
  }

  @Override
  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return unannotatedVariableTypeDescriptor().getNamesSupplier(type);
  }

  protected Type getNativeType() {
    return typeVariable;
  }

  @Override
  public Supplier<TypeBounds> getBounds() {
    return TypeVariableBoundSupplier.create(typeVariable);
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return unnanotatedTypeDescriptor().getRawClassSupplier();
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return unnanotatedTypeDescriptor().getRawClassesSupplier();
  }

  @Override
  public Supplier<TypeInstance> getRuntimeType() {
    return unnanotatedTypeDescriptor().getRuntimeType();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return unnanotatedTypeDescriptor().getTypeArguments();
  }

  @Override
  public Supplier<Nary<TypeConstructor>> getTypeConstructors() {
    return unnanotatedTypeDescriptor().getTypeConstructors();
  }

  @Override
  public Supplier<Nary<TypeField>> getTypeFields() {
    return unnanotatedTypeDescriptor().getTypeFields();
  }

  @Override
  public Predicate<Object> getTypeForPredicate() {
    return unnanotatedTypeDescriptor().getTypeForPredicate();
  }

  @Override
  public Supplier<Nary<TypeMethod>> getTypeMethods() {
    return unnanotatedTypeDescriptor().getTypeMethods();
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeParametersSupplier() {
    return unnanotatedTypeDescriptor().getTypeParametersSupplier();
  }

  @Override
  public boolean isForVariableType() {
    return unannotatedVariableTypeDescriptor().isForVariableType();
  }

  protected VariableTypeDescriptor unannotatedVariableTypeDescriptor(){
    return VariableTypeDescriptor.create(getNativeType(), getBehavioralClasses(), getTypeArguments());
  }

  protected UnannotatedTypeDescriptor unnanotatedTypeDescriptor(){
    return UnannotatedTypeDescriptor.create(getNativeType());
  }

  public static TypeVariableDescription create(TypeVariable<?> typeVariable) {
    TypeVariableDescription description = new TypeVariableDescription();
    description.typeVariable = typeVariable;
    return description;
  }

}
