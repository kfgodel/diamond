package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedVariableTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.TypeVariableBoundSupplier;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.function.Supplier;

/**
 * This type represents a description of an unannotated native type variable
 * Created by kfgodel on 28/09/14.
 */
public class TypeVariableDescription extends UnannotatedVariableTypeDescriptionSupport {

    private TypeVariable<?> typeVariable;

    @Override
    protected Type getNativeType() {
        return typeVariable;
    }

    @Override
    public Supplier<TypeBounds> getBounds() {
        return TypeVariableBoundSupplier.create(typeVariable);
    }

    public static TypeVariableDescription create(TypeVariable<?> typeVariable) {
        TypeVariableDescription description = new TypeVariableDescription();
        description.typeVariable = typeVariable;
        return description;
    }

}
