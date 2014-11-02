package ar.com.kfgodel.diamond.impl.fields.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.annotations.NativeElementAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.members.exceptions.NoExceptionsSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.UnGenerifiedMemberGenerics;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;
import ar.com.kfgodel.diamond.impl.members.parameters.NoParametersSupplier;
import ar.com.kfgodel.diamond.impl.natives.invokables.fields.NativeInstanceFieldInvoker;
import ar.com.kfgodel.diamond.impl.natives.invokables.fields.NativeStaticFieldInvoker;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a field description of a native field instance
 * Created by kfgodel on 12/10/14.
 */
public class NativeFieldDescription implements FieldDescription {

    private Field nativeField;

    public static NativeFieldDescription create(Field nativeField) {
        NativeFieldDescription description = new NativeFieldDescription();
        description.nativeField = nativeField;
        return description;
    }

    @Override
    public Supplier<String> getName() {
        return CachedValue.lazilyBy(nativeField::getName);
    }

    @Override
    public Supplier<TypeInstance> getType() {
        return CachedValue.lazilyBy(() -> Diamond.types().from(nativeField.getAnnotatedType()));
    }

    @Override
    public Supplier<Stream<TypeInstance>> getParameterTypes() {
        return NoParametersSupplier.INSTANCE;
    }

    @Override
    public Supplier<TypeInstance> getDeclaringType() {
        return NativeMemberDeclaringTypeSupplier.create(nativeField);
    }

    @Override
    public Supplier<Stream<MemberModifier>> getModifiers() {
        return ImmutableMemberModifiers.create(nativeField);
    }


    @Override
    public Supplier<PolymorphicInvokable> getInvoker() {
        return CachedValue.lazilyBy(() -> Modifier.isStatic(nativeField.getModifiers()) ?
                        NativeStaticFieldInvoker.create(nativeField) :
                        NativeInstanceFieldInvoker.create(nativeField)
        );
    }

    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
        return NativeElementAnnotationsSupplier.create(nativeField);
    }

    @Override
    public Supplier<Generics> getGenerics() {
        return UnGenerifiedMemberGenerics::instance;
    }

    @Override
    public Supplier<Stream<TypeInstance>> getDeclaredExceptions() {
        return NoExceptionsSupplier::create;
    }
}
