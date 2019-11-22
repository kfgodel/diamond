package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

/**
 * This type represents the names of a wildcard type
 * Created by kfgodel on 04/10/14.
 */
public class WildCardNamesDescription implements TypeNamesDescription {

  private String typeName;

  @Override
  public String shortName() {
    return "?";
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
    return shortName();
  }

  public static WildCardNamesDescription create(String typeName) {
    WildCardNamesDescription names = new WildCardNamesDescription();
    names.typeName = typeName;
    return names;
  }

}
