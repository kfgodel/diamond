package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
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

    public static FieldSourceImpl create() {
        FieldSourceImpl classFieldSource = new FieldSourceImpl();
        return classFieldSource;
    }

    @Override
    public TypeFields in(Class<?> objectClass) {
        return Diamond.of(objectClass).fields();
    }

    @Override
    public TypeField from(Field fieldInstance) {
        FieldDescription fieldDescription = FieldDescriptor.INSTANCE.describe(fieldInstance);
        return from(fieldDescription);
    }

    @Override
    public TypeField from(FieldDescription fieldDescription) {
        // This is the place to cache instances
        return createFieldFrom(fieldDescription);
    }

    /**
     * Creates a new class field instance from a its description
     * @param fieldDescription The description of the field features
     * @return The instance that represents a field
     */
    private TypeField createFieldFrom(FieldDescription fieldDescription) {
        return TypeFieldInstance.create(fieldDescription);
    }


}
