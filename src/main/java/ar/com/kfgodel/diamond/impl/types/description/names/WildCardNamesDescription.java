package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

import java.lang.reflect.WildcardType;
import java.util.Optional;

/**
 * This type represents the names of a wildcard type
 * Created by kfgodel on 04/10/14.
 */
public class WildCardNamesDescription implements TypeNamesDescription {

  private WildcardType wildcardType;

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
