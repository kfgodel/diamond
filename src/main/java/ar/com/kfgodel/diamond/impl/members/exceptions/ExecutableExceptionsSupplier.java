package ar.com.kfgodel.diamond.impl.members.exceptions;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.streams.TypeStreamSupplierFromNativeTypeArray;

import java.lang.reflect.Executable;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the exceptions supplier for executable native types
 * Created by kfgodel on 02/11/14.
 */
public class ExecutableExceptionsSupplier {


    public static Supplier<Stream<TypeInstance>> create(Executable nativeExecutable) {
        return TypeStreamSupplierFromNativeTypeArray.apply(nativeExecutable::getAnnotatedExceptionTypes);
    }
}
