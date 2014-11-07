package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.packages.PackageDescription;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;

/**
 * This type represents the sources for a TypePackage object
 * Created by kfgodel on 05/11/14.
 */
public interface PackageSources {

    /**
     * Retrieves the diamond representation of the native package
     * @param nativePackage The native package representation
     * @return The Diamond representation
     */
    TypePackage from(Package nativePackage);

    /**
     * Retrieves a package by name
     * @param packageName The name of the existing package
     * @return The package representation
     * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If the package doesn't exist
     */
    TypePackage named(String packageName) throws DiamondException;

    /**
     * Retrieves the package by its description
     * @param description The package description
     * @return The package instance
     */
    TypePackage from(PackageDescription description);
}
