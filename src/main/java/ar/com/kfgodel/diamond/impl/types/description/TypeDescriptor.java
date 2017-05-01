package ar.com.kfgodel.diamond.impl.types.description;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.*;
import com.google.common.base.MoreObjects;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * This type describes native types for a diamond representation
 * Created by kfgodel on 29/09/14.
 */
public class TypeDescriptor {

  public static final TypeDescriptor INSTANCE = TypeDescriptor.create();

  private Map<Class<?>, Function<Object, TypeDescription>> descriptorsPerType;
  public static final String descriptorsPerType_FIELD = "descriptorsPerType";


  public Map<Class<?>, Function<Object, TypeDescription>> getDescriptorsPerType() {
    if (descriptorsPerType == null) {
      descriptorsPerType = new LinkedHashMap<>();
      defineDefaultTypeDescriptors();
    }
    return descriptorsPerType;
  }

  /**
   * Declares all the default descriptors per type
   */
  private void defineDefaultTypeDescriptors() {
    // Un-annotated types
    useForInstancesOf(Class.class, ClassDescription::create);
    useForInstancesOf(ParameterizedType.class, ParameterizedTypeDescription::create);
    useForInstancesOf(GenericArrayType.class, GenericArrayTypeDescription::create);
    useForInstancesOf(TypeVariable.class, TypeVariableDescription::create);
    useForInstancesOf(WildcardType.class, WildcardTypeDescription::create);

    // Annotated (since AnnotatedType is used for class but also for all, we cannot map it here)
    useForInstancesOf(AnnotatedParameterizedType.class, AnnotatedParameterizedTypeDescription::create);
    useForInstancesOf(AnnotatedArrayType.class, AnnotatedArrayTypeDescription::create);
    useForInstancesOf(AnnotatedTypeVariable.class, AnnotatedTypeVariableDescription::create);
    useForInstancesOf(AnnotatedWildcardType.class, AnnotatedWildcardDescription::create);
  }

  /**
   * Stores the indicated descriptor function related to a type
   *
   * @param classType          The type for which the descriptor function should be used
   * @param descriptorFunction The function that describes the type instances
   * @param <T>                The type of input
   */
  private <T> void useForInstancesOf(Class<T> classType, Function<T, TypeDescription> descriptorFunction) {
    this.getDescriptorsPerType().put(classType, (Function<Object, TypeDescription>) descriptorFunction);
  }

  /**
   * Describes the given native type in terms of a Diamond type parts
   *
   * @param nativeType The native type representation
   * @return The diamon description of it
   */
  public TypeDescription describe(Object nativeType) {
    Function<Object, TypeDescription> bestDescriptor = findBestDescriptorFor(nativeType);
    TypeDescription typeDescription = bestDescriptor.apply(nativeType);
    return typeDescription;
  }

  /**
   * Finds the best descriptor based on the native object type.<br>
   * Since the type is not the only needed discriminator, some type have exceptional treatment
   *
   * @param nativeType The object that represents a type for the native reflection api
   * @return The function that describes the object
   * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException if type cannot be described (or is not a type)
   */
  private Function<Object, TypeDescription> findBestDescriptorFor(Object nativeType) throws DiamondException {
    Set<Map.Entry<Class<?>, Function<Object, TypeDescription>>> entries = getDescriptorsPerType().entrySet();
    for (Map.Entry<Class<?>, Function<Object, TypeDescription>> entry : entries) {
      Class<?> descriptableType = entry.getKey();
      if (descriptableType.isInstance(nativeType)) {
        Function<Object, TypeDescription> descriptor = entry.getValue();
        return descriptor;
      }
    }
    return requiresSpecialTreatment(nativeType);
  }

  /**
   * Some type cannot be deduced from its object type and thus this method checks for those cases
   *
   * @param nativeType the native object type
   * @return The function that describes the type
   * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException if type cannot be described (or is not a type)
   */
  private Function<Object, TypeDescription> requiresSpecialTreatment(Object nativeType) throws DiamondException {
    if (nativeType instanceof AnnotatedType) {
      //It may be an annotated class
      Type unannotatedType = ((AnnotatedType) nativeType).getType();
      if (unannotatedType instanceof Class) {
        Function<AnnotatedType, TypeDescription> bestDescriptor = AnnotatedClassDescription::create;
        return (Function) bestDescriptor;
      }
      if (unannotatedType == null) {
        throw new DiamondException("The annotated type[" + nativeType + "] has a getType() == null. This is bug on earlier version of the JDK 8.\n" +
          "Please upgrade your VM of this functionality will not work. Related: https://bugs.openjdk.java.net/browse/JDK-8038994");
      }
      throw new DiamondException("An annotated type for something that's not a class doesn't have a creation method: " + unannotatedType);
    }
    throw new DiamondException("There's a new type that we cannot represent: " + nativeType);
  }

  public static TypeDescriptor create() {
    TypeDescriptor descriptor = new TypeDescriptor();
    return descriptor;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add(descriptorsPerType_FIELD, descriptorsPerType)
      .toString();
  }
}
