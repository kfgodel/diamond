package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fields.equality.FieldEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;

import java.util.function.Supplier;

/**
 * This type represents a class field instance for a type
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldInstance extends TypeMemberSupport implements TypeField {

    private Supplier<String> fieldName;
    private Supplier<TypeInstance> fieldType;

    @Override
    public String name() {
        return fieldName.get();
    }

    @Override
    public TypeInstance type() {
        return fieldType.get();
    }

    @Override
    public boolean equals(Object obj) {
        return FieldEquality.INSTANCE.areEquals(this, obj);
    }

    public static TypeFieldInstance create(FieldDescription description) {
        TypeFieldInstance field = new TypeFieldInstance();
        field.fieldName = description.getName();
        field.fieldType = description.getType();
        field.setDeclaringType(description.getDeclaringType());
        field.setModifiers(description.getModifiers());
        return field;
    }

}
