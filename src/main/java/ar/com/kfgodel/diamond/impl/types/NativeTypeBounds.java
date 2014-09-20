package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeBounds;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the bounds of a type
 * Created by kfgodel on 20/09/14.
 */
public class NativeTypeBounds implements TypeBounds {

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

    public static NativeTypeBounds create(AnnotatedType[] upper, AnnotatedType[] lower) {
        NativeTypeBounds bounds = new NativeTypeBounds();
        bounds.upperBounds = adapt(upper);
        bounds.lowerBounds = adapt(lower);
        return bounds;
    }

    /**
     * @param upper The array of native types
     * @return The list of diamond types
     */
    private static List<TypeInstance> adapt(AnnotatedType[] upper) {
        if(upper == null){
            return Collections.emptyList();
        }
        return Arrays.stream(upper)
                .map((type)-> Diamond.types().fromUnspecific(type))
                .collect(Collectors.toList());
    }

}
