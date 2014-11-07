package ar.com.kfgodel.diamond.impl.natives;

import ar.com.kfgodel.iteration.GeneratorSpliterator;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.Spliterator;

/**
 * This type represents the spliterator for iterating a native class superclasses
 * Created by kfgodel on 05/10/14.
 */
public class NativeSuperclassSpliterator {

    public static GeneratorSpliterator<Class<?>> create(Class<?> startingClass) {
        return GeneratorSpliterator.create(NaryFromNative.of(startingClass),
                (clazz) -> NaryFromNative.ofNullable(clazz.getSuperclass()),
                Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED);
    }

}
