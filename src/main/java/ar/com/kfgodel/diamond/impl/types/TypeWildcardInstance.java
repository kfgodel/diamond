package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleTypeBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.WildcardType;
import java.util.Optional;

/**
 * This type represents a type wildcard
 * Created by kfgodel on 20/09/14.
 */
public class TypeWildcardInstance extends TypeInstanceSupport {

    private String wildcardName;
    private TypeBounds bounds;

    @Override
    public TypeBounds bounds() {
        return bounds;
    }

    @Override
    public Optional<TypeInstance> componentType() {
        return Optional.empty();
    }

    @Override
    public String name() {
        return wildcardName;
    }

    /**
     * Creates a wildcard type representatin with the minimum data
     * @param wildcardName The name of the wildcard
     * @param wildCardBounds The bound for the type
     * @param annotations The attached annotations
     * @return The created instance
     */
    public static TypeWildcardInstance create(String wildcardName, TypeBounds wildCardBounds, Annotation[] annotations) {
        TypeWildcardInstance wildcardInstance = new TypeWildcardInstance();
        wildcardInstance.wildcardName = wildcardName;
        wildcardInstance.bounds = wildCardBounds;
        wildcardInstance.setAnnotations(annotations);
        return wildcardInstance;
    }

    /**
     * Creates an type wildcard from its annotated native representation
     * @param annotatedWildCard The native mix of wildcard and annotations
     * @return The created instance
     */
    public static TypeWildcardInstance createFrom(AnnotatedWildcardType annotatedWildCard) {
        WildcardType wildcardType = (WildcardType) annotatedWildCard.getType();
        DoubleTypeBounds wildcardBounds = DoubleTypeBounds.create(annotatedWildCard.getAnnotatedUpperBounds(), annotatedWildCard.getAnnotatedLowerBounds());
        Annotation[] annotations = annotatedWildCard.getAnnotations();
        return create(wildcardType.getTypeName(), wildcardBounds, annotations);
    }

    public static TypeWildcardInstance createFrom(WildcardType wildcardType) {
        TypeBounds wildcardBounds = DoubleTypeBounds.create(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        return create(wildcardType.getTypeName(), wildcardBounds, NO_ANNOTATIONS);
    }


}
