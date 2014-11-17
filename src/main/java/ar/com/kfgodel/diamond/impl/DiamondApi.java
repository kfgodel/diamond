package ar.com.kfgodel.diamond.impl;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.*;
import ar.com.kfgodel.diamond.impl.cache.WeakMapCache;
import ar.com.kfgodel.diamond.impl.sources.*;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * This type represents the implementation of the access point to diamond instances
 * Created by kfgodel on 18/09/14.
 */
public class DiamondApi {

    private DiamondCache cache;
    private MethodSources methods;
    private FieldSources fields;
    private TypeSources types;
    private ConstructorSources constructors;
    private ModifierSources modifiers;
    private PackageSources packages;
    private ParameterSources parameters;
    private MetaObjectSources metaObjects;
    private LinkedHashMap<Class<?>, Function<Object, DiamondReflection>> converterPerType;

    public static DiamondApi create() {
        DiamondApi diamondApi = new DiamondApi();
        diamondApi.cache = WeakMapCache.create();
        diamondApi.types = TypeSourceImpl.create(diamondApi.cache);
        diamondApi.fields = FieldSourceImpl.create(diamondApi.cache);
        diamondApi.methods = MethodSourceImpl.create(diamondApi.cache);
        diamondApi.constructors = ConstructorsSourceImpl.create(diamondApi.cache);
        diamondApi.modifiers = ModifierSourcesImpl.create(diamondApi.cache);
        diamondApi.packages = PackageSourcesImpl.create(diamondApi.cache);
        diamondApi.parameters = ParameterSourcesImpl.create(diamondApi.cache);
        diamondApi.metaObjects = MetaObjectSourcesImpl.create(diamondApi.cache);
        diamondApi.converterPerType = new LinkedHashMap<>();
        diamondApi.initialize();
        return diamondApi;
    }

    private void initialize() {
        addConverterForInstancesOf(Type.class, (nativeType) -> types().from(nativeType));
        addConverterForInstancesOf(AnnotatedType.class, (nativeType) -> types().from(nativeType));
        addConverterForInstancesOf(Method.class, (nativeMethod) -> methods().from(nativeMethod));
        addConverterForInstancesOf(Field.class, (nativeField) -> fields().from(nativeField));
        addConverterForInstancesOf(Constructor.class, (nativeConstructor) -> constructors().from(nativeConstructor));
        addConverterForInstancesOf(Package.class, (nativePackage) -> packages().from(nativePackage));
        addConverterForInstancesOf(Parameter.class, (nativeParameter) -> parameters().from(nativeParameter));
        addConverterForInstancesOf(Parameter.class, (nativeParameter) -> parameters().from(nativeParameter));
    }

    private <T> void addConverterForInstancesOf(Class<T> sourceType, Function<T, DiamondReflection> converter){
        this.converterPerType.put(sourceType, (Function<Object, DiamondReflection>) converter);
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

    public PackageSources packages() {
        return packages;
    }

    public ParameterSources parameters() {
        return parameters;
    }

    public <T extends DiamondReflection> T from(Object nativeReflection) {
        return cache.reuseOrCreateRepresentationFor(nativeReflection, () -> delegateToCorrespondingType(nativeReflection));
    }

    /**
     * Searches for the converter of the given native reflection object
     * @param nativeReflection The native reflection representation
     * @param <T> The expected returned diamond type
     * @return The converted representation
     */
    private <T extends DiamondReflection> T delegateToCorrespondingType(Object nativeReflection) {
        Set<Map.Entry<Class<?>, Function<Object, DiamondReflection>>> entries = converterPerType.entrySet();
        for (Map.Entry<Class<?>, Function<Object, DiamondReflection>> entry : entries) {
            Class<?> sourceType = entry.getKey();
            if(!sourceType.isInstance(nativeReflection)){
                continue;
            }
            Function<Object, DiamondReflection> converter = entry.getValue();
            DiamondReflection representation = converter.apply(nativeReflection);
            return (T) representation;
        }
        throw new DiamondException("There's no conversion defined yet for: "+ nativeReflection);
    }

    public DiamondCache getCache() {
        return cache;
    }

    public MetaObjectSources metaObjects() {
        return metaObjects;
    }
}
