package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedVariableTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.bounds.WildcardBoundsSupplier;

import java.lang.reflect.WildcardType;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated native wildcard type
 * Created by kfgodel on 28/09/14.
 */
public class WildcardTypeDescription extends UnannotatedVariableTypeDescriptionSupport {

    private WildcardType nativeType;

    @Override
    protected Object getVariableType() {
        return nativeType;
    }

    @Override
    public Supplier<TypeBounds> getBounds() {
        return WildcardBoundsSupplier.create(nativeType);
    }

    public static WildcardTypeDescription create(WildcardType nativeType) {
        WildcardTypeDescription description = new WildcardTypeDescription();
        description.nativeType = nativeType;
        return description;
    }

}
