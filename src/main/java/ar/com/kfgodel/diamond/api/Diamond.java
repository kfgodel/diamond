package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.sources.ClassFieldSources;
import ar.com.kfgodel.diamond.api.sources.ClassMethodSources;
import ar.com.kfgodel.diamond.api.sources.ClassSources;
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
    public static ClassInstance of(Class<?> nativeClass){
        return classes().from(nativeClass);
    }

    /**
     * @return An accessor to obtain DClass instances in various ways
     */
    public static ClassSources classes(){
        return API.classes();
    }

    /**
     * @return An accessor to obtain instances of methods that belong to a class
     */
    public static ClassMethodSources methods(){
        return API.methods();
    }

    /**
     * @return An accessor to obtain instances of fields that belong to a class
     */
    public static ClassFieldSources fields() {
        return API.fields();
    }
}
