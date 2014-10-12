package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.generics.BoundedTypeGenerics;

/**
 * This type represents a variable type that can be defined with a concrete type in certain contexts (wildcards and type variables)
 * Created by kfgodel on 20/09/14.
 */
public class VariableTypeInstance extends TypeInstanceSupport {

    private BoundedTypeGenerics generics;

    @Override
    public TypeGenerics generics() {
        return generics;
    }

    /**
     * Creates a type variable from its description
     * @param description The parts needed to crete the instance
     * @return The new type variable instance
     */
    public static VariableTypeInstance create(TypeDescription description) {
        VariableTypeInstance variableType = new VariableTypeInstance();
        variableType.initializeSuper(description);
        variableType.generics = BoundedTypeGenerics.create(description.getBounds());
        return variableType;
    }


}
