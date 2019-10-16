package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the supplier of a type that has no parameters
 * Created by kfgodel on 01/11/14.
 */
public class NoParametersSupplier implements Supplier<Nary<ExecutableParameter>> {

  public static final NoParametersSupplier INSTANCE = new NoParametersSupplier();

  @Override
  public Nary<ExecutableParameter> get() {
    return Nary.empty();
  }
}
