package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a member of a type (field, method or constructor)
 * Created by kfgodel on 18/10/14.
 */
public interface TypeMember {

    /**
     * @return The type in which this member is declared and who owns this member
     */
    TypeInstance declaringType();
}
