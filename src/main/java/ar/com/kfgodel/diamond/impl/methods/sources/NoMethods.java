package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

/**
 * This type represents a source for a type that has no methods
 * Created by kfgodel on 30/09/14.
 */
public class NoMethods implements TypeMethods {

    public static final NoMethods INSTANCE = new NoMethods();

    @Override
    public Nary<TypeMethod> all() {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeMethod> named(String methodName) {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeMethod> withSignature(String methodName, TypeInstance... parameterTypes) {
        return NaryFromNative.empty();
    }

}
