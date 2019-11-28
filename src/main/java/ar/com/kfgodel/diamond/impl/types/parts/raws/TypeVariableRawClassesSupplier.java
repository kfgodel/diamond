package ar.com.kfgodel.diamond.impl.types.parts.raws;

import ar.com.kfgodel.diamond.impl.natives.raws.TypeVariableRawClassExtractor;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.TypeVariable;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type represents the supplier for a type variable raw classes
 * Date: 27/11/19 - 21:17
 */
public class TypeVariableRawClassesSupplier implements Supplier<Nary<Class<?>>> {

  private TypeVariable<?> typeVariable;

  @Override
  public Nary<Class<?>> get() {
    final Set<Class<?>> rawClasses = TypeVariableRawClassExtractor.create().apply(typeVariable);
    return Nary.from(rawClasses);
  }

  public static TypeVariableRawClassesSupplier create(TypeVariable<?> typeVariable) {
    TypeVariableRawClassesSupplier supplier = new TypeVariableRawClassesSupplier();
    supplier.typeVariable = typeVariable;
    return supplier;
  }

}
