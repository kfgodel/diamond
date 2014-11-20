package ar.com.kfgodel.diamond.impl.methods;

import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.TypeMemberSupport;
import ar.com.kfgodel.diamond.impl.members.call.BehaviorCallInstance;
import ar.com.kfgodel.diamond.impl.methods.bound.BoundMethodInstance;
import ar.com.kfgodel.diamond.impl.methods.declaration.MethodDeclaration;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;
import ar.com.kfgodel.diamond.impl.natives.invokables.InstanceArguments;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * This type represents a method that belongs to a type
 * Created by kfgodel on 18/09/14.
 */
public class TypeMethodInstance extends TypeMemberSupport implements TypeMethod {

    private Supplier<TypeInstance> returnType;
    private Supplier<Nary<Object>> defaultValue;
    private Supplier<Nary<Method>> nativeMethod;

    @Override
    public TypeInstance returnType() {
        return returnType.get();
    }

    @Override
    public Object invokeOn(Object instance, Object... arguments) {
        Object[] invokableArguments  = InstanceArguments.join(instance, arguments);
        return this.invoke(invokableArguments);
    }

    @Override
    public Object invoke(Object... arguments) {
        return asFunction().invoke(arguments);
    }

    @Override
    public void run() {
        asFunction().run();
    }

    @Override
    public Object get() {
        return asFunction().get();
    }

    @Override
    public void accept(Object argumentOrInstance) {
        asFunction().accept(argumentOrInstance);
    }

    @Override
    public Object apply(Object argumentOrInstance) {
        return asFunction().apply(argumentOrInstance);
    }

    @Override
    public void accept(Object argumentOrInstance, Object extraArgument) {
        asFunction().accept(argumentOrInstance, extraArgument);
    }

    @Override
    public boolean test(Object argumentOrInstance) {
        return asFunction().test(argumentOrInstance);
    }

    @Override
    public boolean equals(Object obj) {
        return MethodEquality.INSTANCE.areEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return MethodEquality.INSTANCE.hashcodeFor(this);
    }

    public static TypeMethodInstance create(MethodDescription description) {
        TypeMethodInstance method = new TypeMethodInstance();
        method.initialize(description);
        method.nativeMethod = description.getNativeMethod();
        method.returnType = description.getReturnType();
        method.defaultValue = description.getDefaultValue();
        return method;
    }

    @Override
    public String declaration() {
        return MethodDeclaration.create(this).asString();
    }

    @Override
    public Nary<Object> defaultValue() {
        return defaultValue.get();
    }

    @Override
    public BoundMethod bindTo(Object instance) {
        return BoundMethodInstance.create(this, instance);
    }

    @Override
    public BehaviorCall withArguments(Object... arguments) {
        return BehaviorCallInstance.create(this, arguments);
    }

    @Override
    public Nary<Method> nativeType() {
        return nativeMethod.get();
    }
}
