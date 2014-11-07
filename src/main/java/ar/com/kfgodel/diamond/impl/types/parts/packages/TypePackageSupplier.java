package ar.com.kfgodel.diamond.impl.types.parts.packages;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.function.Supplier;

/**
 * This type represents the supplir of packages for types that have a native class
 * Created by kfgodel on 07/11/14.
 */
public class TypePackageSupplier implements Supplier<Nary<TypePackage>> {

    private CachedValue<TypePackage> typePackage;

    public static Supplier<Nary<TypePackage>> create(Class<?> rawClass) {
        TypePackageSupplier supplier = new TypePackageSupplier();
        supplier.typePackage = CachedValue.lazilyBy(() -> Diamond.packages().from(rawClass.getPackage()));
        return supplier;
    }

    @Override
    public Nary<TypePackage> get() {
        return NaryFromNative.of(typePackage.get());
    }
}
