package ar.com.kfgodel.diamond.impl.parameters;

import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;

/**
 * This type represents an executable parameter implementation
 * Created by kfgodel on 07/11/14.
 */
public class ParameterImpl implements ExecutableParameter {

    private Supplier<TypeInstance> declaredType;

    @Override
    public TypeInstance declaredType() {
        return declaredType.get();
    }

    public static ParameterImpl create(ParameterDescription description) {
        ParameterImpl parameter = new ParameterImpl();
        parameter.declaredType = description.getDeclaredType();
        return parameter;
    }

}
