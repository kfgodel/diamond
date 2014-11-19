package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.defaults.MethodDefaultValueSupplier;
import ar.com.kfgodel.diamond.impl.members.exceptions.ExecutableExceptionsSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.ExecutableGenericsSupplier;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;
import ar.com.kfgodel.diamond.impl.members.parameters.ImmutableMemberParameters;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.NativeMethodInvokerGenerator;
import ar.com.kfgodel.diamond.impl.natives.suppliers.AnnotatedElementAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * This type represents a the method description of a native method
 * Created by kfgodel on 07/10/14.
 */
public class NativeMethodDescription implements MethodDescription {

    private Method nativeMethod;

    @Override
    public Supplier<String> getName() {
        return CachedValue.lazilyBy(nativeMethod::getName);
    }

    @Override
    public Supplier<TypeInstance> getReturnType() {
        return CachedValue.lazilyBy(() -> Diamond.types().from(nativeMethod.getAnnotatedReturnType()));
    }

    @Override
    public Supplier<Nary<ExecutableParameter>> getParameters() {
        return ImmutableMemberParameters.create(nativeMethod);
    }

    @Override
    public Supplier<TypeInstance> getDeclaringType() {
        return NativeMemberDeclaringTypeSupplier.create(nativeMethod);
    }

    @Override
    public Supplier<Nary<Modifier>> getModifiers() {
        return ImmutableMemberModifiers.create(nativeMethod);
    }

    @Override
    public Supplier<PolymorphicInvokable> getInvoker() {
        return CachedValue.lazilyBy(() -> NativeMethodInvokerGenerator.INSTANCE.generateFor(nativeMethod));
    }

    @Override
    public Supplier<Nary<Annotation>> getAnnotations() {
        return AnnotatedElementAnnotationsSupplier.create(nativeMethod);
    }

    @Override
    public Supplier<Generics> getGenerics() {
        return CachedValue.lazilyBy(() -> ExecutableGenericsSupplier.create(nativeMethod));
    }

    @Override
    public Supplier<Nary<Object>> getDefaultValue() {
        return MethodDefaultValueSupplier.create(nativeMethod);
    }

    @Override
    public Supplier<Nary<Method>> getNativeMethod() {
        return () -> NaryFromNative.of(nativeMethod);
    }

    @Override
    public Supplier<Nary<TypeInstance>> getDeclaredExceptions() {
        return ExecutableExceptionsSupplier.create(nativeMethod);
    }

    public static NativeMethodDescription create(Method nativeMethod) {
        NativeMethodDescription description = new NativeMethodDescription();
        description.nativeMethod = nativeMethod;
        return description;
    }


}
