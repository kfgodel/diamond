package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;

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
   * Retrieves the parameter described by the given description
   *
   * @param description The parameter description
   * @return The executable parameter
   */
  ExecutableParameter fromDescription(ParameterDescription description);
}
