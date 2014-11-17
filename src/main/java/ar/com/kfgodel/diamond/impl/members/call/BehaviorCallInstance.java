package ar.com.kfgodel.diamond.impl.members.call;

import ar.com.kfgodel.diamond.api.members.TypeBehavior;
import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;

/**
 * This type represents a behavior call
 * Created by kfgodel on 17/11/14.
 */
public class BehaviorCallInstance implements BehaviorCall {

    private Object[] arguments;
    private TypeBehavior behavior;

    public static BehaviorCallInstance create(TypeBehavior behavior, Object[] arguments) {
        BehaviorCallInstance callInstance = new BehaviorCallInstance();
        callInstance.behavior = behavior;
        callInstance.arguments = arguments;
        return callInstance;
    }

    @Override
    public <R> R invokeOn(Object instance) {
        return (R) behavior.invokeOn(instance, arguments);
    }

    @Override
    public <R> R invoke() {
        return (R) behavior.invoke(arguments);
    }

    @Override
    public TypeBehavior boundBehavior() {
        return behavior;
    }

    @Override
    public Object[] arguments() {
        return arguments;
    }

    @Override
    public void accept(Object instance) {
        invokeOn(instance);
    }

    @Override
    public Object apply(Object instance) {
        return invokeOn(instance);
    }

    @Override
    public void run() {
        invoke();
    }

    @Override
    public Object get() {
        return invoke();
    }
}
