package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.bounds.UpperOnlyTypeBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.TypeVariable;
import java.util.Optional;

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
    public Optional<TypeInstance> componentType() {
        return Optional.empty();
    }

    @Override
    public String name() {
        return variableName;
    }

    /**
     * Creates a type variable from its minimum data
     * @param variableName The name of the variable
     * @param bounds The bounds of this type variable
     * @param variableAnnotations The optional annotations on this type
     * @return The new type variable instance
     */
    public static TypeVariableInstance create(String variableName, TypeBounds bounds, Annotation[] variableAnnotations) {
        TypeVariableInstance typeVariable = new TypeVariableInstance();
        typeVariable.variableName = variableName;
        typeVariable.variableBounds = bounds;
        typeVariable.setAnnotations(variableAnnotations);
        return typeVariable;
    }

    /**
     * Creates a type variable instance from an annotated type variable (kind of a mix of type variable and annotations)
     * @param annotatedTypeVariable The annotated mix
     * @return The new type variable instance
     */
    public static TypeVariableInstance createFrom(AnnotatedTypeVariable annotatedTypeVariable) {
        TypeVariable<?> typeVariable = (TypeVariable<?>) annotatedTypeVariable.getType();
        return create(typeVariable, annotatedTypeVariable.getAnnotatedBounds(), annotatedTypeVariable.getAnnotations());
    }

    /**
     * Creates a type variable instance from its native counter part
     * @param typeVariable The native type variable representation
     * @return The new type variable instance
     */
    public static TypeVariableInstance createFrom(TypeVariable<?> typeVariable) {
        // For some reason a type variable knows its annotated bounds (unlike other non-annotated types)
        return create(typeVariable, typeVariable.getAnnotatedBounds(), NO_ANNOTATIONS);
    }

    private static TypeVariableInstance create(TypeVariable<?> typeVariable, AnnotatedType[] bounds, Annotation[] annotations){
        String variableName = typeVariable.getName();
        TypeBounds variableBounds = UpperOnlyTypeBounds.create(bounds);
        return create(variableName, variableBounds, annotations);
    }


}
