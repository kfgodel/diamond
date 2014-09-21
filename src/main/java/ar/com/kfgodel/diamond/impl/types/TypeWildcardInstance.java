package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

/**
 * This type represents a type wildcard
 * Created by kfgodel on 20/09/14.
 */
public class TypeWildcardInstance extends TypeInstanceSupport {

    private String wildcardName;
    private TypeBounds bounds;

    @Override
    public TypeBounds bounds() {
        return bounds;
    }

    @Override
    public String name() {
        return wildcardName;
    }

    /**
     * Creates a wildcard type representatin with the minimum data
     * @param parts The parts to create the instance
     * @return The created instance
     */
    public static TypeWildcardInstance create(TypeParts parts) {
        TypeWildcardInstance wildcardInstance = new TypeWildcardInstance();
        wildcardInstance.wildcardName = parts.getTypeName();
        wildcardInstance.bounds = parts.getBounds();
        wildcardInstance.setAnnotations(parts.getAnnotations());
        return wildcardInstance;
    }

}
