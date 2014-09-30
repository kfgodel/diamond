package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

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
     * @param nativeClass The class to base from
     * @return The class representation
     */
    public TypeInstance from(Class<?> nativeClass){
        return Diamond.types().from(nativeClass);
    }

    public TypeInstance named(String canonicalName){
        try {
            Class<?> nativeClass = Class.forName(canonicalName);
            return from(nativeClass);
        } catch (ClassNotFoundException e) {
            throw new DiamondException("There's no class with the name [" + canonicalName+ "]", e);
        }catch (ExceptionInInitializerError e) {
            throw new DiamondException("Class["+canonicalName+"] was found but couldn't be initialized", e);
        }catch (LinkageError e) {
            throw new DiamondException("Class["+canonicalName+"] was found but a dependency failed", e);
        }
    }
}
