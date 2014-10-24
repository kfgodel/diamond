package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the description of a diamond field that can be used to get a {@link TypeField}
 * Created by kfgodel on 12/10/14.
 */
public interface FieldDescription {
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
     * @return The supplier to get the field modifiers
     */
    Supplier<Stream<MemberModifier>> getModifiers();

    /**
     * @return The supplier of the consumer to set the field's value
     */
    Supplier<BiConsumer<Object,Object>> getSetter();

    /**
     * @return The supplier of the function to get the field's value
     */
    Supplier<Function<Object,?>> getGetter();
}
