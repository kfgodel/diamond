package ar.com.kfgodel.diamond.api.types;

import java.util.stream.Stream;

/**
 * This type represents the bounds of a wildcard or variable type.<br>
 *     This instance may or may not have boundaries depending on the type declaration
 * Created by kfgodel on 20/09/14.
 */
public interface TypeBounds {
    /**
     * The upper bounds limit the possible types to an intersection set of subtypes of the given upper types
     * @return The upper type bounds on this instance (if any declared)
     */
    Stream<TypeInstance> upper();

    /**
     * The lower bounds limit the possible types to an intersection set of supertypes of the givven lower types.<br>
     *  On JVM this usually is only one type
     * @return The lower type bounds on this instance (if any declared)
     */
    Stream<TypeInstance> lower();
}
