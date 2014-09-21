package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
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

    /**
     * Creates a type representation from its native counterpart
     * @param genericArrayType The native representation of the generic array
     * @return The created instance
     */
    public static GenericArrayTypeInstance create(GenericArrayType genericArrayType) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(genericArrayType.getTypeName());
        parts.setComponentType(Diamond.types().fromUnspecific(genericArrayType.getGenericComponentType()));
        parts.setAnnotations(NO_ANNOTATIONS);
        return create(parts);
    }

    /**
     * Creates a type representation from its native annotated instance
     * @param annotatedArrayType The annotated generic array type
     * @return The created instance
     */
    public static GenericArrayTypeInstance create(AnnotatedArrayType annotatedArrayType) {
        TypeParts parts = TypeParts.create();
        Type genericArrayType = annotatedArrayType.getType();
        parts.setTypeName(genericArrayType.getTypeName());
        parts.setComponentType(Diamond.types().fromUnspecific(annotatedArrayType.getAnnotatedGenericComponentType()));
        parts.setAnnotations(annotatedArrayType.getAnnotations());
        return create(parts);
    }
}
