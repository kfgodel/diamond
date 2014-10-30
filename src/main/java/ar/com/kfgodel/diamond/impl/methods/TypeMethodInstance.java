package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.sources.modifiers.Mutability;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;

import java.util.Arrays;
import java.util.function.BiFunction;
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
    private Supplier<BiFunction<Object,Object[],Object>> invoker;

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
        return invoker.get().apply(instance, arguments);
    }

    @Override
    public void run() {
        invokeOn(null);
    }

    @Override
    public Object get() {
        return invokeOn(null);
    }

    @Override
    public void accept(Object argumentOrInstance) {
        if(isStatic()){
            invokeOn(null, argumentOrInstance);
        }else{
            invokeOn(argumentOrInstance);
        }
    }

    @Override
    public Object apply(Object argumentOrInstance) {
        if(isStatic()){
            return invokeOn(null, argumentOrInstance);
        }
        return invokeOn(argumentOrInstance);
    }

    @Override
    public void accept(Object argumentOrInstance, Object extraArgument) {
        if(isStatic()){
            invokeOn(null, argumentOrInstance, extraArgument);
        }else{
            invokeOn(argumentOrInstance, extraArgument);
        }
    }

    @Override
    public Object invoke(Object... arguments) {
        if(isStatic()){
            return invokeOn(null, arguments);
        }
        Object[] restArguments = Arrays.copyOfRange(arguments, 1, arguments.length);
        return invokeOn(arguments[0], restArguments);
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
