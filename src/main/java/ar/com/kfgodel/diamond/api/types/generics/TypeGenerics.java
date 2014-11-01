package ar.com.kfgodel.diamond.api.types.generics;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents the generics information for a specific type.<br>
 *     This instance will hold the relationships between a type and their parameters, arguments and bounds
 * Created by kfgodel on 05/10/14.
 */
public interface TypeGenerics extends Generics {

    /**
     * @return The type boundaries of this type (if any)
     * Wildcards and type variables usually have boundaries other types don't
     */
    TypeBounds bounds();

    /**
     * @return The actual type arguments used to parameterize this type (if any).
     * Parameterized types have type arguments that are preserved through reflection (only on certain cases).<br>
     *     See parameters for the list of type variables that these arguments correspond to
     */
    Stream<TypeInstance> arguments();

    /**
     * @return The generic type parameters this type accepts (if any).
     * Parameterized classes have type variables that can be parameterized. This is the list of type variables.<br>
     *     See arguments for the actual type values of this variables
     */
    Stream<TypeInstance> parameters();

}
