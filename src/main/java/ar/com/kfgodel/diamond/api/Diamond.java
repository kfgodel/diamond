package ar.com.kfgodel.diamond.api;

import ar.com.kfgodel.diamond.api.sources.DClassSources;

/**
 * This type represents the access point to Diamond API
 * Created by kfgodel on 17/09/14.
 */
public interface Diamond {

    /**
     * @return An accessor to obtain DClass instances in various ways
     */
    public static DClassSources classes(){

    }
}
