package ar.com.kfgodel.diamond.impl.members;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.invokables.UndefinedInvoker;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.UndefinedMemberModifiers;
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
    private Supplier<TypeInstance> declaringType;
    private Supplier<Stream<MemberModifier>> modifiers = UndefinedMemberModifiers.create(this);
    private Supplier<PolymorphicInvokable> invoker = UndefinedInvoker.create(this);

    @Override
    public TypeInstance declaringType() {
        if(declaringType == null){
            throw new DiamondException("A declaring type definition was not provided from a subclass for this member: " + this);
        }
        return declaringType.get();
    }

    protected void setDeclaringType(Supplier<TypeInstance> declaringTypeSupplier){
        this.declaringType = declaringTypeSupplier;
    }

    @Override
    public Stream<MemberModifier> modifiers() {
        return modifiers.get();
    }

    protected void setModifiers(Supplier<Stream<MemberModifier>> modifierSupplier){
        this.modifiers = modifierSupplier;
    }

    @Override
    public boolean is(MemberModifier expectedModifier) {
        return modifiers().anyMatch(expectedModifier);
    }

    protected void setInvoker(Supplier<PolymorphicInvokable> invoker) {
        this.invoker = invoker;
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
    public Stream<Annotation> annotations() {
        return annotations.get();
    }

    /**
     * Use this to override default creation with no annotations
     * @param annotationSupplier The new annotations
     */
    protected void setAnnotations(Supplier<Stream<Annotation>> annotationSupplier) {
        this.annotations = annotationSupplier;
    }
}
