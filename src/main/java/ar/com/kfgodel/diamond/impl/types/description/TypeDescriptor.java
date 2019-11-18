package ar.com.kfgodel.diamond.impl.types.description;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.AnnotatedArrayTypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.AnnotatedClassDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.AnnotatedParameterizedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.AnnotatedTypeVariableDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.AnnotatedWildcardDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.ClassDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.GenericArrayTypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.ParameterizedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.TypeVariableDescription;
import ar.com.kfgodel.diamond.impl.types.description.natives.WildcardTypeDescription;
import ar.com.kfgodel.pairs.Pair;
import com.google.common.base.MoreObjects;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * This type describes native types for a diamond representation
 * Created by kfgodel on 29/09/14.
 */
public class TypeDescriptor {

  public static final TypeDescriptor INSTANCE = TypeDescriptor.create();

  private List<Pair<Class<?>, Function<Object, TypeDescription>>> descriptorsPerType;
  public static final String descriptorsPerType_FIELD = "descriptorsPerType";

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
   * Stores the indicated descriptor function related to a type after the last descriptor
   *
   * @param classType          The type for which the descriptor function should be used
   * @param descriptorFunction The function that describes the type instances
   * @param <T>                The type of input
   */
  private <T> void useForInstancesOf(Class<T> classType, Function<T, TypeDescription> descriptorFunction) {
    descriptorsPerType.add(Pair.create(classType, (Function<Object, TypeDescription>) descriptorFunction));
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
    for (Pair<Class<?>, Function<Object, TypeDescription>> entry : descriptorsPerType) {
      Class<?> descriptableType = entry.getFirst();
      if (descriptableType.isInstance(nativeType)) {
        Function<Object, TypeDescription> descriptor = entry.getSecond();
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
        throw new DiamondException("The annotated type[" + nativeType + "] has a getType() == null. "+
          "This is bug on earlier version of the JDK 8.\n" +
          "Please upgrade your VM of this functionality will not work. "+
          "Related: https://bugs.openjdk.java.net/browse/JDK-8038994");
      }
      throw new DiamondException("An annotated type for something that's not a class doesn't have a "+
        "creation method: " + unannotatedType);
    }
    throw new DiamondException("There's a new type that we cannot represent: " + nativeType);
  }

  public static TypeDescriptor create() {
    TypeDescriptor descriptor = new TypeDescriptor();
    descriptor.descriptorsPerType = new ArrayList<>();
    descriptor.defineDefaultTypeDescriptors();
    return descriptor;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add(descriptorsPerType_FIELD, descriptorsPerType)
      .toString();
  }
}
