package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.declaration.Declarable;
import ar.com.kfgodel.diamond.api.executables.Executable;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.generics.Generified;
import ar.com.kfgodel.diamond.api.members.modifiers.ModifiableMember;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a member of a type (field, method or constructor)
 * Created by kfgodel on 18/10/14.
 */
public interface TypeMember extends Named, Annotated, ModifiableMember, Generified, Declarable, Executable {

    /**
     * @return The type in which this member is declared and who owns this member
     */
    TypeInstance declaringType();

    /**
     * @return The generics information for this member
     */
    @Override
    Generics generics();
}
