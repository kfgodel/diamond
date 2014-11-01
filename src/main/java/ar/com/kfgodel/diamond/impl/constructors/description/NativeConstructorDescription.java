package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.annotations.NativeElementAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.ExecutableGenericsSupplier;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;
import ar.com.kfgodel.diamond.impl.members.parameters.ImmutableMemberParameters;
import ar.com.kfgodel.diamond.impl.natives.invokables.constructors.NativeConstructorInvoker;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a description for a constructor from a native instance
 * Created by kfgodel on 15/10/14.
 */
public class NativeConstructorDescription implements ConstructorDescription {

    private Constructor<?> nativeConstructor;

    @Override
    public Supplier<Stream<TypeInstance>> getParameterTypes() {
        return ImmutableMemberParameters.create(nativeConstructor);
    }

    @Override
    public Supplier<TypeInstance> getDeclaringType() {
        return NativeMemberDeclaringTypeSupplier.create(nativeConstructor);
    }

    @Override
    public Supplier<Stream<MemberModifier>> getModifiers() {
        return ImmutableMemberModifiers.create(nativeConstructor);
    }

    @Override
    public Supplier<PolymorphicInvokable> getInvoker() {
        return CachedValue.lazilyBy(() -> NativeConstructorInvoker.create(nativeConstructor));
    }

    @Override
    public Supplier<String> getName() {
        return CachedValue.lazilyBy(nativeConstructor::getName);
    }

    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
        return NativeElementAnnotationsSupplier.create(nativeConstructor);
    }

    @Override
    public Supplier<Generics> getGenerics() {
        return CachedValue.lazilyBy(() -> ExecutableGenericsSupplier.create(nativeConstructor));
    }

    public static NativeConstructorDescription create(Constructor<?> nativeConstructor) {
        NativeConstructorDescription description = new NativeConstructorDescription();
        description.nativeConstructor = nativeConstructor;
        return description;
    }

}
