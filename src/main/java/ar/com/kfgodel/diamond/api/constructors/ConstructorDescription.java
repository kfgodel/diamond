package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.MemberDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * This type represents the Diamond description of a constructor abstracting the underlying implementation
 * Created by kfgodel on 15/10/14.
 */
public interface ConstructorDescription extends MemberDescription {

    /**
     * @return The supplier of the type that declared the constructor
     */
    Supplier<TypeInstance> getDeclaringType();

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
    Supplier<Nary<Annotation>> getAnnotations();

    /**
     * @return The supplier of constructor's generics information
     */
    Supplier<Generics> getGenerics();

    /**
     * @return tHte supplier of the constructor native representation
     */
    Supplier<Nary<Constructor>> getNativeConstructor();

    Function<TypeConstructor,Object> getIdentityToken();
}
