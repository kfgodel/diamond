package ar.com.kfgodel.diamond.impl.named;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.naming.Named;

import java.util.function.Supplier;

/**
 * This type represents the supplier for an unnamed type
 * Created by kfgodel on 01/11/14.
 */
public class UndefinedName implements Supplier<String> {

  private Named unnamed;

  @Override
  public String get() {
    throw new DiamondException("The object doesn't have a name");
  }

  public static UndefinedName create(Named unnamed) {
    UndefinedName supplier = new UndefinedName();
    supplier.unnamed = unnamed;
    return supplier;
  }

}
