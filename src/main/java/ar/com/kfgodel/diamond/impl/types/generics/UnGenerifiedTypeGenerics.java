package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

/**
 * This type represents the generics information for an ungenerified type
 * Created by kfgodel on 05/10/14.
 */
public class UnGenerifiedTypeGenerics implements TypeGenerics {

    public static final UnGenerifiedTypeGenerics INSTANCE = new UnGenerifiedTypeGenerics();

    /**
     * Default implementation with no bounds
     * @return A no bounds instance
     */
    @Override
    public TypeBounds bounds() {
        return NoBounds.INSTANCE;
    }

    /**
     * Default implementation with no arguments
     * @return An empty stream
     */
    @Override
    public Nary<TypeInstance> arguments() {
        return NaryFromNative.empty();
    }

    @Override
    public Nary<TypeInstance> parameters() {
        return NaryFromNative.empty();
    }
}
