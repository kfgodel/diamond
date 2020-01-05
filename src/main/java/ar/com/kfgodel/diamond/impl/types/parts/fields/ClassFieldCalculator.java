package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.natives.suppliers.InheritedMemberSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Field;
import java.util.function.Supplier;

/**
 * This type represents the diamond supplier of type fields from native class instances
 * Created by kfgodel on 12/10/14.
 */
public class ClassFieldCalculator implements Supplier<Nary<TypeField>> {

  private Supplier<? extends Nary<Class<?>>> runtimeClasses;

  @Override
  public Nary<TypeField> get() {
    Nary<Field> nativeFields = calculateFields(runtimeClasses);
    return nativeFields
      .map((nativeField) -> Diamond.fields().from(nativeField));
  }

  private static Nary<Field> calculateFields(Supplier<? extends Nary<Class<?>>> runtimeClasses) {
    return InheritedMemberSupplier.create(runtimeClasses, Class::getDeclaredFields)
      .get();
  }

  public static ClassFieldCalculator create(Supplier<? extends Nary<Class<?>>> runtimeClasses) {
    ClassFieldCalculator calculator = new ClassFieldCalculator();
    calculator.runtimeClasses = runtimeClasses;
    return calculator;
  }


}
