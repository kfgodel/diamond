package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.impl.types.bounds.NoBounds;

import java.util.stream.Stream;

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
    public Stream<TypeInstance> arguments() {
        return Stream.empty();
    }

    @Override
    public Stream<TypeInstance> parameters() {
        return Stream.empty();
    }
}