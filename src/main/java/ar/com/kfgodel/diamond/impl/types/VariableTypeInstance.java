package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

/**
 * This type represents a variable type that can be defined with a concrete type in certain contexts (wildcards and type variables)
 * Created by kfgodel on 20/09/14.
 */
public class VariableTypeInstance extends TypeInstanceSupport {

    private TypeBounds typeBounds;

    @Override
    public TypeBounds bounds() {
        return typeBounds;
    }

    /**
     * Creates a type variable from its minimum data
     * @param parts The parts needed to crete the instance
     * @return The new type variable instance
     */
    public static VariableTypeInstance create(TypeParts parts) {
        VariableTypeInstance variableType = new VariableTypeInstance();
        variableType.setNames(parts.getNames());
        variableType.typeBounds = parts.getBounds();
        variableType.setAnnotations(parts.getAnnotations());
        return variableType;
    }

}
