package ar.com.kfgodel.diamond.api.types.packages;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the diamond description of a package
 * Created by kfgodel on 05/11/14.
 */
public interface PackageDescription {

    /**
     * @return The supplier for package name
     */
    Supplier<String> getNameSupplier();

    /**
     * @return The supplier for package annotations
     */
    Supplier<Stream<Annotation>> getAnnotationsSupplier();
}
