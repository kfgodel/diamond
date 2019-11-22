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

  private TypeInstance type;
  private TypeNamesDescription description;

  @Override
  public String shortName() {
    return description.shortName()
      .orElseGet(this::bareName);
  }

  @Override
  public String commonName() {
    return description.commonName()
      .orElseGet(this::typeName);
  }

  @Override
  public String canonicalName() {
    return description.canonicalName()
      .orElseGet(this::typeName);
  }

  @Override
  public String typeName() {
    return description.typeName();
  }

  @Override
  public String bareName() {
    return description.bareName();
  }

  @Override
  public String completeName() {
    return TypeDeclaration.create(type).asString();
  }

  public static TypeInstanceNames create(TypeInstance type, TypeNamesDescription nameDescription) {
    TypeInstanceNames names = new TypeInstanceNames();
    names.type = type;
    names.description = nameDescription;
    return names;
  }

}
