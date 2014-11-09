package ar.com.kfgodel.diamond.api.methods;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents the description of a diamond method to get a {@link TypeMethod}
 * Created by kfgodel on 07/10/14.
 */
public interface MethodDescription extends MemberDescription {

    /**
     * @return The supplier for getting the method name
     */
    Supplier<String> getName();

    /**
     * @return The supplier for the return type of the described method
     */
    Supplier<TypeInstance> getReturnType();

    /**
     * @return the supplier to get the type that declares the method
     */
    Supplier<TypeInstance> getDeclaringType();

    /**
     * @return The supplier of the method invoker to be able to call it
     */
    Supplier<PolymorphicInvokable> getInvoker();

    /**
     * @return The supplier for the method annotations
     */
    Supplier<Nary<Annotation>> getAnnotations();

    /**
     * @return The supplier of method generics information
     */
    Supplier<Generics> getGenerics();

    /**
     * @return The supplier of the method's default value
     */
    Supplier<Nary<Object>> getDefaultValue();
}
