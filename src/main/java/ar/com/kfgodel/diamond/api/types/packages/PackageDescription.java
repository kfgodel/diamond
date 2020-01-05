package ar.com.kfgodel.diamond.api.types.packages;

import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

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
  Supplier<? extends Nary<Annotation>> getAnnotationsSupplier();
}
