package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.stream.Stream;

/**
 * This type represents an unbounded bounds
 * Created by kfgodel on 21/09/14.
 */
public class NoBounds implements TypeBounds {

    public static final NoBounds INSTANCE = NoBounds.create();

    @Override
    public Stream<TypeInstance> upper() {
        return Stream.empty();
    }

    @Override
    public Stream<TypeInstance> lower() {
        return Stream.empty();
    }

    public static NoBounds create() {
        NoBounds bounds = new NoBounds();
        return bounds;
    }

}
