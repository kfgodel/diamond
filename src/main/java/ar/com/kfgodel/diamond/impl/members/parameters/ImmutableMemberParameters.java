package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromCollection;

import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the immutable parameters list supplier for Executable instances
 * Created by kfgodel on 01/11/14.
 */
public class ImmutableMemberParameters {

  public static Supplier<Nary<ExecutableParameter>> create(Executable nativeExecutable) {
    return NarySupplierFromCollection.lazilyBy(() -> Arrays.stream(nativeExecutable.getParameters())
      .map((nativeParameter) -> Diamond.parameters().from(nativeParameter))
      .collect(Collectors.toList()));
  }

}
