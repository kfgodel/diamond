package ar.com.kfgodel.diamond.api.members;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the common type member description elements
 * Created by kfgodel on 01/11/14.
 */
public interface MemberDescription {

    /**
     * @return The supplier for the parameter types of the described member
     */
    Supplier<Stream<TypeInstance>> getParameterTypes();

    /**
     * @return The supplier of the type that declared the member
     */
    Supplier<TypeInstance> getDeclaringType();

    /**
     * @return The supplier of modifiers applied to the member
     */
    Supplier<Stream<MemberModifier>> getModifiers();

    /**
     * @return The supplier of the member invoker function
     */
    Supplier<PolymorphicInvokable> getInvoker();

    /**
     * @return The supplier to get the member name
     */
    Supplier<String> getName();

    /**
     * @return The supplier for member's annotations
     */
    Supplier<Stream<Annotation>> getAnnotations();

    /**
     * @return The supplier of member's generics information
     */
    Supplier<Generics> getGenerics();

}