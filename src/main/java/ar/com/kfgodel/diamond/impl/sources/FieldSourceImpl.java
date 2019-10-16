package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.api.sources.FieldSources;
import ar.com.kfgodel.diamond.impl.fields.TypeFieldInstance;
import ar.com.kfgodel.diamond.impl.fields.description.FieldDescriptor;

import java.lang.reflect.Field;

/**
 * This type serves as the non fluent implementation of the ClassFieldSources
 * Created by kfgodel on 18/09/14.
 */
public class FieldSourceImpl implements FieldSources {

  private DiamondCache cache;

  public static FieldSourceImpl create(DiamondCache cache) {
    FieldSourceImpl source = new FieldSourceImpl();
    source.cache = cache;
    return source;
  }

  @Override
  public TypeFields in(Class<?> objectClass) {
    return Diamond.of(objectClass).fields();
  }

  @Override
  public TypeField from(Field nativeField) {
    return cache.reuseOrCreateRepresentationFor(nativeField, () -> fromDescription(FieldDescriptor.INSTANCE.describe(nativeField)));
  }

  @Override
  public TypeField fromDescription(FieldDescription fieldDescription) {
    return createFieldFrom(fieldDescription);
  }

  /**
   * Creates a new class field instance from a its description
   *
   * @param fieldDescription The description of the field features
   * @return The instance that represents a field
   */
  private TypeField createFieldFrom(FieldDescription fieldDescription) {
    return TypeFieldInstance.create(fieldDescription);
  }


}
