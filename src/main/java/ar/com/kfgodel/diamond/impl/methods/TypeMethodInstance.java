package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;
import ar.com.kfgodel.diamond.impl.natives.invokables.InstanceArguments;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a method that belongs to a type
 * Created by kfgodel on 18/09/14.
 */
public class TypeMethodInstance extends TypeMemberSupport implements TypeMethod {


    private Supplier<String> methodName;
    private Supplier<TypeInstance> returnType;
    private Supplier<Stream<TypeInstance>> parameterTypes;
    private Supplier<PolymorphicInvokable> invoker;

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
        return parameterTypes.get();
    }

    @Override
    public Object invokeOn(Object instance, Object... arguments) {
        Object[] invokableArguments  = InstanceArguments.join(instance, arguments);
        return this.invoke(invokableArguments);
    }

    @Override
    public Object invoke(Object... arguments) {
        return invoker.get().invoke(arguments);
    }

    @Override
    public void run() {
        this.invoker.get().run();
    }

    @Override
    public Object get() {
        return this.invoker.get().get();
    }

    @Override
    public void accept(Object argumentOrInstance) {
        this.invoker.get().accept(argumentOrInstance);
    }

    @Override
    public Object apply(Object argumentOrInstance) {
        return this.invoker.get().apply(argumentOrInstance);
    }

    @Override
    public void accept(Object argumentOrInstance, Object extraArgument) {
        this.invoker.get().accept(argumentOrInstance, extraArgument);
    }

    @Override
    public boolean test(Object argumentOrInstance) {
        return this.invoker.get().test(argumentOrInstance);
    }

    @Override
    public boolean equals(Object obj) {
        return MethodEquality.INSTANCE.areEquals(this, obj);
    }

    public static TypeMethodInstance create(MethodDescription description) {
        TypeMethodInstance method = new TypeMethodInstance();
        method.methodName = description.getName();
        method.returnType = description.getReturnType();
        method.setDeclaringType(description.getDeclaringType());
        method.setModifiers(description.getModifiers());
        method.parameterTypes = description.getParameterTypes();
        method.invoker = description.getInvoker();
        return method;
    }

}
