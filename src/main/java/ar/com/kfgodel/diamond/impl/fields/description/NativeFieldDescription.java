package ar.com.kfgodel.diamond.impl.fields.description;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;

import java.lang.reflect.Field;
import java.util.function.Supplier;

/**
 * This type represents a field description of a native field instance
 * Created by kfgodel on 12/10/14.
 */
public class NativeFieldDescription implements FieldDescription {

    private Field nativeField;

    public static NativeFieldDescription create(Field nativeField) {
        NativeFieldDescription description = new NativeFieldDescription();
        description.nativeField = nativeField;
        return description;
    }

    @Override
    public Supplier<String> getName() {
        return nativeField::getName;
    }
}
