package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the generics information of a bounded type
 * Created by kfgodel on 05/10/14.
 */
public class BoundedTypeGenerics implements TypeGenerics {

    private LazyValue<TypeBounds> typeBounds;

    @Override
    public TypeBounds bounds() {
        return typeBounds.get();
    }

    @Override
    public Stream<TypeInstance> typeArguments() {
        return NotGenerified.INSTANCE.typeArguments();
    }

    @Override
    public Stream<TypeInstance> typeParameters() {
        return NotGenerified.INSTANCE.typeParameters();
    }

    public static BoundedTypeGenerics create(Supplier<TypeBounds> bounds) {
        BoundedTypeGenerics generics = new BoundedTypeGenerics();
        generics.typeBounds = SuppliedValue.fromLazy(bounds);
        return generics;
    }


}
