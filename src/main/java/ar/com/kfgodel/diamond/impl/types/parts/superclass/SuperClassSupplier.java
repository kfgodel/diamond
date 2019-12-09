package ar.com.kfgodel.diamond.impl.types.parts.superclass;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a fragment of code that can extract the native superclass of a native class
 * Created by kfgodel on 21/09/14.
 */
public class SuperClassSupplier implements Supplier<Nary<TypeInstance>> {

  private Supplier<Nary<TypeInstance>> superclass;

  @Override
  public Nary<TypeInstance> get() {
    return superclass.get();
  }

  public static Supplier<Nary<TypeInstance>> create(Class<?> nativeClass) {
    SuperClassSupplier supplier = new SuperClassSupplier();
    supplier.superclass = () -> {
      Class<?> superclass = nativeClass.getSuperclass();
      return Nary.of(superclass)
        .map(Diamond::of);
    };
    return supplier;
  }

}
