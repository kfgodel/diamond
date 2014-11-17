package ar.com.kfgodel.diamond.api.behavior;

/**
 * This type represents behavior that can be bound to a specific instance
 * Created by kfgodel on 17/11/14.
 */
public interface InstanceBindable<T> {

    /**
     * Binds this element to the given object, making it implicit in every interaction
     * @param instance The instance to bind
     * @return The bound version of this instance
     */
    T bindTo(Object instance);

}

