package ar.com.kfgodel.diamond.impl.cache;

import ar.com.kfgodel.diamond.api.cache.DiamondCache;

import java.util.WeakHashMap;
import java.util.function.Supplier;

/**
 * This type implements the diamond cache by using a weak reference map
 * Created by kfgodel on 09/11/14.
 */
public class WeakMapCache implements DiamondCache {

    private WeakHashMap<Object,Object> cacheMap;

    public static WeakMapCache create() {
        WeakMapCache cache = new WeakMapCache();
        cache.cacheMap = new WeakHashMap<>();
        return cache;
    }

    @Override
    public void invalidate() {
        cacheMap.clear();
    }

    @Override
    public <T> T reuseOrCreateRepresentationFor(Object nativeReflection, Supplier<T> representationSupplier) {
        Object existingRepresentation = cacheMap.get(nativeReflection);
        if(existingRepresentation != null){
            return (T) existingRepresentation;
        }
        T createdRepresentation = representationSupplier.get();
        cacheMap.put(nativeReflection, createdRepresentation);
        return createdRepresentation;
    }
}
