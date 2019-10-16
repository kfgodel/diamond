package ar.com.kfgodel.diamond.impl.natives.suppliers;

import ar.com.kfgodel.diamond.impl.natives.NativeSuperclassSpliterator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a supplier of inherited members where the provided members are open to an extraction,
 * or mapping operation.<br>
 * By using an instance of this class a supplier can provide native method, fields, constructors, etc
 * Created by kfgodel on 12/10/14.
 */
public class InheritedMemberSupplier<M> implements Supplier<Stream<M>> {

  private Set<Class<?>> baseClasses;
  private Function<? super Class<?>, Stream<? extends M>> extractionOperation;

  @Override
  public Stream<M> get() {
    LinkedHashSet<M> nonDuplicateMethods = baseClasses.stream()
      .flatMap((baseClass) -> NativeSuperclassSpliterator.create(baseClass).toStream())
      .flatMap(extractionOperation)
      .collect(Collectors.toCollection(LinkedHashSet::new));
    return nonDuplicateMethods.stream();
  }

  public static <M> InheritedMemberSupplier<M> create(Set<Class<?>> baseClasses, Function<? super Class<?>, Stream<? extends M>> extractionOperation) {
    InheritedMemberSupplier<M> supplier = new InheritedMemberSupplier<>();
    supplier.baseClasses = baseClasses;
    supplier.extractionOperation = extractionOperation;
    return supplier;
  }

}
