package ar.com.kfgodel.diamond.impl.fields.bound;

import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.natives.invokables.InstanceArguments;

/**
 * This type represents a type field bound to an instance
 * Created by kfgodel on 16/11/14.
 */
public class BoundFieldInstance implements BoundField {

    private TypeField field;
    private Object instance;

    @Override
    public TypeField typeField() {
        return field;
    }

    @Override
    public Object instance() {
        return instance;
    }

    @Override
    public void set(Object value) {
        field.setValueOn(instance, value);
    }

    @Override
    public Object get() {
        return field.getValueFrom(instance);
    }

    @Override
    public Object invoke(Object... arguments) {
        Object[] joined = InstanceArguments.join(instance, arguments);
        return field.invoke(joined);
    }

    public static BoundFieldInstance create(TypeField field, Object instance) {
        BoundFieldInstance boundField = new BoundFieldInstance();
        boundField.field = field;
        boundField.instance = instance;
        return boundField;
    }

}
