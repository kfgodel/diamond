package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.TypeVariable;

/**
 * This type represents the a type variable names
 * Created by kfgodel on 04/10/14.
 */
public class TypeVariableNamesDescription implements TypeNamesDescription {

  private TypeVariable<?> typeVariable;

  @Override
  public Unary<String> shortName() {
    return Nary.empty();
  }

  @Override
  public Unary<String> commonName() {
    return Nary.empty();
  }

  @Override
  public Unary<String> canonicalName() {
    return Nary.empty();
  }

  @Override
  public String typeName() {
    return typeVariable.getTypeName();
  }

  @Override
  public String bareName() {
    return typeVariable.getName();
  }

  public static TypeVariableNamesDescription create(TypeVariable<?> typeVariable) {
    TypeVariableNamesDescription description = new TypeVariableNamesDescription();
    description.typeVariable = typeVariable;
    return description;
  }

}
