package ar.com.kfgodel.diamond.impl.types.description.names;

import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassTypeNameDescription implements TypeNamesDescription {

  private String typeName;
  private Class<?> nativeClass;

  @Override
  public Nary<String> shortName() {
    return Nary.of(nativeClass.getSimpleName());
  }

  @Override
  public Nary<String> commonName() {
    return Nary.of(nativeClass.getName());
  }

  @Override
  public Nary<String> canonicalName() {
    return Nary.of(nativeClass.getCanonicalName());
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
