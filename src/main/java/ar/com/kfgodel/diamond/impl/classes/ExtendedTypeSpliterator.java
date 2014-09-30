package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This type represents a spliterator that traverses the extended types of a type
 * Created by kfgodel on 28/09/14.
 */
public class ExtendedTypeSpliterator implements Spliterator<TypeInstance> {

    private TypeSpliterator<TypeInstance> internalSpliterator;

    @Override
    public boolean tryAdvance(Consumer<? super TypeInstance> action) {
        return internalSpliterator.tryAdvance(action);
    }

    @Override
    public Spliterator<TypeInstance> trySplit() {
        return internalSpliterator.trySplit();
    }

    @Override
    public long estimateSize() {
        return internalSpliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return internalSpliterator.characteristics();
    }

    public static ExtendedTypeSpliterator create(TypeInstance firstInstance) {
        ExtendedTypeSpliterator spliterator = new ExtendedTypeSpliterator();
        spliterator.internalSpliterator = TypeSpliterator.create(firstInstance, (instance)-> instance.extendedType());
        return spliterator;
    }
}
