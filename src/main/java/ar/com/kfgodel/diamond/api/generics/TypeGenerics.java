package ar.com.kfgodel.diamond.api.generics;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents the generics information about a specific type.<br>
 *     This instance will hold the relationships between a type and their parameters, arguments and bounds
 * Created by kfgodel on 05/10/14.
 */
public interface TypeGenerics {

    /**
     * @return The type boundaries of this type (if any)
     * Wildcards and type variables usually have boundaries other types don't
     */
    TypeBounds bounds();

    /**
     * @return The actual type arguments used to parameterize this type (if any).
     * Parameterized types have type arguments that are preserved through reflection (only on certain cases).<br>
     *     See typeParameters for the list of type variables that these arguments correspond to
     */
    Stream<TypeInstance> typeArguments();

    /**
     * @return The generic type parameters this type accepts (if any).
     * Parameterized classes have type variables that can be parameterized. This is the list of type variables.<br>
     *     See typeArguments for the actual type values of this variables
     */
    Stream<TypeInstance> typeParameters();

}