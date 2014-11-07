package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Executable;
import java.util.function.Supplier;

/**
 * This type represents the generics supplier for
 * Created by kfgodel on 01/11/14.
 */
public class ExecutableGenericsSupplier{

    public static Generics create(Executable nativeExecutable) {
        Supplier<Nary<TypeInstance>> genericParametersSupplier = GenericTypeParametersSupplier.create(nativeExecutable);
        return ParameterizedMemberGenerics.create(genericParametersSupplier);
    }

}
