package ar.com.kfgodel.diamond.impl.fields.description;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;

import java.lang.reflect.Field;

/**
 * This type represents a field descriptro that takes native fields and describes them in diamond terms
 * Created by kfgodel on 12/10/14.
 */
public class FieldDescriptor {

    public static final FieldDescriptor INSTANCE = FieldDescriptor.create();

    /**
     * Creates a description of the given native field based on its features
     * @param nativeField The field to describe
     * @return The description for diamond
     */
    public FieldDescription describe(Field nativeField){
        return NativeFieldDescription.create(nativeField);
    }

    public static FieldDescriptor create() {
        FieldDescriptor descriptor = new FieldDescriptor();
        return descriptor;
    }

}
