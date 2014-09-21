package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

/**
 * This type represents a type variable
 * Created by kfgodel on 20/09/14.
 */
public class TypeVariableInstance extends TypeInstanceSupport {

    private String variableName;
    private TypeBounds variableBounds;

    @Override
    public TypeBounds bounds() {
        return variableBounds;
    }

    @Override
    public String name() {
        return variableName;
    }

    /**
     * Creates a type variable from its minimum data
     * @param parts The parts needed to crete the instance
     * @return The new type variable instance
     */
    public static TypeVariableInstance create(TypeParts parts) {
        TypeVariableInstance typeVariable = new TypeVariableInstance();
        typeVariable.variableName = parts.getTypeName();
        typeVariable.variableBounds = parts.getBounds();
        typeVariable.setAnnotations(parts.getAnnotations());
        return typeVariable;
    }

}
