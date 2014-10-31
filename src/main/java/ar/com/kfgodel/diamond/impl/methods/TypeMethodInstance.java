package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.sources.modifiers.Mutability;
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
    private Supplier<Invokable> invoker;

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
    public void run() {
        this.invoke();
    }

    @Override
    public Object get() {
        return this.invoke();
    }

    @Override
    public void accept(Object argumentOrInstance) {
        this.invoke(argumentOrInstance);
    }

    @Override
    public Object apply(Object argumentOrInstance) {
        return this.invoke(argumentOrInstance);
    }

    @Override
    public void accept(Object argumentOrInstance, Object extraArgument) {
        this.invoke(argumentOrInstance, extraArgument);
    }

    @Override
    public Object invoke(Object... arguments) {
        return invoker.get().invoke(arguments);
    }

    /**
     * Indicates if this instance represents a static method
     * @return true if this method doesn't require an instance to be passed to be called
     */
    private boolean isStatic(){
        return modifiers().anyMatch(Mutability.STATIC);
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
