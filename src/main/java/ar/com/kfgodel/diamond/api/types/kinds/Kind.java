package ar.com.kfgodel.diamond.api.types.kinds;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type represents a kind of type that groups types with similar features
 * Created by kfgodel on 03/02/15.
 */
public interface Kind {

    /**
     * Indicates if the given type belong to this kind (shares its properties)
     * @param testedType The teste type
     * @return True if the type is of this kind
     */
    boolean contains(TypeInstance testedType);
}
