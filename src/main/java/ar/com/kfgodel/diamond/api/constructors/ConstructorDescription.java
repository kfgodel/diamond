package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.MemberModifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the Diamond description of a constructor abstracting the underlying implementation
 * Created by kfgodel on 15/10/14.
 */
public interface ConstructorDescription {

    /**
     * @return The supplier for the parameter types of the described method
     */
    Supplier<Stream<TypeInstance>> getParameterTypes();

    /**
     * @return The supplier of the type that declared the constructor
     */
    Supplier<TypeInstance> getDeclaringType();

    /**
     * @return The supplier of modifiers applied to the constructor
     */
    Supplier<Stream<MemberModifier>> getModifiers();

    /**
     * @return The supplier of the constructor invoker function
     */
    Supplier<PolymorphicInvokable> getInvoker();

    /**
     * @return The supplier to get the constructor name
     */
    Supplier<String> getName();

    /**
     * @return The supplier for constructor's annotations
     */
    Supplier<Stream<Annotation>> getAnnotations();
}
