package ar.com.kfgodel.diamond.impl.types.parts.superclass;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.function.Supplier;

/**
 * This type represents a fragment of code that can extract the native superclass of a native class
 * Created by kfgodel on 21/09/14.
 */
public class SuperClassSupplier implements Supplier<Nary<TypeInstance>> {

    private CachedValue<TypeInstance> superclass;

    @Override
    public Nary<TypeInstance> get() {
        TypeInstance superType = superclass.get();
        if(superType == null){
            return NaryFromNative.empty();
        }
        return NaryFromNative.of(superType);
    }

    public static Supplier<Nary<TypeInstance>>  create(Class<?> nativeClass) {
        SuperClassSupplier supplier = new SuperClassSupplier();
        supplier.superclass = CachedValue.lazilyBy(() -> {
            Class<?> superclass = nativeClass.getSuperclass();
            if (superclass == null) {
                return null;
            }
            return Diamond.of(superclass);
        });
        return supplier;
    }

}
