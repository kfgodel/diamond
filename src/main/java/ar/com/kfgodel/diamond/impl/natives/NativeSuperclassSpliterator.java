package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.iteration.GeneratorSpliterator;

import java.util.Optional;
import java.util.Spliterator;

/**
 * This type represents the spliterator for iterating a native class superclasses
 * Created by kfgodel on 05/10/14.
 */
public class NativeSuperclassSpliterator {

    public static GeneratorSpliterator<Class<?>> create(Class<?> startingClass) {
        return GeneratorSpliterator.create(Optional.of(startingClass),
                (clazz) -> Optional.ofNullable(clazz.getSuperclass()),
                Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED);
    }

}
