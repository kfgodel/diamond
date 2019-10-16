package ar.com.kfgodel.diamond.impl.natives.suppliers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents supplier of native class fields.<br>
 * It gets all the fields from a type and its hierarchy from the list of native classes
 * Created by kfgodel on 12/10/14.
 */
public class NativeFieldsSupplier {

  public static Supplier<Stream<Field>> create(Set<Class<?>> rawClasses) {
    return InheritedMemberSupplier.create(rawClasses, (superClass) -> Arrays.stream(superClass.getDeclaredFields()));
  }

}
