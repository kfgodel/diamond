package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

import java.lang.reflect.WildcardType;

/**
 * This type represents the names of a wildcard type
 * Created by kfgodel on 04/10/14.
 */
public class WildCardNamesDescription implements TypeNamesDescription {

  private WildcardType wildcardType;

  @Override
  public String shortName() {
    return bareName();
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
    return wildcardType.getTypeName();
  }

  @Override
  public String bareName() {
    return "?";
  }

  public static WildCardNamesDescription create(WildcardType type) {
    WildCardNamesDescription names = new WildCardNamesDescription();
    names.wildcardType = type;
    return names;
  }

}
