package ar.com.kfgodel.diamond.impl.fields.bound;

import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.impl.members.bound.BoundMemberSupport;
import ar.com.kfgodel.diamond.impl.natives.invokables.InstanceArguments;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;

/**
 * This type represents a type field bound to an instance
 * Created by kfgodel on 16/11/14.
 */
public class BoundFieldInstance extends BoundMemberSupport implements BoundField {

    private TypeField field;

    @Override
    public TypeField typeField() {
        return field;
    }

    @Override
    public TypeMember typeMember() {
        return typeField();
    }

    @Override
    public void set(Object value) {
        field.setValueOn(instance(), value);
    }

    @Override
    public Object get() {
        return field.getValueFrom(instance());
    }

    @Override
    public Object invoke(Object... arguments) {
        Object[] joined = InstanceArguments.join(instance(), arguments);
        return field.invoke(joined);
    }

    public static BoundFieldInstance create(TypeField field, Object instance) {
        BoundFieldInstance boundField = new BoundFieldInstance();
        boundField.setInstance(instance);
        boundField.field = field;
        return boundField;
    }

    @Override
    public String toString() {
        return DebugPrinter.print(this);
    }
}
