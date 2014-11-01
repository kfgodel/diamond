package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the generics information of a bounded type
 * Created by kfgodel on 05/10/14.
 */
public class BoundedTypeGenerics implements TypeGenerics {

    private Supplier<TypeBounds> typeBounds;

    @Override
    public TypeBounds bounds() {
        return typeBounds.get();
    }

    @Override
    public Stream<TypeInstance> arguments() {
        return UnGenerifiedTypeGenerics.INSTANCE.arguments();
    }

    @Override
    public Stream<TypeInstance> parameters() {
        return UnGenerifiedTypeGenerics.INSTANCE.parameters();
    }

    public static BoundedTypeGenerics create(Supplier<TypeBounds> bounds) {
        BoundedTypeGenerics generics = new BoundedTypeGenerics();
        generics.typeBounds = bounds;
        return generics;
    }


}
