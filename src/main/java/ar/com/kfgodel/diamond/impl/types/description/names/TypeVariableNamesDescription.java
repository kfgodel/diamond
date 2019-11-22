package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

/**
 * This type represents the a type variable names
 * Created by kfgodel on 04/10/14.
 */
public class TypeVariableNamesDescription implements TypeNamesDescription {

  private String variableName;
  private String typeName;

  @Override
  public String shortName() {
    return variableName;
  }

  @Override
  public String commonName() {
    return typeName();
  }

  @Override
  public String canonicalName() {
    return typeName();
  }

  @Override
  public String typeName() {
    return typeName;
  }

  @Override
  public String bareName() {
    return variableName;
  }

  public static TypeVariableNamesDescription create(String variableName, String typeName) {
    TypeVariableNamesDescription names = new TypeVariableNamesDescription();
    names.variableName = variableName;
    names.typeName = typeName;
    return names;
  }

}
