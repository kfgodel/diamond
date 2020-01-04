package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents the supplier of type without extended super type
 * Created by kfgodel on 28/09/14.
 */
public class NoExtendedTypeSupplier implements Supplier<Unary<TypeInstance>> {

  public static final NoExtendedTypeSupplier INSTANCE = new NoExtendedTypeSupplier();

  @Override
  public Unary<TypeInstance> get() {
    return Nary.empty();
  }

}
