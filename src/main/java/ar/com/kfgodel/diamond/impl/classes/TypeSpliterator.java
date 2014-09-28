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
public class TypeSpliterator<T extends TypeInstance> implements Spliterator<T> {

    private Optional<? extends T> optionalInstance;
    private Function<? super T, Optional<? extends T>> advanceOperation;

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        if(!optionalInstance.isPresent()){
            return false;
        }
        T currentInstance = optionalInstance.get();
        action.accept(currentInstance);
        optionalInstance = advanceOperation.apply(currentInstance);
        return true;
    }

    @Override
    public Spliterator<T> trySplit() {
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

    public static<T extends TypeInstance> TypeSpliterator<T> create(T firstInstance, Function<? super T, Optional<? extends T>> advanceOperation ) {
        TypeSpliterator<T> spliterator = new TypeSpliterator<>();
        spliterator.optionalInstance = Optional.of(firstInstance);
        spliterator.advanceOperation = advanceOperation;
        return spliterator;
    }
}
