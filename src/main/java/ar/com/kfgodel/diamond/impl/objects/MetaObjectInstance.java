package ar.com.kfgodel.diamond.impl.objects;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.BoundFields;
import ar.com.kfgodel.diamond.api.methods.BoundMethods;
import ar.com.kfgodel.diamond.api.objects.MetaObject;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.fields.bound.BoundFieldsImpl;
import ar.com.kfgodel.diamond.impl.methods.bound.BoundMethodsImpl;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;

/**
 * This type represents a meta-level view of an object
 * Created by kfgodel on 17/11/14.
 */
public class MetaObjectInstance implements MetaObject {

    private Object instance;
    private TypeInstance instanceType;
    private BoundFields fields;
    private BoundMethods methods;

    @Override
    public TypeInstance type() {
        if (instanceType == null) {
            instanceType = Diamond.of(instance.getClass());
        }
        return instanceType;
    }

    @Override
    public BoundMethods methods() {
        if (methods == null) {
            methods = BoundMethodsImpl.create(type().methods(), instance);
        }
        return methods;
    }

    @Override
    public BoundFields fields() {
        if (fields == null) {
            fields = BoundFieldsImpl.create(type().fields(), instance);
        }
        return fields;
    }

    @Override
    public Object instance() {
        return instance;
    }


    @Override
    public int hashCode() {
        return instance.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return instance.equals(obj);
    }

    @Override
    public String toString() {
        return DebugPrinter.print(this);
    }

    public static MetaObjectInstance create(Object instance) {
        MetaObjectInstance metaObject = new MetaObjectInstance();
        metaObject.instance = instance;
        return metaObject;
    }

}
