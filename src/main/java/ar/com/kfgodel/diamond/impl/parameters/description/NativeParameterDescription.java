package ar.com.kfgodel.diamond.impl.parameters.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.reflect.Parameter;
import java.util.function.Supplier;

/**
 * This type represents the description of a native parameter instance for diamond
 * Created by kfgodel on 07/11/14.
 */
public class NativeParameterDescription implements ParameterDescription {

    private Parameter nativeParameter;

    @Override
    public Supplier<TypeInstance> getDeclaredType() {
        return CachedValue.lazilyBy(() -> Diamond.types().from(nativeParameter.getAnnotatedType()));
    }

    @Override
    public Supplier<String> getName() {
        return nativeParameter::getName;
    }

    public static NativeParameterDescription create(Parameter nativeParameter) {
        NativeParameterDescription description = new NativeParameterDescription();
        description.nativeParameter = nativeParameter;
        return description;
    }

}
