package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

import java.util.Optional;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassTypeNameDescription implements TypeNamesDescription {

  private String typeName;
  private Class<?> nativeClass;

  @Override
  public Optional<String> shortName() {
    return Optional.ofNullable(nativeClass.getSimpleName());
  }

  @Override
  public Optional<String> commonName() {
    return Optional.ofNullable(nativeClass.getName());
  }

  @Override
  public Optional<String> canonicalName() {
    return Optional.ofNullable(nativeClass.getCanonicalName());
  }

  @Override
  public String typeName() {
    return typeName;
  }

  @Override
  public String bareName() {
    if (nativeClass.getComponentType() != null) {
      //It's an array
      return "[]";
    }
    return nativeClass.getCanonicalName();
  }

  public static ClassTypeNameDescription create(Class<?> nativeClass, String typeName) {
    ClassTypeNameDescription description = new ClassTypeNameDescription();
    description.nativeClass = nativeClass;
    description.typeName = typeName;
    return description;
  }

}
