package ar.com.kfgodel.diamond.impl.lambdas;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.lambdas.LambdaDescription;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type implements the lambda behavior of a diamond lambda reification
 * Created by kfgodel on 02/02/15.
 */
public class LambdaInstance implements Lambda {
    
    private Supplier<PolymorphicInvokable> function;
    private Supplier<Nary<ExecutableParameter>> parameters;
    private Supplier<TypeInstance> returnType;
    private Supplier<Nary<TypeInstance>> declaredExceptions;
    
    @Override
    public PolymorphicInvokable asFunction() {
        return function.get();
    }

    @Override
    public Nary<ExecutableParameter> parameters() {
        return parameters.get();
    }

    @Override
    public TypeInstance returnType() {
        return returnType.get();
    }

    @Override
    public Nary<TypeInstance> declaredExceptions() {
        return declaredExceptions.get();
    }

    @Override
    public Nary<TypeInstance> parameterTypes() {
        Stream<TypeInstance> nativeStream = parameters().map(ExecutableParameter::declaredType);
        return NaryFromNative.create(nativeStream);
    }

    public static LambdaInstance create(LambdaDescription description) {
        LambdaInstance instance = new LambdaInstance();
        instance.function = description.getPolymorphicFunction();
        instance.parameters = description.getParameters();
        instance.returnType = description.getReturnType();
        instance.declaredExceptions = description.getDeclaredExceptions();
        return instance;
    }

}
