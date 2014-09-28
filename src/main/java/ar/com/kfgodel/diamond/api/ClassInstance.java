package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassMethodSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;

/**
 * This type represents a class
 * Created by kfgodel on 17/09/14.
 */
public interface ClassInstance extends TypeInstance {
    /**
     * The name of the class without a package prefix
     * @return The name that identifies this class
     */
    String name();

    /**
     * Returns the accessor object for class methods of this instance
     * @return The source of methods
     */
    ClassDefinedClassMethodSource methods();

    /**
     * Return the object that represents this class and the linear relation of their superclasses up to Object
     * @return The lineage of this class
     */
    ClassLineage lineage();

    /**
     * @return The optional superclass of this instance. Or empty if this type
     *  represents either the Object class, an interface type, an array type, a primitive type, void,
     *  or a variable type (like wildcard).<br>
     *     The super class is the un-parameterized (raw) class instance that is the runtime super type of
     *     this type
     */
    Optional<ClassInstance> superclass();

}
