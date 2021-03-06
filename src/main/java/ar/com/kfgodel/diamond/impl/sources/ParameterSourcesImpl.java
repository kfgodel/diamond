package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.sources.ParameterSources;
import ar.com.kfgodel.diamond.impl.parameters.ParameterInstance;
import ar.com.kfgodel.diamond.impl.parameters.description.ParameterDescriptor;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Parameter;

/**
 * This type implements the parameter sources type
 * Created by kfgodel on 07/11/14.
 */
public class ParameterSourcesImpl implements ParameterSources {

  private DiamondCache cache;

  public static ParameterSourcesImpl create(DiamondCache cache) {
    ParameterSourcesImpl source = new ParameterSourcesImpl();
    source.cache = cache;
    return source;
  }

  @Override
  public ExecutableParameter from(Parameter nativeParameter) {
    return cache.reuseOrCreateRepresentationFor(nativeParameter, () -> {
      final ParameterDescription description = ParameterDescriptor.INSTANCE.describe(nativeParameter);
      return fromDescription(description);
    });
  }

  @Override
  public Nary<ExecutableParameter> from(Parameter[] nativeParameters) {
    return Nary.from(nativeParameters)
      .map(this::from);
  }

  @Override
  public ExecutableParameter fromDescription(ParameterDescription description) {
    return createParameterFrom(description);
  }

  private ExecutableParameter createParameterFrom(ParameterDescription description) {
    return ParameterInstance.create(description);
  }
}
