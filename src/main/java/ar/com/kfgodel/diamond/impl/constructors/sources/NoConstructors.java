package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

/**
 * This type represents the source of constructors for a type that has no constructors
 * Created by kfgodel on 15/10/14.
 */
public class NoConstructors implements TypeConstructors {

    public static final NoConstructors INSTANCE = new NoConstructors();

    @Override
    public Nary<TypeConstructor> all() {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeConstructor> niladic() {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeConstructor> withParameters(TypeInstance... paramTypes) {
        return NaryFromNative.empty();
    }

}
