package ar.com.kfgodel.diamond.impl.methods.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.executables.FilterByParameterType;
import ar.com.kfgodel.diamond.impl.named.NamedSourceSupport;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.streams.StreamEquality;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the set of methods for a type stored as an immutable set
 * Created by kfgodel on 19/09/14.
 */
public class TypeMethodsImpl extends NamedSourceSupport<TypeMethod> implements TypeMethods {

    private Supplier<Nary<TypeMethod>> typeMethods;

    @Override
    public Nary<TypeMethod> all() {
        return typeMethods.get();
    }

    @Override
    public Nary<TypeMethod> withSignature(String methodName, TypeInstance... parameterTypes) {
        return Nary.create(named(methodName).filter((method)-> StreamEquality.INSTANCE.areEquals(method.parameterTypes(), Arrays.stream(parameterTypes))));
    }

    @Override
    public Nary<TypeMethod> withNativeSignature(String methodName, Type... parameterTypes) {
        return withSignature(methodName, Diamond.ofNative(parameterTypes));
    }

    @Override
    public Nary<TypeMethod> withParameters(TypeInstance... paramTypes) {
        return FilterByParameterType.create(all(), paramTypes);
    }

    @Override
    public Nary<TypeMethod> withNativeParameters(Type... parameterTypes) {
        return withParameters(Diamond.ofNative(parameterTypes));
    }

    @Override
    protected Stream<TypeMethod> getAll() {
        return all();
    }

    public static TypeMethodsImpl create(Supplier<Nary<TypeMethod>> classMethods) {
        TypeMethodsImpl methodSource = new TypeMethodsImpl();
        methodSource.typeMethods = classMethods;
        return methodSource;
    }


}
