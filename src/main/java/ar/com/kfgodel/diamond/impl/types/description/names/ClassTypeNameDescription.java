package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassTypeNameDescription implements TypeNamesDescription {

  private String shortName;
  private String standardName;
  private String canonicalName;
  private String typeName;
  private String bareName;

  @Override
  public String shortName() {
    return shortName;
  }

  @Override
  public String commonName() {
    return standardName;
  }

  @Override
  public String canonicalName() {
    return canonicalName;
  }

  @Override
  public String typeName() {
    return typeName;
  }

  @Override
  public String bareName() {
    return bareName;
  }

  public static ClassTypeNameDescription create(Class<?> nativeClass, String typeName) {
    ClassTypeNameDescription classTypeNames = new ClassTypeNameDescription();
    classTypeNames.shortName = nativeClass.getSimpleName();
    classTypeNames.standardName = nativeClass.getName();
    classTypeNames.canonicalName = nativeClass.getCanonicalName();
    classTypeNames.typeName = typeName;
    if (nativeClass.getComponentType() != null) {
      //It's an array
      classTypeNames.bareName = "[]";
    } else {
      classTypeNames.bareName = nativeClass.getCanonicalName();
    }
    return classTypeNames;
  }

}
