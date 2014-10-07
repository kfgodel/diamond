package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.AnnotatedTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.WildcardBoundsSupplier;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.WildcardType;
import java.util.function.Supplier;

/**
 * This type represents an annotated native wildcard type description
 * Created by kfgodel on 28/09/14.
 */
public class AnnotatedWildcardDescription extends AnnotatedTypeDescriptionSupport {

    private AnnotatedWildcardType nativeType;

    @Override
    protected AnnotatedType getAnnotatedType() {
        return nativeType;
    }

    @Override
    protected TypeDescription createUnannotatedDescription() {
        return WildcardTypeDescription.create((WildcardType) nativeType.getType());
    }

    @Override
    public Supplier<TypeBounds> getBounds() {
        return WildcardBoundsSupplier.create(nativeType);
    }

    public static AnnotatedWildcardDescription create(AnnotatedWildcardType nativeType) {
        AnnotatedWildcardDescription description = new AnnotatedWildcardDescription();
        description.nativeType = nativeType;
        return description;
    }

}
