package ar.com.kfgodel.diamond.impl.types.parts.typeparameters;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.GenericDeclaration;
import java.util.function.Supplier;

/**
 * This type represents a fragment of code that allows you to get the type parameters of a class
 * Created by kfgodel on 25/09/14.
 */
public class GenericTypeParametersSupplier {

  public static Supplier<Nary<TypeInstance>> create(GenericDeclaration genericDeclaration) {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(genericDeclaration.getTypeParameters());
    });
  }

}
