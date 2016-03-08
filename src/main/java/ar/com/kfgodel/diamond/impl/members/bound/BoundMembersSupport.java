package ar.com.kfgodel.diamond.impl.members.bound;

import ar.com.kfgodel.diamond.api.behavior.InstanceBindable;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type serves as base class for sources of bindable types
 * Created by kfgodel on 17/11/14.
 */
public abstract class BoundMembersSupport {

    private Object bindInstance;

    protected void setBindInstance(Object bindInstance) {
        this.bindInstance = bindInstance;
    }

    /**
     * Adds a transformation to the given methods to bind them to the instance
     * @param bindables The methods to bind
     * @return The bound methods
     */
    protected <T> Nary<T> boundVersionOf(Nary<? extends InstanceBindable<T>> bindables) {
        return Nary.create(bindables.map((bindable) -> bindable.bindTo(bindInstance)));
    }

}
