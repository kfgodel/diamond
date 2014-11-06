package ar.com.kfgodel.diamond.impl.types.packages;

import ar.com.kfgodel.diamond.api.types.packages.PackageDescription;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.AnnotatedTypeAnnotationsSupplier;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
    public Supplier<Stream<Annotation>> getAnnotationsSupplier() {
        return AnnotatedTypeAnnotationsSupplier.create(nativePackage);
    }
}
