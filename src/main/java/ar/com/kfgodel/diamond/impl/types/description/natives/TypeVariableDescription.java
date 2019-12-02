package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.description.descriptors.VariableTypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.names.TypeVariableNamesDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.TypeVariableBoundSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents a description of an unannotated native type variable
 * Created by kfgodel on 28/09/14.
 */
public class TypeVariableDescription extends TypeDescriptionSupport {

  private TypeVariable<?> nativeType;

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
    return Nary::empty;
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return unannotatedVariableTypeDescriptor().getInheritanceDescription();
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return TypeVariableNamesDescription.create(nativeType);
  }

  protected Type getNativeType() {
    return nativeType;
  }

  @Override
  public Supplier<TypeBounds> getBounds() {
    return TypeVariableBoundSupplier.create(nativeType);
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassSupplier() {
    return CachedValue.lazilyBy(() -> {
      final Nary<Class<?>> rawClasses = getRawClassesSupplier().get();
      final Class<?> firstRawClass = RawClassesCalculator.create().coalesce(rawClasses);
      return Nary.of(firstRawClass);
    });
  }

  @Override
  public Supplier<Nary<Class<?>>> getRawClassesSupplier() {
    return NaryFromCollectionSupplier.lazilyBy(()-> {
      return RawClassesCalculator.create()
        .from(nativeType)
        .collect(Collectors.toSet());
    });
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

  protected VariableTypeDescriptor unannotatedVariableTypeDescriptor(){
    return VariableTypeDescriptor.create(getNativeType(), getBehavioralClasses(), getTypeArguments());
  }

  public static TypeVariableDescription create(TypeVariable<?> typeVariable) {
    TypeVariableDescription description = new TypeVariableDescription();
    description.nativeType = typeVariable;
    return description;
  }

}
