package ar.com.kfgodel.diamond.impl.fields.bound;

import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.BoundFields;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.impl.members.bound.BoundMembersSupport;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents the source of bound fields for a meta-object
 * Created by kfgodel on 17/11/14.
 */
public class BoundFieldsImpl extends BoundMembersSupport implements BoundFields {

    private TypeFields fieldSource;

    public static BoundFieldsImpl create(TypeFields fieldSource, Object instance) {
        BoundFieldsImpl fields = new BoundFieldsImpl();
        fields.setBindInstance(instance);
        fields.fieldSource = fieldSource;
        return fields;
    }

    @Override
    public Nary<BoundField> all() {
        return boundVersionOf(fieldSource.all());
    }

    @Override
    public Nary<BoundField> named(String fieldName) {
        return boundVersionOf(fieldSource.named(fieldName));
    }
}
