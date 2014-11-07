package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.streams.TypeStreamSupplierFromNativeTypeArray;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Executable;
import java.util.function.Supplier;

/**
 * This type represents the immutable parameters list supplier for Executable instances
 * Created by kfgodel on 01/11/14.
 */
public class ImmutableMemberParameters {

    public static Supplier<Nary<TypeInstance>> create(Executable nativeExecutable) {
        return TypeStreamSupplierFromNativeTypeArray.apply(nativeExecutable::getAnnotatedParameterTypes);
    }

}
