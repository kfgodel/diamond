package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This type represents the spliterator that traverses the superclases of a class
 * Created by kfgodel on 19/09/14.
 */
public class SuperClassTypeSpliterator implements Spliterator<TypeInstance> {

    private TypeInstanceSpliterator internalSpliterator;

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

    public static SuperClassTypeSpliterator create(TypeInstance firstInstance) {
        SuperClassTypeSpliterator spliterator = new SuperClassTypeSpliterator();
        spliterator.internalSpliterator = TypeInstanceSpliterator.create(firstInstance, (instance) -> instance.inheritance().superclass());
        return spliterator;
    }

}
