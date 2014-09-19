package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.impl.reflections.NativeClassMethod;

import java.lang.reflect.Method;

/**
 * This type is the non fluent implementation of the class method source
 * Created by kfgodel on 18/09/14.
 */
public class ClassMethodSourceImpl {

    public static ClassMethodSourceImpl create() {
        ClassMethodSourceImpl classMethodSource = new ClassMethodSourceImpl();
        return classMethodSource;
    }

    /**
     * Retrieves the diamond instance of the given native method instance
     * @param methodInstance The native method instance
     * @return The diamond representation
     */
    public ClassMethod from(Method methodInstance){
        return NativeClassMethod.create(methodInstance);
    }

    public ClassMethod inIdentifiedAs(Class<?> classInstance, String methodName, Class<?>... parameterTypes){
        ClassInstance diamondClass = Diamond.classes().from(classInstance);
        return diamondClass.methods().identifiedAs(methodName, parameterTypes);
    }
}
