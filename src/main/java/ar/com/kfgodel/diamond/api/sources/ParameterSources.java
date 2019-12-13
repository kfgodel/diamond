package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Parameter;

/**
 * This type represents the possible sources for a parameter instance
 * Created by kfgodel on 07/11/14.
 */
public interface ParameterSources {
  /**
   * Retrieves the diamond representation of the parameter given as native reflection object
   *
   * @param nativeParameter The native representation of the parameter
   * @return The diamond representation
   */
  ExecutableParameter from(Parameter nativeParameter);

  /**
   * Retrieves the diamond representation for each of the given native parameters
   * @param nativeParameters An array of parameters
   * @return The nary of diamond parameters
   */
  Nary<ExecutableParameter> from(Parameter[] nativeParameters);

  /**
   * Retrieves the parameter described by the given description
   *
   * @param description The parameter description
   * @return The executable parameter
   */
  ExecutableParameter fromDescription(ParameterDescription description);
}
