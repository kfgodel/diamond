package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.natives.suppliers.InheritedMemberSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the diamond supplier of type fields from native class instances
 * Created by kfgodel on 12/10/14.
 */
public class ClassFieldSupplier {

  public static Supplier<Nary<TypeField>> create(Supplier<Nary<Class<?>>> baseClassesSupplier) {
    return NarySupplierFromCollection.lazilyBy(() -> {
      Stream<Field> nativeFields = calculateFields(baseClassesSupplier);
      return nativeFields
        .map((nativeField) -> Diamond.fields().from(nativeField))
        .collect(Collectors.toList());
    });
  }

  private static Stream<Field> calculateFields(Supplier<Nary<Class<?>>> baseClassesSupplier) {
    final Set<Class<?>> rawClasses = baseClassesSupplier.get()
      .collect(Collectors.toSet());
    final Supplier<Stream<Field>> fieldInheritedMemberSupplier = InheritedMemberSupplier.create(
      rawClasses,
      (superClass) -> Arrays.stream(superClass.getDeclaredFields())
    );
    return fieldInheritedMemberSupplier.get();
  }

}
