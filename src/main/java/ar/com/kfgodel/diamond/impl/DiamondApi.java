package ar.com.kfgodel.diamond.impl;

import ar.com.kfgodel.diamond.api.sources.constructors.ConstructorSources;
import ar.com.kfgodel.diamond.api.sources.fields.FieldSources;
import ar.com.kfgodel.diamond.api.sources.methods.MethodSources;
import ar.com.kfgodel.diamond.api.sources.modifiers.ModifierSources;
import ar.com.kfgodel.diamond.api.sources.types.TypeSources;
import ar.com.kfgodel.diamond.impl.sources.*;

/**
 * This type represents the implementation of the access point to diamond instances
 * Created by kfgodel on 18/09/14.
 */
public class DiamondApi {

    private MethodSources methods;
    private FieldSources fields;
    private TypeSources types;
    private ConstructorSources constructors;
    private ModifierSources modifiers;

    public static DiamondApi create() {
        DiamondApi diamondApi = new DiamondApi();
        diamondApi.fields = FieldSourceImpl.create();
        diamondApi.methods = MethodSourceImpl.create();
        diamondApi.types = TypeSourceImpl.create();
        diamondApi.constructors = ConstructorsSourceImpl.create();
        diamondApi.modifiers = ModifierSourcesImpl.create();
        return diamondApi;
    }

    public MethodSources methods() {
        return methods;
    }

    public FieldSources fields() {
        return fields;
    }

    public TypeSources types() {
        return types;
    }

    public ConstructorSources constructors() {
        return constructors;
    }

    public ModifierSources modifiers(){return modifiers; };
}
