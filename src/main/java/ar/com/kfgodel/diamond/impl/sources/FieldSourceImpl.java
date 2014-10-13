package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.sources.fields.FieldSources;
import ar.com.kfgodel.diamond.impl.fields.ClassFieldInstance;
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
    public ClassField from(Field fieldInstance) {
        FieldDescription fieldDescription = FieldDescriptor.INSTANCE.describe(fieldInstance);
        return from(fieldDescription);
    }

    @Override
    public ClassField from(FieldDescription fieldDescription) {
        // This is the place to cache instances
        return createFieldFrom(fieldDescription);
    }

    /**
     * Creates a new class field instance from a its description
     * @param fieldDescription The description of the field features
     * @return The instance that represents a field
     */
    private ClassField createFieldFrom(FieldDescription fieldDescription) {
        return ClassFieldInstance.create(fieldDescription);
    }


}
