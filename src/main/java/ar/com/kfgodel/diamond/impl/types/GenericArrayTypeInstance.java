package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
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
     * @param typeName The name of this type
     * @param componentType The type of elements for the array
     * @param annotations The attached annotations
     * @return The created instance
     */
    public static GenericArrayTypeInstance create(String typeName, TypeInstance componentType, Annotation[] annotations) {
        GenericArrayTypeInstance arrayType = new GenericArrayTypeInstance();
        arrayType.name = typeName;
        arrayType.componentType = Optional.of(componentType);
        arrayType.setAnnotations(annotations);
        return arrayType;
    }

    /**
     * Creates a type representation from its native counterpart
     * @param genericArrayType The native representation of the generic array
     * @return The created instance
     */
    public static GenericArrayTypeInstance create(GenericArrayType genericArrayType) {
        String typeName = genericArrayType.getTypeName();
        TypeInstance componentType = Diamond.types().fromUnspecific(genericArrayType.getGenericComponentType());
        return create(typeName, componentType, NO_ANNOTATIONS);
    }

    /**
     * Creates a type representation from its native annotated instance
     * @param annotatedArrayType The annotated generic array type
     * @return The created instance
     */
    public static GenericArrayTypeInstance create(AnnotatedArrayType annotatedArrayType) {
        Type genericArrayType = annotatedArrayType.getType();
        String typeName = genericArrayType.getTypeName();
        TypeInstance componentType = Diamond.types().fromUnspecific(annotatedArrayType.getAnnotatedGenericComponentType());
        Annotation[] annotations = annotatedArrayType.getAnnotations();
        return create(typeName, componentType, annotations);
    }
}
