package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.List;
import java.util.stream.Stream;

/**
 * This type represents the bounds of a type that has upper and lower boundaries
 * Created by kfgodel on 20/09/14.
 */
public class DoubleTypeBounds implements TypeBounds {

    private List<TypeInstance> upperBounds;
    private List<TypeInstance> lowerBounds;

    @Override
    public Stream<TypeInstance> upper() {
        return upperBounds.stream();
    }

    @Override
    public Stream<TypeInstance> lower() {
        return lowerBounds.stream();
    }

    public static DoubleTypeBounds create(List<TypeInstance> upper, List<TypeInstance> lower) {
        DoubleTypeBounds bounds = new DoubleTypeBounds();
        bounds.upperBounds = upper;
        bounds.lowerBounds = lower;
        return bounds;
    }

}
