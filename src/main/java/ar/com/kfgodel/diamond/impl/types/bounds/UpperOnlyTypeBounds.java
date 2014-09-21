package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedType;
import java.util.List;
import java.util.stream.Stream;

/**
 * This type represents a type boundary with only upper bounds (common for type variables)
 * Created by kfgodel on 20/09/14.
 */
public class UpperOnlyTypeBounds implements TypeBounds {

    private List<TypeInstance> upperBounds;

    @Override
    public Stream<TypeInstance> upper() {
        return upperBounds.stream();
    }

    @Override
    public Stream<TypeInstance> lower() {
        return Stream.empty();
    }

    public static UpperOnlyTypeBounds create(AnnotatedType[] upper) {
        UpperOnlyTypeBounds bounds = new UpperOnlyTypeBounds();
        bounds.upperBounds = DoubleTypeBounds.collect(DoubleTypeBounds.streamOf(upper));
        return bounds;
    }

}
