package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a description for a constructor from a native instance
 * Created by kfgodel on 15/10/14.
 */
public class NativeConstructorDescription implements ConstructorDescription {

    private Constructor<?> constructor;

    @Override
    public Supplier<Stream<TypeInstance>> getParameterTypes() {
        return ()-> Arrays.stream(constructor.getAnnotatedParameterTypes())
                .map((annotated) -> Diamond.types().from(annotated));
    }

    @Override
    public Supplier<TypeInstance> getDeclaringType() {
        return NativeMemberDeclaringTypeSupplier.create(constructor);
    }

    @Override
    public Supplier<Stream<MemberModifier>> getModifiers() {
        return ImmutableMemberModifiers.create(constructor);
    }

    public static NativeConstructorDescription create(Constructor<?> nativeConstructor) {
        NativeConstructorDescription description = new NativeConstructorDescription();
        description.constructor = nativeConstructor;
        return description;
    }

}
