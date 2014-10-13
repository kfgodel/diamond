package ar.com.kfgodel.diamond.impl;

import ar.com.dgarcia.fluentizer.impl.Fluentizer;
import ar.com.kfgodel.diamond.api.sources.fields.ClassFieldSources;
import ar.com.kfgodel.diamond.api.sources.methods.ClassMethodSources;
import ar.com.kfgodel.diamond.api.sources.types.TypeSources;
import ar.com.kfgodel.diamond.impl.sources.ClassFieldSourceImpl;
import ar.com.kfgodel.diamond.impl.sources.ClassMethodSourceImpl;
import ar.com.kfgodel.diamond.impl.sources.TypeSourceImpl;

/**
 * This type represents the implementation of the access point to diamond instances
 * Created by kfgodel on 18/09/14.
 */
public class DiamondApi {

    private ClassMethodSources methods;
    private ClassFieldSources fields;
    private TypeSources types;

    public static DiamondApi create() {
        DiamondApi diamondApi = new DiamondApi();
        Fluentizer fluentizer = Fluentizer.create();
        diamondApi.fields = ClassFieldSourceImpl.create();
        diamondApi.methods = ClassMethodSourceImpl.create();
        diamondApi.types = TypeSourceImpl.create();
        return diamondApi;
    }

    public ClassMethodSources methods() {
        return methods;
    }

    public ClassFieldSources fields() {
        return fields;
    }

    public TypeSources types() {
        return types;
    }
}
