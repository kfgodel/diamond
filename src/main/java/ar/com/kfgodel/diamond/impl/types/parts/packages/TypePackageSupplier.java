package ar.com.kfgodel.diamond.impl.types.parts.packages;

import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.packages.TypePackageImpl;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.AnnotatedTypeAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represents the package supplier from a native class
 *
 * Created by kfgodel on 05/11/14.
 */
public class TypePackageSupplier {


    public static Supplier<Optional<TypePackage>> create(Class<?> rawClass) {
        Package nativePackage = rawClass.getPackage();
        return CachedValue.lazilyBy(() ->
                Optional.of(TypePackageImpl.create(
                        nativePackage::getName,
                        AnnotatedTypeAnnotationsSupplier.create(nativePackage))));
    }
}
