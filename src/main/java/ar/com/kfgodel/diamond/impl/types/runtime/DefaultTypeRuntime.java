package ar.com.kfgodel.diamond.impl.types.runtime;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.runtime.TypeRuntime;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This class is the default implementation for a type runtime information
 * Date: 8/12/19 - 20:42
 */
public class DefaultTypeRuntime implements TypeRuntime {

  private TypeInstance type;
  private Supplier<Nary<Class<?>>> classes;

  @Override
  public Nary<Class<?>> classes() {
    return classes.get();
  }

  public static DefaultTypeRuntime create(TypeInstance type, Supplier<Nary<Class<?>>> classes) {
    DefaultTypeRuntime runtime = new DefaultTypeRuntime();
    runtime.type = type;
    runtime.classes = classes;
    return runtime;
  }

}
