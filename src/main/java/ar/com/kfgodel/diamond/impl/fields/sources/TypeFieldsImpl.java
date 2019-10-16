package ar.com.kfgodel.diamond.impl.fields.sources;

import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.impl.named.NamedSourceSupport;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the set of fields for a given type stored as immutable
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldsImpl extends NamedSourceSupport<TypeField> implements TypeFields {

  private Supplier<Nary<TypeField>> typeFields;

  @Override
  public Nary<TypeField> all() {
    return typeFields.get();
  }

  @Override
  protected Nary<TypeField> getAll() {
    return all();
  }

  public static TypeFieldsImpl create(Supplier<Nary<TypeField>> typeFields) {
    TypeFieldsImpl fieldSource = new TypeFieldsImpl();
    fieldSource.typeFields = typeFields;
    return fieldSource;
  }

}
