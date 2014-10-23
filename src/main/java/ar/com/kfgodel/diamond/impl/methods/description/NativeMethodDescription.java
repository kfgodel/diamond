package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a the method description of a native method
 * Created by kfgodel on 07/10/14.
 */
public class NativeMethodDescription implements MethodDescription {

    private Method nativeMethod;

    @Override
    public Supplier<String> getName() {
        return SuppliedValue.lazilyBy(nativeMethod::getName);
    }

    @Override
    public Supplier<TypeInstance> getReturnType() {
        return SuppliedValue.lazilyBy(() -> Diamond.types().from(nativeMethod.getAnnotatedReturnType()));
    }

    @Override
    public Supplier<Stream<TypeInstance>> getParameterTypes() {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(()-> Arrays.stream(nativeMethod.getAnnotatedParameterTypes())
                .map((annotated) -> Diamond.types().from(annotated))
                .collect(Collectors.toList())));
    }

    @Override
    public Supplier<TypeInstance> getDeclaringType() {
        return NativeMemberDeclaringTypeSupplier.create(nativeMethod);
    }

    @Override
    public Supplier<Stream<MemberModifier>> getModifiers() {
        return ImmutableMemberModifiers.create(nativeMethod);
    }

    public static NativeMethodDescription create(Method nativeMethod) {
        NativeMethodDescription description = new NativeMethodDescription();
        description.nativeMethod = nativeMethod;
        return description;
    }


}
