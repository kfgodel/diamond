package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
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
     * @param typeName The name of the type
     * @param arguments The type arguments for each parameter
     * @param annotations The attached annotations
     * @return The created instance
     */
    public static ParameterizedTypeInstance create(String typeName, List<TypeInstance> arguments, Annotation[] annotations) {
        ParameterizedTypeInstance parameterized = new ParameterizedTypeInstance();
        parameterized.typeName = typeName;
        parameterized.typeArguments = arguments;
        parameterized.setAnnotations(annotations);
        return parameterized;
    }

    /**
     * Creates a parameterized type instance from its native annotated representation
     * @param annotatedParameterized The native representation of an annotated type
     * @return The created instance
     */
    public static ParameterizedTypeInstance create(AnnotatedParameterizedType annotatedParameterized) {
        String typeName = annotatedParameterized.getType().getTypeName();
        List<TypeInstance> typeArguments = Arrays.stream(annotatedParameterized.getAnnotatedActualTypeArguments())
                .map((annotatedType) -> Diamond.types().fromUnspecific(annotatedType))
                .collect(Collectors.toList());
        Annotation[] annotations = annotatedParameterized.getAnnotations();
        return create(typeName, typeArguments, annotations );
    }

    /**
     * Creates a parameterized type from its native representation
     * @param parameterizedType The native representation
     * @return the created instance
     */
    public static ParameterizedTypeInstance create(ParameterizedType parameterizedType) {
        String typeName = parameterizedType.getTypeName();
        List<TypeInstance> typeArguments = Arrays.stream(parameterizedType.getActualTypeArguments())
                .map((typeArgument)-> Diamond.types().fromUnspecific(typeArgument))
                .collect(Collectors.toList());
        return create(typeName, typeArguments, NO_ANNOTATIONS);
    }


}
