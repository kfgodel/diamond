package ar.com.kfgodel.diamond.impl.types.iteration;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.iteration.GeneratorSpliterator;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.Spliterator;
import java.util.function.Function;

/**
 * This type represents a type spliterator that traverses types in a linear fashion
 * Created by kfgodel on 28/09/14.
 */
public class TypeInstanceSpliterator {

    public static Spliterator<TypeInstance> create(TypeInstance firstInstance, Function<? super TypeInstance, Nary<? extends TypeInstance>> advanceOperation ) {
        return GeneratorSpliterator.create(NaryFromNative.of(firstInstance), advanceOperation, Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED);
    }
}
