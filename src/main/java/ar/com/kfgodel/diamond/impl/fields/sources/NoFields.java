package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the fields of a type that has no fields
 * Created by kfgodel on 12/10/14.
 */
public class NoFields implements TypeFields {

  public static final NoFields INSTANCE = new NoFields();

  @Override
  public Nary<TypeField> all() {
    return Nary.empty();
  }

  @Override
  public Nary<TypeField> named(String fieldName) {
    return Nary.empty();
  }

}
