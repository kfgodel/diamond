package ar.com.kfgodel.diamond.impl.members.exceptions;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.Executable;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the exceptions supplier for executable native types
 * Created by kfgodel on 02/11/14.
 */
public class ExecutableExceptionsSupplier {


  public static Supplier<Nary<TypeInstance>> create(Executable nativeExecutable) {
    return NarySupplierFromCollection.lazilyBy(() -> {
      return Diamond.types().from(nativeExecutable.getAnnotatedExceptionTypes())
        .collect(Collectors.toList());
    });
  }
}
