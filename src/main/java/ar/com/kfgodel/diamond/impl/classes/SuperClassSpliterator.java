package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.ClassInstance;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This type represents the spliterator that traverses the superclases of a class
 * Created by kfgodel on 19/09/14.
 */
public class SuperClassSpliterator implements Spliterator<ClassInstance> {

    private TypeSpliterator<ClassInstance> internalSpliterator;

    @Override
    public boolean tryAdvance(Consumer<? super ClassInstance> action) {
        return internalSpliterator.tryAdvance(action);
    }

    @Override
    public Spliterator<ClassInstance> trySplit() {
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

    public static SuperClassSpliterator create(ClassInstance firstInstance) {
        SuperClassSpliterator spliterator = new SuperClassSpliterator();
        spliterator.internalSpliterator = TypeSpliterator.create(firstInstance, (instance)-> instance.superclass());
        return spliterator;
    }

}
