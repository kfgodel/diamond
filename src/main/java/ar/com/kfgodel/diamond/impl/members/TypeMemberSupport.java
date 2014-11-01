package ar.com.kfgodel.diamond.impl.members;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.invokables.UndefinedInvoker;
import ar.com.kfgodel.diamond.impl.members.declaringtype.UndefinedDeclaringType;
import ar.com.kfgodel.diamond.impl.members.generics.UndefinedMemberGenerics;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.UndefinedMemberModifiers;
import ar.com.kfgodel.diamond.impl.members.parameters.UndefinedMemberParameters;
import ar.com.kfgodel.diamond.impl.named.UndefinedName;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type is a base class for type member classes
 * Created by kfgodel on 18/10/14.
 */
public abstract class TypeMemberSupport implements TypeMember {

    private Supplier<Stream<Annotation>> annotations = NoAnnotationsSupplier.INSTANCE;
    private Supplier<String> name = UndefinedName.create(this);
    private Supplier<TypeInstance> declaringType = UndefinedDeclaringType.create(this);
    private Supplier<Stream<MemberModifier>> modifiers = UndefinedMemberModifiers.create(this);
    private Supplier<PolymorphicInvokable> invoker = UndefinedInvoker.create(this);
    private Supplier<Generics> generics = UndefinedMemberGenerics.create(this);
    private Supplier<Stream<TypeInstance>> parameterTypes = UndefinedMemberParameters.create(this);


    @Override
    public TypeInstance declaringType() {
        return declaringType.get();
    }

    @Override
    public Stream<MemberModifier> modifiers() {
        return modifiers.get();
    }

    @Override
    public boolean is(MemberModifier expectedModifier) {
        return modifiers().anyMatch(expectedModifier);
    }

    @Override
    public PolymorphicInvokable asFunction() {
        return this.invoker.get();
    }

    @Override
    public String toString() {
        return this.declaration();
    }

    @Override
    public String name() {
        return name.get();
    }

    @Override
    public Stream<Annotation> annotations() {
        return annotations.get();
    }

    @Override
    public Generics generics() {
        return generics.get();
    }

    @Override
    public Stream<TypeInstance> parameterTypes() {
        return parameterTypes.get();
    }

    protected void initialize(MemberDescription description){
        this.name = description.getName();
        this.declaringType = description.getDeclaringType();
        this.modifiers = description.getModifiers();
        this.invoker = description.getInvoker();
        this.annotations = description.getAnnotations();
        this.parameterTypes = description.getParameterTypes();
        this.generics = description.getGenerics();
    }
}
