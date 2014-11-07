package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a field defined for a type that can store state
 * Created by kfgodel on 18/09/14.
 */
public interface TypeField extends TypeMember, Consumer<Object>, BiConsumer<Object, Object>, Supplier<Object>, Function<Object,Object>, Invokable {

    /**
     * @return The name of the field
     */
    @Override
    String name();

    /**
     * @return The type of this field storage.<br>
     *     This corresponds to the type declared for this field
     */
    TypeInstance type();

    /**
     * Gets the value stored in the given object under the field represented by this instance
     * @param instance The object to take the value from (null for static fields)
     * @param <R> The expected value type
     * @return The value obtained
     */
    <R> R getValueFrom(Object instance);

    /**
     * Sets the value in the given object under the field represented by this instance
     * @param instance The object to set the value into (null for static fields)
     * @param value The value to set to
     */
    void setValueOn(Object instance, Object value);

    /**
     * Treats this field as a static field, and tries to get its value
     * @return The value from this field as a static field
     */
    @Override
    Object get();

    /**
     * Treats this field as a static field and sets its value to the given argument
     * @param argument The value to set in the static field
     */
    @Override
    void accept(Object argument);

    /**
     * Treats this field as an instance field and gets its value from teh given instance
     * @param instance The instance to read the value from
     * @return The value of this field in the given instance
     */
    @Override
    Object apply(Object instance);

    /**
     * Treats this field as an instance field and sets its value to the given value
     * @param instance The instance to set the value into
     * @param argument The value to set in the instance
     */
    @Override
    void accept(Object instance, Object argument);

    /**
     * @return An empty stream as fields don't have parameters.<br>
     *     This method allows polymorphic compatibility between methods and constructors
     */
    @Override
    Nary<TypeInstance> parameterTypes();

    /**
     * @return An empty stream as fields don't declare exceptions.<br>
     *     This method allows polymorphism between methods and constructors
     */
    @Override
    Nary<TypeInstance> declaredExceptions();
}
