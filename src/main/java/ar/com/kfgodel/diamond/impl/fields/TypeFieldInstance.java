package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
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
    private Supplier<PolymorphicInvokable> invoker;

    @Override
    public String name() {
        return fieldName.get();
    }

    @Override
    public TypeInstance type() {
        return fieldType.get();
    }

    @Override
    public <R> R getValueFrom(Object instance) {
        return (R) this.invoker.get().apply(instance);
    }

    @Override
    public void setValueOn(Object instance, Object value) {
        this.invoker.get().accept(instance, value);
    }

    @Override
    public Object get() {
        return this.invoker.get().get();
    }

    @Override
    public void accept(Object argument) {
        this.invoker.get().accept(argument);
    }

    @Override
    public void accept(Object instance, Object value) {
        this.invoker.get().accept(instance, value);
    }

    @Override
    public Object apply(Object instance) {
        return this.invoker.get().apply(instance);
    }


    @Override
    public Object invoke(Object... arguments) {
        return this.invoker.get().invoke(arguments);
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
        field.invoker = description.getInvoker();
        return field;
    }

}
