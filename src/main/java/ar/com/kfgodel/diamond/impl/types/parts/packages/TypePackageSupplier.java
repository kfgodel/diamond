package ar.com.kfgodel.diamond.impl.types.parts.packages;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the supplir of packages for types that have a native class
 * Created by kfgodel on 07/11/14.
 */
public class TypePackageSupplier implements Supplier<Nary<TypePackage>> {

    private CachedValue<TypePackage> typePackage;

    public static Supplier<Nary<TypePackage>> create(Class<?> rawClass) {
        TypePackageSupplier supplier = new TypePackageSupplier();
        supplier.typePackage = CachedValue.lazilyBy(() -> {
            Package nativePackage = rawClass.getPackage();
            if(nativePackage == null){
                return null; // Some classes don't have package
            }else{
                return Diamond.packages().from(nativePackage);
            }
        });
        return supplier;
    }

    @Override
    public Nary<TypePackage> get() {
        return Nary.ofNullable(typePackage.get());
    }
}
