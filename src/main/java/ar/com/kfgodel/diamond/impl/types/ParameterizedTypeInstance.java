package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

import java.util.List;
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


}
