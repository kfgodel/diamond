package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.natives.suppliers.InheritedMemberSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the diamond supplier of type fields from native class instances
 * Created by kfgodel on 12/10/14.
 */
public class ClassFieldCalculator implements Supplier<List<TypeField>> {

  private Supplier<Nary<Class<?>>> runtimeClasses;

  @Override
  public List<TypeField> get() {
    Stream<Field> nativeFields = calculateFields(runtimeClasses);
    return nativeFields
      .map((nativeField) -> Diamond.fields().from(nativeField))
      .collect(Collectors.toList());
  }

  private static Stream<Field> calculateFields(Supplier<Nary<Class<?>>> runtimeClasses) {
    return InheritedMemberSupplier.create(runtimeClasses, Class::getDeclaredFields)
      .get();
  }

  public static ClassFieldCalculator create(Supplier<Nary<Class<?>>> runtimeClasses) {
    ClassFieldCalculator calculator = new ClassFieldCalculator();
    calculator.runtimeClasses = runtimeClasses;
    return calculator;
  }


}
