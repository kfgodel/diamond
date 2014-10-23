package ar.com.kfgodel.diamond.impl.types.parts.superclass;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents a fragment of code that can extract the native superclass of a native class
 * Created by kfgodel on 21/09/14.
 */
public class SuperClassSupplier {

    public static Supplier<Optional<TypeInstance>>  create(Class<?> nativeClass) {
        return SuppliedValue.lazilyBy(()->{
            Class<?> superclass = nativeClass.getSuperclass();
            if(superclass == null){
                return Optional.empty();
            }
            return Optional.of(Diamond.of(superclass));
        });
    }

}
