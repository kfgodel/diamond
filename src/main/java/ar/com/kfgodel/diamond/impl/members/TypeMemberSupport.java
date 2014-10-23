package ar.com.kfgodel.diamond.impl.members;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.modifiers.suppliers.UndefinedMemberModifiers;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type is a base class for type member classes
 * Created by kfgodel on 18/10/14.
 */
public class TypeMemberSupport implements TypeMember {

    private LazyValue<TypeInstance> declaringType;
    private Supplier<Stream<MemberModifier>> modifiers = UndefinedMemberModifiers.create(this);

    @Override
    public TypeInstance declaringType() {
        if(declaringType == null){
            throw new DiamondException("A declaring type definition was not provided from a subclass for this member: " + this);
        }
        return declaringType.get();
    }

    protected void setDeclaringType(Supplier<TypeInstance> declaringTypeSupplier){
        this.declaringType = SuppliedValue.lazilyBy(declaringTypeSupplier);
    }

    @Override
    public Stream<MemberModifier> modifiers() {
        return modifiers.get();
    }

    protected void setModifiers(Supplier<Stream<MemberModifier>> modifierSupplier){
        this.modifiers = modifierSupplier;
    }

}
