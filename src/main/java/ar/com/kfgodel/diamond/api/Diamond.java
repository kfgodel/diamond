package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.sources.constructors.ConstructorSources;
import ar.com.kfgodel.diamond.api.sources.fields.FieldSources;
import ar.com.kfgodel.diamond.api.sources.methods.MethodSources;
import ar.com.kfgodel.diamond.api.sources.modifiers.ModifierSources;
import ar.com.kfgodel.diamond.api.sources.packages.PackageSources;
import ar.com.kfgodel.diamond.api.sources.types.TypeSources;
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

    public static PackageSources packages() {return API.packages();}


    /**
     * @return An accessor to obtain instances that represent type constructors
     */
    public static ConstructorSources constructors() {
        return API.constructors();
    }
}
