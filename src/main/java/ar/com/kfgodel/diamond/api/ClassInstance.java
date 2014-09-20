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
     * @return THe name that identifies this class
     */
    public String name();

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
     * @return The optional superclass of this instance. Empty for Object
     */
    Optional<ClassInstance> getSuperclass();
}
