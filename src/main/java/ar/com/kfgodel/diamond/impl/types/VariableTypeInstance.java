package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescription;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

/**
 * This type represents a variable type that can be defined with a concrete type in certain contexts (wildcards and type variables)
 * Created by kfgodel on 20/09/14.
 */
public class VariableTypeInstance extends TypeInstanceSupport {

    private LazyValue<TypeBounds> typeBounds;

    @Override
    public TypeBounds bounds() {
        return typeBounds.get();
    }

    /**
     * Creates a type variable from its description
     * @param description The parts needed to crete the instance
     * @return The new type variable instance
     */
    public static VariableTypeInstance create(TypeDescription description) {
        VariableTypeInstance variableType = new VariableTypeInstance();
        variableType.setNames(description.getNames());
        variableType.setAnnotations(description.getAnnotations());
        variableType.typeBounds = SuppliedValue.create(description.getBounds());
        return variableType;
    }


}
