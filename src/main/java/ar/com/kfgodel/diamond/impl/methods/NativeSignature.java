package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.methods.MethodSignature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This type represents a method signature based on native types
 * Created by kfgodel on 19/09/14.
 */
public class NativeSignature implements MethodSignature{

    private String methodName;

    private List<Class<?>> parameterTypes;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MethodSignature)){
            return false;
        }
        MethodSignature other = (MethodSignature) obj;
        return this.name().equals(other.name()) && this.parameterTypes().equals(other.parameterTypes());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.methodName);
        builder.append("(");
        builder.append(this.parameterTypes);
        builder.append(")");
        return builder.toString();
    }

    public static NativeSignature create(String methodName, Class<?>... parameterTypes) {
        NativeSignature signature = new NativeSignature();
        signature.methodName = methodName;
        signature.parameterTypes = new ArrayList<>(Arrays.asList(parameterTypes));
        return signature;
    }

    @Override
    public String name() {
        return methodName;
    }

    @Override
    public List<Class<?>> parameterTypes() {
        return parameterTypes;
    }
}
