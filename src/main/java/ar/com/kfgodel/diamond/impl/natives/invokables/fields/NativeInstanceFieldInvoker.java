package ar.com.kfgodel.diamond.impl.natives.invokables.fields;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * This type represents the native field invoker for instance fields
 * Created by kfgodel on 30/10/14.
 */
public class NativeInstanceFieldInvoker extends NativeFieldInvokerSupport{

    @Override
    public Object invoke(Object... arguments) {
        if(arguments.length == 1){
            return getValueFrom(arguments[0]);
        }else if (arguments.length == 2){
            setValueOn(arguments[0], arguments[1]);
        }else{
            throw new DiamondException("This field["+this.getNativeField()+"] only accepts 1 or 2 arguments: " + Arrays.toString(arguments));
        }
        return null;
    }

    @Override
    public Object get() {
        throw new DiamondException("Get for instance field["+getNativeField()+"] cannot be done without an instance");
    }

    @Override
    public Object apply(Object argument) {
        // Called as a function to get the value of an instance
        return getValueFrom(argument);
    }

    @Override
    public void accept(Object consumedElement) {
        throw new DiamondException("Set for instance field["+getNativeField()+"] cannot be done without an instance");
    }

    @Override
    public void accept(Object instance, Object newValue) {
        // Called as biConsumer to set the value of this field in the instance
        this.setValueOn(instance, newValue);
    }


    public static NativeInstanceFieldInvoker create(Field nativeField) {
        NativeInstanceFieldInvoker invoker = new NativeInstanceFieldInvoker();
        invoker.setNativeField(nativeField);
        return invoker;
    }
}
