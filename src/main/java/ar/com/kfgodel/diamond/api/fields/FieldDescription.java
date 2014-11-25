package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the description of a diamond field that can be used to get a {@link TypeField}
 * Created by kfgodel on 12/10/14.
 */
public interface FieldDescription extends MemberDescription {
    /**
     * @return The name to identify the field
     */
    Supplier<String> getName();

    /**
     * @return The type of the field storage
     */
    Supplier<TypeInstance> getType();

    /**
     * @return The type in which the field is declared
     */
    Supplier<TypeInstance> getDeclaringType();

    /**
     * @return The supplier of the functional behavior of the field (get and set operations)
     */
    Supplier<PolymorphicInvokable> getInvoker();

    /**
     * @return The supplier of this field annotations
     */
    Supplier<Nary<Annotation>> getAnnotations();

    /**
     * @return The supplier of the setter function to set the field value
     */
    Supplier<BiConsumer<Object,Object>> getSetter();

    /**
     * @return The supplier of the getter function for the field
     */
    Supplier<Function<Object,Object>> getGetter();

    /**
     * @return The supplier of the fields native representation
     */
    Supplier<Nary<Field>> getNativeField();

    /**
     * @return The token that represents this field
     */
    Function<TypeField,Object> getIdentityToken();
}
