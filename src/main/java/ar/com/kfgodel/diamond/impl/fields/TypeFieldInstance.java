package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fields.declaration.FieldDeclaration;
import ar.com.kfgodel.diamond.impl.fields.equality.FieldEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a class field instance for a type
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldInstance extends TypeMemberSupport implements TypeField {

    private Supplier<TypeInstance> fieldType;

    @Override
    public TypeInstance type() {
        return fieldType.get();
    }

    @Override
    public <R> R getValueFrom(Object instance) {
        return (R) asFunction().apply(instance);
    }

    @Override
    public void setValueOn(Object instance, Object value) {
        asFunction().accept(instance, value);
    }

    @Override
    public Object get() {
        return asFunction().get();
    }

    @Override
    public void accept(Object argument) {
        this.asFunction().accept(argument);
    }

    @Override
    public void accept(Object instance, Object value) {
        asFunction().accept(instance, value);
    }

    @Override
    public Object apply(Object instance) {
        return asFunction().apply(instance);
    }

    @Override
    public Object invoke(Object... arguments) {
        return asFunction().invoke(arguments);
    }

    @Override
    public boolean equals(Object obj) {
        return FieldEquality.INSTANCE.areEquals(this, obj);
    }

    public static TypeFieldInstance create(FieldDescription description) {
        TypeFieldInstance field = new TypeFieldInstance();
        field.initialize(description);
        field.fieldType = description.getType();
        return field;
    }

    @Override
    public String declaration() {
        return FieldDeclaration.create(this).asString();
    }

    @Override
    public Stream<TypeInstance> parameterTypes() {
        return Stream.empty();
    }
}
