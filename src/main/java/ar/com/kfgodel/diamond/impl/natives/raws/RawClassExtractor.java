package ar.com.kfgodel.diamond.impl.natives.raws;

import java.util.Set;
import java.util.function.Function;

/**
 * This interface defines the contract for a function that takes a type argument and knows how to extract the
 * raw classes that define its behavior in runtime.<br>
 *
 * Date: 27/11/19 - 20:29
 */
public interface RawClassExtractor<T> extends Function<T, Set<Class<?>>> {
}
