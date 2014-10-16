package ar.com.kfgodel.diamond.impl.constructors;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents an instance of type constructor
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorInstance implements TypeConstructor {

    private LazyValue<List<TypeInstance>> parameterTypes;

    @Override
    public Stream<TypeInstance> parameterTypes() {
        return parameterTypes.get().stream();
    }

    public static TypeConstructor create(ConstructorDescription description) {
        TypeConstructorInstance classMethod = new TypeConstructorInstance();
        // We decide to cache the parameter list
        classMethod.parameterTypes = SuppliedValue.create(() -> description.getParameterTypes().get().collect(Collectors.toList()));
        return classMethod;
    }
}
