package ar.com.kfgodel.diamond.impl.types.names;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.names.TypeNamesDescription;
import ar.com.kfgodel.diamond.impl.types.declaration.TypeDeclaration;

/**
 * This class serves as a generic {@link TypeNames} implementation
 * Date: 21/11/19 - 22:54
 */
public class TypeInstanceNames implements TypeNames {

  private String shortName;
  private String commonName;
  private String canonicalName;
  private String typeName;
  private String bareName;
  private TypeDeclaration typeDeclaration;

  @Override
  public String shortName() {
    return shortName;
  }

  @Override
  public String commonName() {
    return commonName;
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

  @Override
  public String completeName() {
    return typeDeclaration.asString();
  }

  public static TypeInstanceNames create(TypeInstance type, TypeNamesDescription description) {
    TypeInstanceNames names = new TypeInstanceNames();
    names.bareName = description.bareName();
    names.shortName = description.shortName()
      .orElseGet(description::bareName);
    names.typeName = description.typeName();
    names.commonName = description.commonName()
      .orElseGet(description::typeName);
    names.canonicalName = description.canonicalName()
      .orElseGet(description::typeName);
    names.typeDeclaration = TypeDeclaration.create(type);
    return names;
  }

}
