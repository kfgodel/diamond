package ar.com.kfgodel.diamond.impl.types.description.descriptors;

import java.util.Set;

/**
 * This type represents the helper object that can be used to describe part of an unannotated type
 * Date: 24/11/19 - 01:36
 */
public class UnannotatedTypeDescriptor {

  private Class<?> rawClass;
  private Set<Class<?>> rawClasses;


  public static UnannotatedTypeDescriptor create() {
    UnannotatedTypeDescriptor descriptor = new UnannotatedTypeDescriptor();
    return descriptor;
  }

}
