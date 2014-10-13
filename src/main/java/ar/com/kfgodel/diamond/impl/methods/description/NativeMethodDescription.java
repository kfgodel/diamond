package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a the method description of a native method
 * Created by kfgodel on 07/10/14.
 */
public class NativeMethodDescription implements MethodDescription {

    private Method nativeMethod;

    @Override
    public Supplier<String> getName() {
        return nativeMethod::getName;
    }

    @Override
    public Supplier<TypeInstance> getReturnType() {
        return () -> Diamond.types().from(nativeMethod.getAnnotatedReturnType());
    }

    @Override
    public Supplier<Stream<TypeInstance>> getParameterTypes() {
        return ()-> Arrays.stream(nativeMethod.getAnnotatedParameterTypes())
                    .map((annotated) -> Diamond.types().from(annotated));
    }

    public static NativeMethodDescription create(Method nativeMethod) {
        NativeMethodDescription description = new NativeMethodDescription();
        description.nativeMethod = nativeMethod;
        return description;
    }


}
