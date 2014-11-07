package ar.com.kfgodel.diamond.impl.parameters;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents an executable parameter implementation
 * Created by kfgodel on 07/11/14.
 */
public class ParameterInstance implements ExecutableParameter {

    private Supplier<TypeInstance> declaredType;
    private Supplier<String> name;
    private Supplier<Nary<Modifier>> modifiers;

    @Override
    public TypeInstance declaredType() {
        return declaredType.get();
    }

    public static ParameterInstance create(ParameterDescription description) {
        ParameterInstance parameter = new ParameterInstance();
        parameter.declaredType = description.getDeclaredType();
        parameter.name = description.getName();
        parameter.modifiers = description.getModifiers();
        return parameter;
    }

    @Override
    public String name() {
        return name.get();
    }

    @Override
    public Stream<Modifier> modifiers() {
        return modifiers.get();
    }

    @Override
    public boolean is(Modifier expectedModifier) {
        return modifiers().anyMatch(expectedModifier);
    }
}
