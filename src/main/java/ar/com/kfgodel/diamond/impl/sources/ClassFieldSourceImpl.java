package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.sources.fields.ClassDefinedClassFieldSource;
import ar.com.kfgodel.diamond.api.sources.fields.ClassFieldSources;
import ar.com.kfgodel.diamond.impl.fields.ClassFieldInstance;
import ar.com.kfgodel.diamond.impl.fields.description.FieldDescriptor;

import java.lang.reflect.Field;

/**
 * This type serves as the non fluent implementation of the ClassFieldSources
 * Created by kfgodel on 18/09/14.
 */
public class ClassFieldSourceImpl implements ClassFieldSources {

    public static ClassFieldSourceImpl create() {
        ClassFieldSourceImpl classFieldSource = new ClassFieldSourceImpl();
        return classFieldSource;
    }

    @Override
    public ClassDefinedClassFieldSource in(Class<?> nativeClass) {
        return null;
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
