package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.methods.MethodDescription;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * This type represents a method decription with references to the parts suppliers
 * Created by kfgodel on 07/10/14.
 */
public class NativeMethodDescription implements MethodDescription {

    private Method nativeMethod;

    @Override
    public Supplier<String> getName() {
        return nativeMethod::getName;
    }

    public static NativeMethodDescription create(Method nativeMethod) {
        NativeMethodDescription description = new NativeMethodDescription();
        description.nativeMethod = nativeMethod;
        return description;
    }


}
