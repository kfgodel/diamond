package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.typeparameters.GenericTypeParametersSupplier;

import java.lang.reflect.Executable;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the generics supplier for
 * Created by kfgodel on 01/11/14.
 */
public class ExecutableGenericsSupplier{

    public static Generics create(Executable nativeExecutable) {
        Supplier<Stream<TypeInstance>> genericParametersSupplier = GenericTypeParametersSupplier.create(nativeExecutable);
        return ParameterizedMemberGenerics.create(genericParametersSupplier);
    }

}
