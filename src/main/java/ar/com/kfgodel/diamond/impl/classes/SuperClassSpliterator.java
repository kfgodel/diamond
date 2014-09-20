package ar.com.kfgodel.diamond.impl.classes;

import ar.com.kfgodel.diamond.api.ClassInstance;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This type represents the spliterator that traverses the superclases of a class
 * Created by kfgodel on 19/09/14.
 */
public class SuperClassSpliterator implements Spliterator<ClassInstance> {

    private Optional<ClassInstance> optionalInstance;

    @Override
    public boolean tryAdvance(Consumer<? super ClassInstance> action) {
        if(!optionalInstance.isPresent()){
            return false;
        }
        ClassInstance currentInstance = optionalInstance.get();
        action.accept(currentInstance);
        optionalInstance = currentInstance.getSuperclass();
        return true;
    }

    @Override
    public Spliterator<ClassInstance> trySplit() {
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

    public static SuperClassSpliterator create(ClassInstance firstInstance) {
        SuperClassSpliterator spliterator = new SuperClassSpliterator();
        spliterator.optionalInstance = Optional.of(firstInstance);
        return spliterator;
    }

}
