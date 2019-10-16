package ar.com.kfgodel.diamond.impl.types.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNames;

/**
 * This type represents the names of a wildcard type
 * Created by kfgodel on 04/10/14.
 */
public class WildCardNames implements TypeNames {

  private String typeName;

  @Override
  public String shortName() {
    return "?";
  }

  @Override
  public String classloaderName() {
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

  public static WildCardNames create(String typeName) {
    WildCardNames names = new WildCardNames();
    names.typeName = typeName;
    return names;
  }

}
