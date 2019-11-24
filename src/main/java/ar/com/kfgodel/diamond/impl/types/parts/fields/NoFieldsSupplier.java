package ar.com.kfgodel.diamond.impl.types.parts.fields;

import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a field supplier for a type with no fields
 * Date: 24/11/19 - 02:51
 */
public class NoFieldsSupplier implements Supplier<Nary<TypeField>>{
  public static final Supplier<Nary<TypeField>> INSTANCE = create();

  @Override
  public Nary<TypeField> get() {
    return Nary.empty();
  }

  public static NoFieldsSupplier create() {
    NoFieldsSupplier supplier = new NoFieldsSupplier();
    return supplier;
  }

}
