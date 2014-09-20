package ar.com.kfgodel.diamond.impl;

import ar.com.dgarcia.fluentizer.impl.Fluentizer;
import ar.com.kfgodel.diamond.api.sources.ClassFieldSources;
import ar.com.kfgodel.diamond.api.sources.ClassMethodSources;
import ar.com.kfgodel.diamond.api.sources.ClassSources;
import ar.com.kfgodel.diamond.impl.sources.ClassFieldSourceImpl;
import ar.com.kfgodel.diamond.impl.sources.ClassMethodSourceImpl;
import ar.com.kfgodel.diamond.impl.sources.ClassSourceImpl;

/**
 * This type represents the implementation of the access point to diamond instances
 * Created by kfgodel on 18/09/14.
 */
public class DiamondApi {

    private ClassSources classes;
    private ClassMethodSources methods;
    private ClassFieldSources fields;

    public static DiamondApi create() {
        DiamondApi diamondApi = new DiamondApi();
        Fluentizer fluentizer = Fluentizer.create();
        diamondApi.classes = fluentizer.expressAs(ClassSources.class, ClassSourceImpl.create());
        diamondApi.methods = fluentizer.expressAs(ClassMethodSources.class, ClassMethodSourceImpl.create());
        diamondApi.fields = fluentizer.expressAs(ClassFieldSources.class, ClassFieldSourceImpl.create());
        return diamondApi;
    }

    public ClassSources classes() {
        return classes;
    }

    public ClassMethodSources methods() {
        return methods;
    }

    public ClassFieldSources fields() {
        return fields;
    }
}
