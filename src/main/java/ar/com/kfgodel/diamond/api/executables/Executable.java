package ar.com.kfgodel.diamond.api.executables;

import ar.com.kfgodel.diamond.api.behavior.ParameterizedBehavior;
import ar.com.kfgodel.diamond.api.exceptions.Exceptionable;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type represents an executable type (diamond version of native Executable).<br>
 * The executable represents the meta-information of an invokable type (parameters, return type, exceptions, etc)
 * <p>
 * Created by kfgodel on 07/11/14.
 */
public interface Executable extends ParameterizedBehavior, Exceptionable {

  /**
   * @return This member usable as a function with semantics and result depending on this instance, and the used arguments
   */
  PolymorphicInvokable asFunction();

  /**
   * Returns the parameters needed by this instance to be invoked.<br>
   * Depending on the instance definition this may be 0, 1, or N parameters
   *
   * @return The set of parameters (in the needed order)
   */
  Nary<ExecutableParameter> parameters();

  /**
   * @return The instance that represents the return type of this executable.<br>
   */
  TypeInstance returnType();

}
