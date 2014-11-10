package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.sources.*;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.DiamondApi;

import java.lang.reflect.Type;

/**
 * This type represents the access point to Diamond API
 * Created by kfgodel on 17/09/14.
 */
public interface Diamond {

    /**
     * Singleton instance to avoid creation
     */
    public static final DiamondApi API = DiamondApi.create();


    /**
     * @param nativeClass The native class instance to base on
     * @return A diamond class instance representation
     */
    public static TypeInstance of(Type nativeClass){
        return types().from(nativeClass);
    }

    /**
     * Utility method to quickly covert a set of native classes into their diamond representation
     * @param nativeClasses Native types
     * @return The array of diamond representation
     */
    public static TypeInstance[] ofNative(Type... nativeClasses){
        TypeInstance[] types = new TypeInstance[nativeClasses.length];
        for (int i = 0; i < nativeClasses.length; i++) {
            Type nativeClass = nativeClasses[i];
            types[i] = Diamond.of(nativeClass);
        }
        return types;
    }

    /**
     * Converts the given native reflection API object into a Diamond representation.<br>
     * - Classes and types are converted into TypeInstance.<br>
     * - Methods are converted into TypeMethods.<br>
     * - Fields into TypeFields.<br>
     * - Constructors into TypeConstructor.<br>
     * - int modifiers into Modifiers.<br>
     * - Parameters into ExecutableParameters<br>
     * <br>
     * This a generic entry point that delegates the conversion to more specific versions, like methods().from(Method).,
     * Use this entry point as a all-purpose converter, or to reduce verbosity
     * @param nativeReflection The native reflection object that represent a code element
     * @param <T> The type of expected diamond instance
     * @return The diamond representation for the given element
     */
    public static <T extends DiamondReflection> T from(Object nativeReflection){
        return API.from(nativeReflection);
    }

    /**
     * @return An accessor to obtain instances of methods that belong to a class
     */
    public static MethodSources methods(){
        return API.methods();
    }

    /**
     * @return An accessor to obtain instances of fields that belong to a class
     */
    public static FieldSources fields() {
        return API.fields();
    }

    /**
     * @return An accessor to obtain instances that represent types
     */
    public static TypeSources types() { return API.types(); }

    /**
     * @return An accessor to obtain instances that represent type member modifiers
     */
    public static ModifierSources modifiers() {return API.modifiers(); }

    /**
     * @return An accessor to obtain instances that represent packages
     */
    public static PackageSources packages() {return API.packages();}


    /**
     * @return An accessor to obtain instances that represent parameters
     */
    public static ParameterSources parameters() {return API.parameters();}


    /**
     * @return An accessor to obtain instances that represent type constructors
     */
    public static ConstructorSources constructors() {
        return API.constructors();
    }

    /**
     * @return The cache instance used to reference reused instances
     */
    public static DiamondCache cache() {return API.getCache();}
}
