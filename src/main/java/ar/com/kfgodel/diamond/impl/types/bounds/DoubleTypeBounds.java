package ar.com.kfgodel.diamond.impl.types.bounds;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    public static DoubleTypeBounds create(AnnotatedType[] upper, AnnotatedType[] lower) {
        return create(streamOf(upper),streamOf(lower));
    }

    public static DoubleTypeBounds create(Type[] upper, Type[] lower) {
        return create(streamOf(upper), streamOf(lower));
    }

    public static DoubleTypeBounds create(Stream<TypeInstance> upper, Stream<TypeInstance> lower) {
        DoubleTypeBounds bounds = new DoubleTypeBounds();
        bounds.upperBounds = collect(upper);
        bounds.lowerBounds = collect(lower);
        return bounds;
    }

    public static Stream<TypeInstance> streamOf(AnnotatedType[] annotatedTypes) {
        return Arrays.stream(annotatedTypes).map((annotated) -> Diamond.types().fromUnspecific(annotated));
    }

    public static Stream<TypeInstance> streamOf(Type[] types) {
        return Arrays.stream(types).map((type) -> Diamond.types().fromUnspecific(type));
    }

    public static List<TypeInstance> collect(Stream<TypeInstance> upper) {
        return upper.collect(Collectors.toList());
    }

}
