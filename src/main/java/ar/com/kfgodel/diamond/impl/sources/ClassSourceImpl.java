package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.impl.reflections.NativeClass;

/**
 * This type is the non fluent implementation of the ClassSource
 * Created by kfgodel on 18/09/14.
 */
public class ClassSourceImpl {

    public static ClassSourceImpl create() {
        ClassSourceImpl classSource = new ClassSourceImpl();
        return classSource;
    }

    /**
     * Retrieves the diamond class instance from a native class instance
     * @param classInstance The class to base from
     * @return The class representation
     */
    public ClassInstance from(Class<?> classInstance){
        return NativeClass.create(classInstance);
    }

    public ClassInstance named(String canonicalName){
        try {
            Class<?> classInstance = Class.forName(canonicalName);
            return from(classInstance);
        } catch (ClassNotFoundException e) {
            throw new DiamondException("There's no class with the name [" + canonicalName+ "]", e);
        }catch (ExceptionInInitializerError e) {
            throw new DiamondException("Class["+canonicalName+"] was found but couldn't be initialized", e);
        }catch (LinkageError e) {
            throw new DiamondException("Class["+canonicalName+"] was found but a dependency failed", e);
        }
    }
}
