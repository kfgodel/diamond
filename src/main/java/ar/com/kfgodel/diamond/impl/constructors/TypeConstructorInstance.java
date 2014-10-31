package ar.com.kfgodel.diamond.impl.constructors;

import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.constructors.equality.ConstructorEquality;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents an instance of type constructor
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorInstance extends TypeMemberSupport implements TypeConstructor {

    private Supplier<Stream<TypeInstance>> parameterTypes;
    private Supplier<Invokable> invoker;

    @Override
    public Stream<TypeInstance> parameterTypes() {
        return parameterTypes.get();
    }

    @Override
    public Object invoke(Object... arguments) {
        return invoker.get().invoke(arguments);
    }

    @Override
    public boolean equals(Object obj) {
        return ConstructorEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public Object get() {
        return invoke();
    }

    @Override
    public Object apply(Object argument) {
        return invoke(argument);
    }


    public static TypeConstructor create(ConstructorDescription description) {
        TypeConstructorInstance constructor = new TypeConstructorInstance();
        constructor.setDeclaringType(description.getDeclaringType());
        constructor.setModifiers(description.getModifiers());
        constructor.parameterTypes = description.getParameterTypes();
        constructor.invoker = description.getInvoker();
        return constructor;
    }

}
