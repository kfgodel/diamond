package ar.com.kfgodel.diamond.impl.classes;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * This type represents the spliterator for native class superclasses
 * Created by kfgodel on 05/10/14.
 */
public class SuperclassSpliterator implements Spliterator<Class<?>> {

    private Class<?> currentClass;

    @Override
    public boolean tryAdvance(Consumer<? super Class<?>> action) {
        if(currentClass == null){
            //We reached top
            return false;
        }
        action.accept(currentClass);
        currentClass = currentClass.getSuperclass();
        return true;
    }

    @Override
    public Spliterator<Class<?>> trySplit() {
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

    public static SuperclassSpliterator create(Class<?> startingClass) {
        SuperclassSpliterator spliterator = new SuperclassSpliterator();
        spliterator.currentClass = startingClass;
        return spliterator;
    }

}
