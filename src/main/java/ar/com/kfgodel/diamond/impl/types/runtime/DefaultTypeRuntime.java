package ar.com.kfgodel.diamond.impl.types.runtime;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.api.types.runtime.RuntimeTypeHierarchy;
import ar.com.kfgodel.diamond.api.types.runtime.TypeRuntime;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This class is the default implementation for a type runtime information
 * Date: 8/12/19 - 20:42
 */
public class DefaultTypeRuntime implements TypeRuntime {

  private Supplier<Nary<Class<?>>> classes;
  private Supplier<Unary<TypeInstance>> runtimeType;
  private RuntimeTypeHierarchy hierarchy;

  @Override
  public Nary<Class<?>> classes() {
    return classes.get();
  }

  @Override
  public TypeInstance type() {
    return runtimeType.get()
      .orElseThrow(()-> new DiamondException("Runtime type is not available")); //Better error?
  }

  @Override
  public RuntimeTypeHierarchy hierarchy() {
    return hierarchy;
  }

  public static DefaultTypeRuntime create(TypeInstance type,
                                          Supplier<Nary<Class<?>>> classes,
                                          Supplier<Unary<TypeInstance>> runtimeType,
                                          InheritanceDescription inheritance) {
    DefaultTypeRuntime runtime = new DefaultTypeRuntime();
    runtime.classes = classes;
    runtime.runtimeType = runtimeType;
    runtime.hierarchy = DefaultRuntimeHierarchy.create(type, inheritance);
    return runtime;
  }

}
