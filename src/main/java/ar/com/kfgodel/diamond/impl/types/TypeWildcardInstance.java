package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleTypeBounds;
import ar.com.kfgodel.diamond.impl.types.parts.TypeParts;

import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.WildcardType;

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
    public String name() {
        return wildcardName;
    }

    /**
     * Creates a wildcard type representatin with the minimum data
     * @param parts The parts to create the instance
     * @return The created instance
     */
    public static TypeWildcardInstance create(TypeParts parts) {
        TypeWildcardInstance wildcardInstance = new TypeWildcardInstance();
        wildcardInstance.wildcardName = parts.getTypeName();
        wildcardInstance.bounds = parts.getBounds();
        wildcardInstance.setAnnotations(parts.getAnnotations());
        return wildcardInstance;
    }

    /**
     * Creates an type wildcard from its annotated native representation
     * @param annotatedWildCard The native mix of wildcard and annotations
     * @return The created instance
     */
    public static TypeWildcardInstance createFrom(AnnotatedWildcardType annotatedWildCard) {
        WildcardType wildcardType = (WildcardType) annotatedWildCard.getType();
        TypeParts parts = TypeParts.create();
        parts.setTypeName(wildcardType.getTypeName());
        parts.setBounds(DoubleTypeBounds.create(annotatedWildCard.getAnnotatedUpperBounds(), annotatedWildCard.getAnnotatedLowerBounds()));
        parts.setAnnotations(annotatedWildCard.getAnnotations());
        return create(parts);
    }

    public static TypeWildcardInstance createFrom(WildcardType wildcardType) {
        TypeParts parts = TypeParts.create();
        parts.setTypeName(wildcardType.getTypeName());
        parts.setBounds(DoubleTypeBounds.create(wildcardType.getUpperBounds(), wildcardType.getLowerBounds()));
        parts.setAnnotations(NO_ANNOTATIONS);
        return create(parts);
    }


}
