package ar.com.kfgodel.diamond.impl.constructors;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.constructors.declaration.ConstructorDeclaration;
import ar.com.kfgodel.diamond.impl.constructors.equality.ConstructorEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;

/**
 * This type represents an instance of type constructor
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorInstance extends TypeMemberSupport implements TypeConstructor {

    @Override
    public boolean equals(Object obj) {
        return ConstructorEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return ConstructorEquality.INSTANCE.hashcodeFor(this);
    }

    @Override
    public Object invoke(Object... arguments) {
        return asFunction().invoke(arguments);
    }


    @Override
    public Object get() {
        return asFunction().get();
    }

    @Override
    public Object apply(Object argument) {
        return asFunction().apply(argument);
    }


    public static TypeConstructor create(ConstructorDescription description) {
        TypeConstructorInstance constructor = new TypeConstructorInstance();
        constructor.initialize(description);
        return constructor;
    }

    @Override
    public String declaration() {
        return ConstructorDeclaration.create(this).asString();
    }

    @Override
    public TypeInstance returnType() {
        return declaringType();
    }

    @Override
    public boolean isInstanceMember() {
        // Constructors are always class members (don't require an instance)
        return false;
    }
}
