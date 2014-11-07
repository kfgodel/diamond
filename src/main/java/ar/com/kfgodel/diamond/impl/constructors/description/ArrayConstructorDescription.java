package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.members.modifiers.Visibility;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.exceptions.NoExceptionsSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.UnGenerifiedMemberGenerics;
import ar.com.kfgodel.diamond.impl.natives.invokables.constructors.NativeArrayConstructor;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromElementSupplier;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the description of an artificial array type constructor
 * Created by kfgodel on 16/10/14.
 */
public class ArrayConstructorDescription implements ConstructorDescription {
    private Class<?> nativeArrayClass;

    @Override
    public Supplier<Nary<TypeInstance>> getParameterTypes() {
        return NaryFromElementSupplier.from(Diamond.of(int.class));
    }

    @Override
    public Supplier<TypeInstance> getDeclaringType() {
        return CachedValue.eagerlyFrom(Diamond.of(nativeArrayClass));
    }

    @Override
    public Supplier<Nary<MemberModifier>> getModifiers() {
        // Array creation is similar to public visibility
        return NaryFromElementSupplier.from(Visibility.PUBLIC);
    }

    @Override
    public Supplier<PolymorphicInvokable> getInvoker() {
        return CachedValue.lazilyBy(() -> NativeArrayConstructor.create(nativeArrayClass));
    }

    @Override
    public Supplier<String> getName() {
        return CachedValue.lazilyBy(nativeArrayClass::getSimpleName);
    }

    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
        return NoAnnotationsSupplier.INSTANCE;
    }

    @Override
    public Supplier<Generics> getGenerics() {
        return UnGenerifiedMemberGenerics::instance;
    }

    @Override
    public Supplier<Nary<TypeInstance>> getDeclaredExceptions() {
        return NoExceptionsSupplier::create;
    }

    public static ArrayConstructorDescription create(Class<?> nativeArrayType) {
        ArrayConstructorDescription description = new ArrayConstructorDescription();
        description.nativeArrayClass = nativeArrayType;
        return description;
    }

}
