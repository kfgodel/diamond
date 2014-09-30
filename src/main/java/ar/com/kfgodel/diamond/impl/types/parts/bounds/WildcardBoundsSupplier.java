package ar.com.kfgodel.diamond.impl.types.parts.bounds;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.bounds.DoubleTypeBounds;

import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.WildcardType;
import java.util.function.Supplier;

/**
 * This type represents the supplier of bounds for a wildcard type
 * Created by kfgodel on 28/09/14.
 */
public class WildcardBoundsSupplier implements Supplier<TypeBounds> {

    private Object nativeType;

    public static WildcardBoundsSupplier create(Object nativeType) {
        WildcardBoundsSupplier supplier = new WildcardBoundsSupplier();
        supplier.nativeType = nativeType;
        return supplier;
    }

    @Override
    public TypeBounds get() {
        Object[] upperBounds;
        Object[] lowerBounds;
        if(nativeType instanceof AnnotatedWildcardType){
            AnnotatedWildcardType asAnnotated = (AnnotatedWildcardType) nativeType;
            upperBounds = asAnnotated.getAnnotatedUpperBounds();
            lowerBounds = asAnnotated.getAnnotatedLowerBounds();
        }else if(nativeType instanceof WildcardType){
            WildcardType asWildcard = (WildcardType) nativeType;
            upperBounds = asWildcard.getUpperBounds();
            lowerBounds = asWildcard.getLowerBounds();
        }else{
            throw new DiamondException("The type["+nativeType+"] is not a wildcard representation");
        }
        return DoubleTypeBounds.create(TypeVariableBoundSupplier.typeListFrom(upperBounds), TypeVariableBoundSupplier.typeListFrom(lowerBounds));
    }
}