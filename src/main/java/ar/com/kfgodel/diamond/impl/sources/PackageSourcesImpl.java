package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.PackageSources;
import ar.com.kfgodel.diamond.api.types.packages.PackageDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.impl.types.packages.PackageDescriptor;
import ar.com.kfgodel.diamond.impl.types.packages.TypePackageImpl;

/**
 * This type represents the sources for type packages
 * Created by kfgodel on 05/11/14.
 */
public class PackageSourcesImpl implements PackageSources {

    public static PackageSourcesImpl create() {
        PackageSourcesImpl sources = new PackageSourcesImpl();
        return sources;
    }

    @Override
    public TypePackage from(Package nativePackage) {
        PackageDescription description = PackageDescriptor.INSTANCE.describe(nativePackage);
        return from(description);
    }

    @Override
    public TypePackage from(PackageDescription description){
        // Cache?
        return createPackageFrom(description);
    }


    /**
     * Creates a new type package from its native representation
     */
    private TypePackage createPackageFrom(PackageDescription description) {
        return TypePackageImpl.create(description.getNameSupplier(), description.getAnnotationsSupplier());
    }

    @Override
    public TypePackage named(String packageName) throws DiamondException {
        Package nativePackage = Package.getPackage(packageName);
        if(nativePackage == null){
            throw new DiamondException("The package["+packageName+"] could not be found, or is not yet loaded");
        }
        return from(nativePackage);
    }
}
