package ar.com.kfgodel.diamond.impl.members;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.invokables.UndefinedInvoker;
import ar.com.kfgodel.diamond.impl.members.declaringtype.UndefinedDeclaringType;
import ar.com.kfgodel.diamond.impl.members.exceptions.UndefinedMemberExceptions;
import ar.com.kfgodel.diamond.impl.members.generics.UndefinedMemberGenerics;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.UndefinedMemberModifiers;
import ar.com.kfgodel.diamond.impl.members.parameters.UndefinedMemberParameters;
import ar.com.kfgodel.diamond.impl.named.UndefinedName;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type is a base class for type member classes
 * Created by kfgodel on 18/10/14.
 */
public abstract class TypeMemberSupport implements TypeMember {

    private Supplier<Nary<Annotation>> annotations = NoAnnotationsSupplier.INSTANCE;
    private Supplier<String> name = UndefinedName.create(this);
    private Supplier<TypeInstance> declaringType = UndefinedDeclaringType.create(this);
    private Supplier<Nary<Modifier>> modifiers = UndefinedMemberModifiers.create(this);
    private Supplier<PolymorphicInvokable> invoker = UndefinedInvoker.create(this);
    private Supplier<Generics> generics = UndefinedMemberGenerics.create(this);
    private Supplier<Nary<TypeInstance>> exceptions = UndefinedMemberExceptions.create(this);
    private Supplier<Nary<ExecutableParameter>> parameters = UndefinedMemberParameters.create(this);

    @Override
    public TypeInstance declaringType() {
        return declaringType.get();
    }

    @Override
    public Nary<Modifier> modifiers() {
        return modifiers.get();
    }

    @Override
    public boolean is(Modifier expectedModifier) {
        return modifiers().anyMatch(expectedModifier);
    }

    @Override
    public PolymorphicInvokable asFunction() {
        return this.invoker.get();
    }

    @Override
    public String name() {
        return name.get();
    }

    @Override
    public Nary<Annotation> annotations() {
        return annotations.get();
    }

    @Override
    public Generics generics() {
        return generics.get();
    }

    @Override
    public Nary<TypeInstance> parameterTypes() {
        Stream<TypeInstance> nativeStream = parameters().map(ExecutableParameter::declaredType);
        return NaryFromNative.create(nativeStream);
    }

    @Override
    public Nary<TypeInstance> declaredExceptions() {
        return exceptions.get();
    }

    @Override
    public Nary<ExecutableParameter> parameters() {
        return parameters.get();
    }

    @Override
    public boolean isInstanceMember() {
        return !is(Modifiers.STATIC);
    }

    @Override
    public int hashCode() {
        return getIdentityToken().hashCode();
    }

    protected void initialize(MemberDescription description){
        this.name = description.getName();
        this.declaringType = description.getDeclaringType();
        this.modifiers = description.getModifiers();
        this.invoker = description.getInvoker();
        this.annotations = description.getAnnotations();
        this.parameters = description.getParameters();
        this.generics = description.getGenerics();
        this.exceptions = description.getDeclaredExceptions();
    }
}
