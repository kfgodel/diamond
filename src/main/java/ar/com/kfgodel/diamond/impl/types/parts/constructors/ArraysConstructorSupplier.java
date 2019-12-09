package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.constructors.description.ArrayConstructorDescription;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromElement;

import java.util.function.Supplier;

/**
 * This type represents the artificial constructor supplier for native array types.<br>
 * Arrays don't have a constructor method but they can be instantiated with an int argument using native reflection
 * helpers. This supplier fills the gap to simulate a constructor for arrays
 * Created by kfgodel on 16/10/14.
 */
public class ArraysConstructorSupplier {

  public static Supplier<Nary<TypeConstructor>> create(Class<?> nativeArrayClass) {
    return NarySupplierFromElement.using(CachedValue.from(() -> {
      ConstructorDescription arrayConstructorDescription = ArrayConstructorDescription.create(nativeArrayClass);
      TypeConstructor constructor = Diamond.constructors().fromDescription(arrayConstructorDescription);
      return constructor;
    }));
  }

}
