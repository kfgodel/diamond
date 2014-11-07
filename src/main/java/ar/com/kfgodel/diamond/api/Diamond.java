package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.sources.*;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.DiamondApi;

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
    public static TypeInstance of(Class<?> nativeClass){
        return types().from(nativeClass);
    }

    /**
     * Utility method to quickly covert a set of native classes into their diamond representation
     * @param nativeClasses Native types
     * @return The array of diamond representation
     */
    public static TypeInstance[] ofNative(Class<?>... nativeClasses){
        TypeInstance[] types = new TypeInstance[nativeClasses.length];
        for (int i = 0; i < nativeClasses.length; i++) {
            Class<?> nativeClass = nativeClasses[i];
            types[i] = Diamond.of(nativeClass);
        }
        return types;
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
}
