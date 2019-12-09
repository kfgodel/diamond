package ar.com.kfgodel.diamond.impl.natives.suppliers;

import ar.com.kfgodel.iteration.GeneratorSpliterator;
import ar.com.kfgodel.nary.api.Nary;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a supplier of inherited members where the provided members are open to an extraction,
 * or mapping operation.<br>
 * By using an instance of this class a supplier can provide native method, fields, constructors, etc
 * Created by kfgodel on 12/10/14.
 */
public class InheritedMemberSupplier<M> implements Supplier<Stream<M>> {

  private Supplier<Nary<Class<?>>> startingClasses;
  private Function<? super Class<?>, M[]> extractionOperation;

  @Override
  public Stream<M> get() {
    return startingClasses.get()
      .flatMap(this::calculateSuperclasses)
      .flatMap(this::extractMembers)
      .distinct();
  }

  private Stream<M> extractMembers(Class<?> superclass) {
    return Arrays.stream(extractionOperation.apply(superclass));
  }

  private Stream<Class<?>> calculateSuperclasses(Class<?> startingClass) {
    final GeneratorSpliterator<Class<?>> spliterator = GeneratorSpliterator.create(Nary.of(startingClass),
      (clazz) -> Nary.of(clazz.getSuperclass()),
      Spliterator.DISTINCT & Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED
    );
    return spliterator.toStream();
  }

  public static <M> InheritedMemberSupplier<M> create(
    Supplier<Nary<Class<?>>> baseClasses,
    Function<? super Class<?>, M[]> extractionOperation
  ) {
    InheritedMemberSupplier<M> supplier = new InheritedMemberSupplier<>();
    supplier.startingClasses = baseClasses;
    supplier.extractionOperation = extractionOperation;
    return supplier;
  }

}
