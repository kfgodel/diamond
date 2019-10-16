package ar.com.kfgodel.diamond.impl.parameters.description;

import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;

import java.lang.reflect.Parameter;

/**
 * This type represents the descriptor of native parameters
 * Created by kfgodel on 07/11/14.
 */
public class ParameterDescriptor {

  public static final ParameterDescriptor INSTANCE = ParameterDescriptor.create();

  /**
   * Creates a description of the given native parameter based on its features
   *
   * @param nativeParameter The parameter to describe
   * @return The description for diamond
   */
  public ParameterDescription describe(Parameter nativeParameter) {
    return NativeParameterDescription.create(nativeParameter);
  }

  public static ParameterDescriptor create() {
    ParameterDescriptor descriptor = new ParameterDescriptor();
    return descriptor;
  }

}
