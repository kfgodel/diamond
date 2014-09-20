package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.naming.Named;

/**
 * This type represents the basic interface common to all types.<br>
 * Instances of this type represent one of the possible type in Java language including type variables and wildcards.<br>
 * <br>
 * Methods in this type are based on information available in subtypes of Java Type
 *
 * Created by kfgodel on 20/09/14.
 */
public interface TypeInstance extends Named, Annotated {

    /**
     * @return The type boundaries of this type (if any)
     * Wildcards and type variables usually have boundaries other types don't
     */
    TypeBounds bounds();

}
