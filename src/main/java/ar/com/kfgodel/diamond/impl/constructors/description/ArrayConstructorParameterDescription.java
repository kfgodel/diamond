package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.util.function.Supplier;

/**
 * This type represents the description of the parameter needed for the artificial array constructor
 * Created by kfgodel on 07/11/14.
 */
public class ArrayConstructorParameterDescription implements ParameterDescription {

    public static ArrayConstructorParameterDescription INSTANCE = new ArrayConstructorParameterDescription();

    @Override
    public Supplier<TypeInstance> getDeclaredType() {
        return CachedValue.lazilyBy(()-> Diamond.of(int.class));
    }

    public static ArrayConstructorParameterDescription create() {
        ArrayConstructorParameterDescription description = new ArrayConstructorParameterDescription();
        return description;
    }

}
