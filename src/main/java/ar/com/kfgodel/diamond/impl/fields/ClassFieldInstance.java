package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.fields.ClassField;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

/**
 * This type represents a class field instance for a type
 * Created by kfgodel on 12/10/14.
 */
public class ClassFieldInstance implements ClassField {

    private LazyValue<String> fieldName;

    @Override
    public String name() {
        return fieldName.get();
    }

    public static ClassFieldInstance create(FieldDescription description) {
        ClassFieldInstance classField = new ClassFieldInstance();
        classField.fieldName = SuppliedValue.create(description.getName());
        return classField;
    }

}
