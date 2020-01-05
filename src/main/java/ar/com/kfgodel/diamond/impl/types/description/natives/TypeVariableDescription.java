package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.natives.raws.RawClassesCalculator;
import ar.com.kfgodel.diamond.impl.types.bounds.SingleBound;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.VariableTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.names.TypeVariableNamesDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.TypeDescriptionSupport;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.TypeVariable;
import java.util.function.Supplier;

/**
 * This type represents a description of an unannotated native type variable
 * Created by kfgodel on 28/09/14.
 */
public class TypeVariableDescription extends TypeDescriptionSupport {

  private TypeVariable<?> nativeType;

  @Override
  public Supplier<TypeBounds> getBounds() {
    return CachedValue.from(() -> {
      final Nary<TypeInstance> upperBounds = Diamond.types().from(nativeType.getAnnotatedBounds());
      return SingleBound.create(upperBounds);
    });
  }

  @Override
  public Supplier<Unary<TypePackage>> getDeclaredPackage() {
    return Nary::empty;
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    return VariableTypeInheritanceDescription.create(getBounds());
  }

  @Override
  public TypeNamesDescription getNamesDescription() {
    return TypeVariableNamesDescription.create(nativeType);
  }

  @Override
  public Supplier<Unary<Object>> getReflectionTypeSupplier() {
    return CachedValue.from(()-> Nary.of(this.nativeType));
  }

  @Override
  public Supplier<Nary<Class<?>>> getRuntimeClasses() {
    return CachedValues.adapting(()-> {
      return RawClassesCalculator.create()
        .from(nativeType)
        .distinct();
    });
  }

  @Override
  public boolean isForVariableType() {
    return true;
  }

  public static TypeVariableDescription create(TypeVariable<?> typeVariable) {
    TypeVariableDescription description = new TypeVariableDescription();
    description.nativeType = typeVariable;
    return description;
  }

}
