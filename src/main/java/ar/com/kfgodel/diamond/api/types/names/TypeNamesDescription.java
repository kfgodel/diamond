package ar.com.kfgodel.diamond.api.types.names;

/**
 * This type represents a description of the names for a {@link ar.com.kfgodel.diamond.api.types.TypeInstance}
 * based on this description a type instance can define its names
 *
 * Date: 21/11/19 - 21:55
 */
public interface TypeNamesDescription {

  String shortName();
  String commonName();
  String canonicalName();
  String typeName();
  String bareName();

}
