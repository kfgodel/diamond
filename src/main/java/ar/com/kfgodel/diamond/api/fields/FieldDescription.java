package ar.com.kfgodel.diamond.api.fields;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
     * @return The supplier to get the field modifiers
     */
    Supplier<Stream<MemberModifier>> getModifiers();

    /**
     * @return The supplier of the functional behavior of the field (get and set operations)
     */
    Supplier<PolymorphicInvokable> getInvoker();

    /**
     * @return The supplier of this field annotations
     */
    Supplier<Stream<Annotation>> getAnnotations();
}
