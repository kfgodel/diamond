package ar.com.kfgodel.diamond.impl.types.parts.typearguments;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the supplier for a type that has been extended with actual type arguments
 * Created by kfgodel on 29/09/14.
 */
public class ExtendedTypeArgumentsSupplier implements Supplier<Nary<TypeInstance>> {

  private Supplier<Nary<TypeInstance>> originalSupplier;
  private Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer;

  @Override
  public Nary<TypeInstance> get() {
    List<TypeInstance> originalTypeArguments = originalSupplier.get().collect(Collectors.toList());
    extendedTypeArgumentsReplacer.accept(originalTypeArguments);
    return Nary.create(originalTypeArguments);
  }

  public static ExtendedTypeArgumentsSupplier create(Supplier<Nary<TypeInstance>> originalSupplier, Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer) {
    ExtendedTypeArgumentsSupplier supplier = new ExtendedTypeArgumentsSupplier();
    supplier.extendedTypeArgumentsReplacer = extendedTypeArgumentsReplacer;
    supplier.originalSupplier = originalSupplier;
    return supplier;
  }

}
