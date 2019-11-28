package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.UnannotatedTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.VariableTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.WildcardBoundsSupplier;
import ar.com.kfgodel.diamond.impl.types.parts.raws.WildcardRawClassesSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.WildcardType;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the description of an unannotated native wildcard type
 * Created by kfgodel on 28/09/14.
 */
public class WildcardTypeDescription extends TypeDescriptionSupport {

  private WildcardType nativeType;

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
    return getRawClassesSupplier().get().collect(Collectors.toSet());
  }

  @Override
  public Supplier<Nary<TypePackage>> getDeclaredPackage() {
    return unannotatedVariableTypeDescriptor().getDeclaredPackage();
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedVariableTypeDescriptor().getInheritanceDescription();
  }

  @Override
  public Supplier<TypeNames> getNamesSupplier(TypeInstance type) {
    return unannotatedVariableTypeDescriptor().getNamesSupplier(type);
  }

  public WildcardType getNativeType() {
    return nativeType;
  }

  @Override
  public Supplier<TypeBounds> getBounds() {
    return WildcardBoundsSupplier.create(nativeType);
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return unnanotatedTypeDescriptor().getRawClassSupplier();
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return WildcardRawClassesSupplier.create(nativeType);
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

  public static WildcardTypeDescription create(WildcardType nativeType) {
    WildcardTypeDescription description = new WildcardTypeDescription();
    description.nativeType = nativeType;
    return description;
  }

}
