package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a Parameterized generic type
 * Created by kfgodel on 21/09/14.
 */
public class ParameterizedTypeInstance extends TypeInstanceSupport {

    private String typeName;
    private List<TypeInstance> typeArguments;

    @Override
    public Stream<TypeInstance> typeArguments() {
        return typeArguments.stream();
    }

    @Override
    public String name() {
        return typeName;
    }

    /**
     * Creates a parameterized type representation with its minimum data
     * @param parts The parts needed to create the instance
     * @return The created instance
     */
    public static ParameterizedTypeInstance create(TypeParts parts) {
        ParameterizedTypeInstance parameterized = new ParameterizedTypeInstance();
        parameterized.typeName = parts.getTypeName();
        parameterized.typeArguments = parts.getTypeArguments();
        parameterized.setAnnotations(parts.getAnnotations());
        return parameterized;
    }

    /**
     * Creates a parameterized type instance from its native annotated representation
     * @param annotatedParameterized The native representation of an annotated type
     * @return The created instance
     */
    public static ParameterizedTypeInstance create(AnnotatedParameterizedType annotatedParameterized) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(annotatedParameterized.getType().getTypeName());
        List<TypeInstance> typeArguments = Arrays.stream(annotatedParameterized.getAnnotatedActualTypeArguments())
                .map((annotatedType) -> Diamond.types().fromUnspecific(annotatedType))
                .collect(Collectors.toList());
        parts.setTypeArguments(typeArguments);
        parts.setAnnotations(annotatedParameterized.getAnnotations());
        return create(parts);
    }

    /**
     * Creates a parameterized type from its native representation
     * @param parameterizedType The native representation
     * @return the created instance
     */
    public static ParameterizedTypeInstance create(ParameterizedType parameterizedType) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(parameterizedType.getTypeName());
        List<TypeInstance> typeArguments = Arrays.stream(parameterizedType.getActualTypeArguments())
                .map((typeArgument)-> Diamond.types().fromUnspecific(typeArgument))
                .collect(Collectors.toList());
        parts.setTypeArguments(typeArguments);
        parts.setAnnotations(NO_ANNOTATIONS);
        return create(parts);
    }


}
