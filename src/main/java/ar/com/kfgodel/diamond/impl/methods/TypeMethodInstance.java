package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;
import ar.com.kfgodel.lazyvalue.api.LazyValue;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents a method that belongs to a type
 * Created by kfgodel on 18/09/14.
 */
public class TypeMethodInstance extends TypeMemberSupport implements TypeMethod {


    private LazyValue<String> methodName;
    private LazyValue<TypeInstance> returnType;
    private LazyValue<List<TypeInstance>> parameterTypes;

    @Override
    public String name() {
        return methodName.get();
    }

    @Override
    public TypeInstance returnType() {
        return returnType.get();
    }

    @Override
    public Stream<TypeInstance> parameterTypes() {
        return parameterTypes.get().stream();
    }

    @Override
    public boolean equals(Object obj) {
        return MethodEquality.INSTANCE.areEquals(this, obj);
    }

    public static TypeMethodInstance create(MethodDescription description) {
        TypeMethodInstance classMethod = new TypeMethodInstance();
        classMethod.methodName = SuppliedValue.from(description.getName());
        classMethod.returnType = SuppliedValue.from(description.getReturnType());
        classMethod.setDeclaringType(description.getDeclaringType());
        // We decide to cache the parameter list
        classMethod.parameterTypes = SuppliedValue.from(() -> description.getParameterTypes().get().collect(Collectors.toList()));
        return classMethod;
    }

}
