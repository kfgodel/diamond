package ar.com.kfgodel.diamond.impl.fields.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.NativeMemberDeclaringTypeSupplier;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.ImmutableMemberModifiers;
import ar.com.kfgodel.diamond.impl.natives.NativeFieldGetter;
import ar.com.kfgodel.diamond.impl.natives.NativeFieldSetter;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Function;
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
        return SuppliedValue.lazilyBy(nativeField::getName);
    }

    @Override
    public Supplier<TypeInstance> getType() {
        return SuppliedValue.lazilyBy(()-> Diamond.types().from(nativeField.getAnnotatedType()));
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
    public Supplier<BiConsumer<Object, Object>> getSetter() {
        return SuppliedValue.lazilyBy(()-> NativeFieldSetter.create(nativeField));
    }

    @Override
    public Supplier<Function<Object, ?>> getGetter() {
        return SuppliedValue.lazilyBy(()-> NativeFieldGetter.create(nativeField));
    }

}
