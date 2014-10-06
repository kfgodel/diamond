package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This type represents a type spliterator that traverses types in a linear fashion
 * Created by kfgodel on 28/09/14.
 */
public class TypeInstanceSpliterator implements Spliterator<TypeInstance> {

    private Optional<? extends TypeInstance> optionalInstance;
    private Function<? super TypeInstance, Optional<? extends TypeInstance>> advanceOperation;

    @Override
    public boolean tryAdvance(Consumer<? super TypeInstance> action) {
        if(!optionalInstance.isPresent()){
            return false;
        }
        TypeInstance currentInstance = optionalInstance.get();
        action.accept(currentInstance);
        optionalInstance = advanceOperation.apply(currentInstance);
        return true;
    }

    @Override
    public Spliterator<TypeInstance> trySplit() {
        // We cannot split on this implementation
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED;
    }

    public static  TypeInstanceSpliterator create(TypeInstance firstInstance, Function<? super TypeInstance, Optional<? extends TypeInstance>> advanceOperation ) {
        TypeInstanceSpliterator spliterator = new TypeInstanceSpliterator();
        spliterator.optionalInstance = Optional.of(firstInstance);
        spliterator.advanceOperation = advanceOperation;
        return spliterator;
    }
}
