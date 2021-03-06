package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

/**
 * This type describes a type that has no name
 * Date: 24/11/19 - 03:00
 */
public class UnnamedTypeDescription implements TypeNamesDescription {

  private String replacementForName;

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
    return replacementForName;
  }

  @Override
  public String bareName() {
    return replacementForName;
  }

  public static UnnamedTypeDescription create(String replacementForName) {
    UnnamedTypeDescription description = new UnnamedTypeDescription();
    description.replacementForName = replacementForName;
    return description;
  }

}
