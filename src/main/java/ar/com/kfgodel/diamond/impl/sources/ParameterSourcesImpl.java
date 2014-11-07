package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.sources.ParameterSources;
import ar.com.kfgodel.diamond.impl.parameters.ParameterInstance;
import ar.com.kfgodel.diamond.impl.parameters.description.ParameterDescriptor;

import java.lang.reflect.Parameter;

/**
 * This type implements the parameter sources type
 * Created by kfgodel on 07/11/14.
 */
public class ParameterSourcesImpl implements ParameterSources {

    public static ParameterSourcesImpl create() {
        ParameterSourcesImpl parameterSources = new ParameterSourcesImpl();
        return parameterSources;
    }

    @Override
    public ExecutableParameter from(Parameter nativeParameter) {
        // Cache here
        ParameterDescription description = ParameterDescriptor.INSTANCE.describe(nativeParameter);
        return from(description);
    }

    @Override
    public ExecutableParameter from(ParameterDescription description) {
        return createParameterFrom(description);
    }

    private ExecutableParameter createParameterFrom(ParameterDescription description) {
        return ParameterInstance.create(description);
    }
}
