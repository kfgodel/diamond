package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

import java.util.Optional;

/**
 * This type represents a generic array type
 * Created by kfgodel on 21/09/14.
 */
public class GenericArrayTypeInstance extends TypeInstanceSupport {

    private String name;
    private Optional<TypeInstance> componentType;

    @Override
    public Optional<TypeInstance> componentType() {
        return componentType;
    }

    @Override
    public String name() {
        return this.name;
    }

    /**
     * Creates an generic array type representation with the minimum data
     * @param parts The parts to create the instance
     * @return The created instance
     */
    public static GenericArrayTypeInstance create(TypeParts parts) {
        GenericArrayTypeInstance arrayType = new GenericArrayTypeInstance();
        arrayType.name = parts.getTypeName();
        arrayType.componentType = Optional.of(parts.getComponentType());
        arrayType.setAnnotations(parts.getAnnotations());
        return arrayType;
    }
}
