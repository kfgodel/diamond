package ar.com.kfgodel.diamond.impl.members.exceptions;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Executable;
import java.util.function.Supplier;

/**
 * This type represents the exceptions supplier for executable native types
 * Created by kfgodel on 02/11/14.
 */
public class ExecutableExceptionsSupplier {


  public static Supplier<Nary<TypeInstance>> create(Executable nativeExecutable) {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(nativeExecutable.getAnnotatedExceptionTypes());
    });
  }
}
