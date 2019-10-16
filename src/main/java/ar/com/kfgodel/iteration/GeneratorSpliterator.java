package ar.com.kfgodel.iteration;

import ar.com.kfgodel.nary.api.Nary;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This type represents a spliterator that generates values from previous, using a function.<br>
 * The iteration is done when the function returns an empty optional
 * Created by kfgodel on 13/10/14.
 */
public class GeneratorSpliterator<T> implements Spliterator<T> {

  /**
   * traits of this spliterator used by collections or streams
   */
  private int spliteratorCharacteristics;

  private Nary<? extends T> nextValue;
  private Function<? super T, Nary<? extends T>> generatorFunction;

  @Override
  public boolean tryAdvance(Consumer<? super T> action) {
    if (!nextValue.isPresent()) {
      return false;
    }
    T currentValue = nextValue.get();
    action.accept(currentValue);
    nextValue = generatorFunction.apply(currentValue);
    return true;
  }

  @Override
  public Spliterator<T> trySplit() {
    // We cannot split on this implementation
    return null;
  }

  @Override
  public long estimateSize() {
    //If we don't know the size, contract is to return max value
    return Long.MAX_VALUE;
  }

  @Override
  public int characteristics() {
    return spliteratorCharacteristics;
  }

  /**
   * Creates a new stream from this instance to consume its elements
   *
   * @return The created stream
   */
  public Stream<T> toStream() {
    return StreamSupport.stream(this, false);
  }

  /**
   * Creates a new generator spliterator with given characteristics description
   *
   * @param firstValue      The first iterated value
   * @param generator       The generator function for getting the next value
   * @param characteristics The bitmap describing characteristics of the iteration
   * @param <T>             Type of expected iterated elements
   * @return The created spliterator
   */
  public static <T> GeneratorSpliterator<T> create(Nary<? extends T> firstValue, Function<? super T, Nary<? extends T>> generator, int characteristics) {
    GeneratorSpliterator<T> spliterator = new GeneratorSpliterator<>();
    spliterator.spliteratorCharacteristics = characteristics;
    spliterator.nextValue = firstValue;
    spliterator.generatorFunction = generator;
    return spliterator;
  }

  /**
   * Creates a new generator spliterator with no given characteristics (so iterators cannot assume preconditions)
   *
   * @param firstValue The first iterated value
   * @param generator  The generator function for getting the next value
   * @param <T>        Type of expected iterated elements
   * @return The created spliterator
   */
  public static <T> GeneratorSpliterator<T> create(Nary<? extends T> firstValue, Function<? super T, Nary<? extends T>> generator) {
    return create(firstValue, generator, 0);
  }

  /**
   * Creates a new generator spliterator for one or zero elements to be iterated
   *
   * @param firstValue The first value to iterate (or an empty optional)
   * @param <T>        Type of expected iterated elements
   * @return The created spliterator
   */
  public static <T> GeneratorSpliterator<T> create(Nary<? extends T> firstValue) {
    return create(firstValue, (value) -> Nary.empty());
  }


}
