package ar.com.kfgodel.diamond.api.types.inheritance;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;

/**
 * This type represents the information about a type inheritance an its relations with other super types
 * Created by kfgodel on 05/10/14.
 */
public interface TypeInheritance {

    /**
     * @return The extended supertype of this type (declared with extends). Or empty if this type
     *  represents either the Object class, an interface type, an array type, a primitive type, void,
     *  or a variable type (like wildcard).<br>
     *  The extended type is the parent class with correct type arguments assigned from this type,
     *  thus is the compile time parent type of this type.
     */
    Optional<TypeInstance> extendedType();

    /**
     * @return The optional superclass of this instance. Or empty if this type
     *  represents either the Object class, an interface type, an array type, a primitive type, void,
     *  or a variable type (like wildcard).<br>
     *     The super class is the un-parameterized (raw) class instance that is the runtime super type of
     *     this type
     */
    Optional<TypeInstance> superclass();

    /**
     * Returns this type lineage (starting from this type, the set of extended types up until Object).<br>
     *     This lineage does not follow class relationships but parameterized types relationships.<br>
     *     In this lineage you will find compile time related types
     * @return The type lineage of this type
     */
    TypeLineage typeLineage();

    /**
     * Returns this class lineage (starting from this type, the set of inherited classes up until Object).<br>
     *     This lineage does have type parameters coherency, because follows raw class inheritance.<br>
     *     In this lineage you will find runtime tim related types
     * @return The class lineage of this type
     */
    TypeLineage classLineage();
}
