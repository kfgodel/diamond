package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.annotations.NativeElementAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.members.exceptions.ExecutableExceptionsSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.ExecutableGenericsSupplier;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;
import ar.com.kfgodel.diamond.impl.members.parameters.ImmutableMemberParameters;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.NativeInstanceMethodInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.NativeStaticMethodInvoker;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    public Supplier<Nary<MemberModifier>> getModifiers() {
        return ImmutableMemberModifiers.create(nativeMethod);
    }

    @Override
    public Supplier<PolymorphicInvokable> getInvoker() {
        return CachedValue.lazilyBy(() -> Modifier.isStatic(nativeMethod.getModifiers()) ?
                NativeStaticMethodInvoker.create(nativeMethod) :
                NativeInstanceMethodInvoker.create(nativeMethod));
    }

    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
        return NativeElementAnnotationsSupplier.create(nativeMethod);
    }

    @Override
    public Supplier<Generics> getGenerics() {
        return CachedValue.lazilyBy(() -> ExecutableGenericsSupplier.create(nativeMethod));
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
