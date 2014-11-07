package ar.com.kfgodel.diamond.impl.parameters;

import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;

/**
 * This type represents an executable parameter implementation
 * Created by kfgodel on 07/11/14.
 */
public class ParameterInstance implements ExecutableParameter {

    private Supplier<TypeInstance> declaredType;
    private Supplier<String> name;

    @Override
    public TypeInstance declaredType() {
        return declaredType.get();
    }

    public static ParameterInstance create(ParameterDescription description) {
        ParameterInstance parameter = new ParameterInstance();
        parameter.declaredType = description.getDeclaredType();
        parameter.name = description.getName();
        return parameter;
    }

    @Override
    public String name() {
        return name.get();
    }
}
