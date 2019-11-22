package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

import java.lang.reflect.TypeVariable;
import java.util.Optional;

/**
 * This type represents the a type variable names
 * Created by kfgodel on 04/10/14.
 */
public class TypeVariableNamesDescription implements TypeNamesDescription {

  private TypeVariable<?> typeVariable;

  @Override
  public Optional<String> shortName() {
    return Optional.empty();
  }

  @Override
  public Optional<String> commonName() {
    return Optional.empty();
  }

  @Override
  public Optional<String> canonicalName() {
    return Optional.empty();
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
