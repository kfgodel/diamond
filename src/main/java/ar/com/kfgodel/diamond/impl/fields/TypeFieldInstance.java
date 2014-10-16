package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

/**
 * This type represents a class field instance for a type
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldInstance implements TypeField {

    private LazyValue<String> fieldName;
    private LazyValue<TypeInstance> fieldType;

    @Override
    public String name() {
        return fieldName.get();
    }

    @Override
    public TypeInstance type() {
        return fieldType.get();
    }

    public static TypeFieldInstance create(FieldDescription description) {
        TypeFieldInstance classField = new TypeFieldInstance();
        classField.fieldName = SuppliedValue.create(description.getName());
        classField.fieldType = SuppliedValue.create(description.getType());
        return classField;
    }

}
