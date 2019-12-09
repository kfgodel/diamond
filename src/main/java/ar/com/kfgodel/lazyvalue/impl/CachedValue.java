package ar.com.kfgodel.lazyvalue.impl;

import java.util.function.Supplier;

/**
 * This type represents a supplier defined value that is delayed until needed
 * Created by kfgodel on 21/09/14.
 */
public class CachedValue<T> implements Supplier<T> {

  private Supplier<T> valueGenerator;
  private T value;

  @Override
  public T get() {
    if (stillHasGenerator()) {
      // Generate only once
      value = valueGenerator.get();
      discardGenerator();
    }
    return value;
  }

  private void discardGenerator() {
    valueGenerator = null;
  }

  private boolean stillHasGenerator() {
    return valueGenerator != null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
    builder.append("{ value: ");
    if (stillHasGenerator()) {
      builder.append("undefined");
    } else {
      builder.append(value);
    }
    builder.append("}");
    return builder.toString();
  }

  /**
   * Creates a lazily defined value through a supplier to generate it the first time
   *
   * @param valueGenerator The value generator to be called the first time
   * @param <T>            The expect type of value
   * @return The created instance
   */
  public static <T> Supplier<T> from(Supplier<T> valueGenerator) {
    CachedValue<T> value = new CachedValue<>();
    value.valueGenerator = valueGenerator;
    return value;
  }

}
