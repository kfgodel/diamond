package ar.com.kfgodel.diamond.impl.types.packages;

import ar.com.kfgodel.diamond.api.types.packages.PackageDescription;
import ar.com.kfgodel.diamond.impl.natives.suppliers.AnnotatedElementAnnotationsSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * This type represents the description of a native package
 *
 * Created by kfgodel on 05/11/14.
 */
public class NativePackageDescription implements PackageDescription {

    private Package nativePackage;


    public static PackageDescription create(Package nativePackage) {
        NativePackageDescription description = new NativePackageDescription();
        description.nativePackage = nativePackage;
        return description;
    }

    @Override
    public Supplier<String> getNameSupplier() {
        return nativePackage::getName;
    }

    @Override
    public Supplier<Nary<Annotation>> getAnnotationsSupplier() {
        return AnnotatedElementAnnotationsSupplier.create(nativePackage);
    }
}
