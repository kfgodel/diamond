package ar.com.kfgodel.diamond.impl.fields;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.sources.modifiers.Mutability;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fields.equality.FieldEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a class field instance for a type
 * Created by kfgodel on 12/10/14.
 */
public class TypeFieldInstance extends TypeMemberSupport implements TypeField {

    private Supplier<String> fieldName;
    private Supplier<TypeInstance> fieldType;
    private Supplier<Function<Object,?>> getter;
    private Supplier<BiConsumer<Object, Object>> setter;

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
        return (R) getter.get().apply(instance);
    }

    @Override
    public void setValueOn(Object instance, Object value) {
        setter.get().accept(instance, value);
    }

    @Override
    public Object get() {
        return getValueFrom(null);
    }

    @Override
    public void accept(Object argument) {
        setValueOn(null, argument);
    }

    @Override
    public void accept(Object instance, Object value) {
        this.setValueOn(instance, value);
    }

    @Override
    public Object apply(Object instance) {
        return this.getValueFrom(instance);
    }


    @Override
    public Object invoke(Object... arguments) {
        if(isStatic()){
            if(arguments.length == 0){
                return getValueFrom(null);
            } else if(arguments.length == 1){
                setValueOn(null, arguments[0]);
            } else{
                throw new DiamondException("This field["+this+"] only accepts 0 or 1 argument: " + Arrays.toString(arguments));
            }
        }else{
            if(arguments.length == 1){
                return getValueFrom(arguments[0]);
            }else if(arguments.length == 2){
                setValueOn(arguments[0], arguments[1]);
            }else{
                throw new DiamondException("This field["+this+"] only accepts 1 or 2 arguments: " + Arrays.toString(arguments));
            }
        }
        return null;
    }

    /**
     * Indicates if this instance represents a static field
     * @return true if this field doesn't require an instance to be read/write
     */
    private boolean isStatic(){
        return modifiers().anyMatch(Mutability.STATIC);
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
        field.setter = description.getSetter();
        field.getter = description.getGetter();
        return field;
    }

}
