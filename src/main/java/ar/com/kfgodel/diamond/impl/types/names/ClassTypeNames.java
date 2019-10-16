package ar.com.kfgodel.diamond.impl.types.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNames;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassTypeNames implements TypeNames {

  private String shortName;
  private String classloaderName;
  private String canonicalName;
  private String typeName;
  private String bareName;

  @Override
  public String shortName() {
    return shortName;
  }

  @Override
  public String classloaderName() {
    return classloaderName;
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

  public static ClassTypeNames create(Class<?> nativeClass, String typeName) {
    ClassTypeNames classTypeNames = new ClassTypeNames();
    classTypeNames.shortName = nativeClass.getSimpleName();
    classTypeNames.classloaderName = nativeClass.getName();
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
