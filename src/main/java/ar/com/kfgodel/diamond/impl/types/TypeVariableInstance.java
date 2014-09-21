package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.UpperOnlyTypeBounds;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.TypeVariable;

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

    /**
     * Creates a type variable instance from an annotated type variable (kind of a mix of type variable and annotations)
     * @param annotatedTypeVariable The annotated mix
     * @return The new type variable instance
     */
    public static TypeVariableInstance createFrom(AnnotatedTypeVariable annotatedTypeVariable) {
        TypeParts parts = TypeParts.create();
        TypeVariable<?> typeVariable = (TypeVariable<?>) annotatedTypeVariable.getType();
        parts.setTypeName(typeVariable.getTypeName());
        parts.setBounds(UpperOnlyTypeBounds.create(annotatedTypeVariable.getAnnotatedBounds()));
        parts.setAnnotations(annotatedTypeVariable.getAnnotations());
        return create(parts);
    }

    /**
     * Creates a type variable instance from its native counter part
     * @param typeVariable The native type variable representation
     * @return The new type variable instance
     */
    public static TypeVariableInstance createFrom(TypeVariable<?> typeVariable) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(typeVariable.getTypeName());
        // For some reason a type variable knows its annotated bounds (unlike other non-annotated types)
        parts.setBounds(UpperOnlyTypeBounds.create(typeVariable.getAnnotatedBounds()));
        parts.setAnnotations(NO_ANNOTATIONS);
        return create(parts);
    }

}
