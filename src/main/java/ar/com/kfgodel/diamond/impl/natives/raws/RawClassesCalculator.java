package ar.com.kfgodel.diamond.impl.natives.raws;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class knows how to calculate the raw classes used to represent a type in runtime.<br>
 * Due to compile time types being different from runtime types, this class allows getting the runtime
 * classes from a generic type
 *
 * Date: 1/12/19 - 19:11
 */
public class RawClassesCalculator {

  /**
   * Gets the class or classes used in runtime to represent the given generic type without knowing which
   * sub-type is given.<br>
   * This method delegates into the one that is specific for the given type
   * @param nativeType The generic type
   * @return The raw class or classes used in runtime
   */
  public Nary<Class<?>> fromUnknown(Type nativeType){
    if (nativeType instanceof Class) {
      return from((Class) nativeType);
    }
    if (nativeType instanceof ParameterizedType) {
      return from((ParameterizedType) nativeType);
    }
    if (nativeType instanceof GenericArrayType) {
      return from((GenericArrayType) nativeType);
    }
    if (nativeType instanceof WildcardType) {
      return from((WildcardType) nativeType);
    }
    if (nativeType instanceof TypeVariable) {
      return from((TypeVariable<?>) nativeType);
    }
    throw new DiamondException("The given generic type[" + nativeType + "] is not one of the known sub-types. " +
      "Is it a new concept in the language?");
  }

  /**
   * Base case for a class which returns the same class
   * @param nativeType The native class type
   * @return The given class
   */
  public Nary<Class<?>> from(Class<?> nativeType){
    return Nary.of(nativeType);
  }

  /**
   * Gets the runtime raw class from the parameterized type.<br>
   * The raw class is the same class without the parameters
   * @param nativeType The parameterized type
   * @return Its un-parameterized class
   */
  public Nary<Class<?>> from(ParameterizedType nativeType){
    Type unparameterizedType = nativeType.getRawType();
    return fromUnknown(unparameterizedType);
  }

  /**
   * Gets the raw classes from the upper bounds on the given type variable.<br>
   * Since a type variable can have more than one upper bound, the returned classes may be more
   * than 1, or none
   * @param nativeType The type variable
   * @return The un-parameterized classes taken from the upper bounds of the variable
   */
  public Nary<Class<?>> from(TypeVariable<?> nativeType){
    final Type[] upperBounds = nativeType.getBounds();
    return from(upperBounds);
  }

  /**
   * Gets the raw classes from the upper bounds on the given wildcard type.<br>
   * Since a wildcard can have more than one upper bound, the returned classes may be more
   * than 1, or none
   * @param nativeType The type variable
   * @return The un-parameterized classes taken from the upper bounds of the wildcard
   */
  public Nary<Class<?>> from(WildcardType nativeType){
    final Type[] upperBounds = nativeType.getUpperBounds();
    return from(upperBounds);
  }

  private Nary<Class<?>> from(Type[] upperBounds){
    if (upperBounds== null || upperBounds.length == 0) {
      // Unbounded means that we only know that's an Object
      return Nary.of(Object.class);
    }
    Nary<Class<?>> rawClasses = Nary.empty();
    for (Type upperBound : upperBounds) {
      final Nary<Class<?>> boundClasses = fromUnknown(upperBound);
      rawClasses = rawClasses.concat(boundClasses);
    }
    return rawClasses;
  }

  /**
   * Gets the raw class used in runtime to create instances of the given generic array.<br>
   * Because arrays cannot be instantiated with generics, the given type is un-generified to
   * get a new instance from which the raw class is calculated.<br>
   * That means this method instantiates a 0 sized array of the given type to calculate is raw class
   * @param nativeType The generic array
   * @return The only class used in runtime
   */
  public Nary<Class<?>> from(GenericArrayType nativeType){
    Class<?> rawComponentClass = calculateRawComponentClass(nativeType);
    // We need to create an array to get the actual runtime class
    final Object sampleArray = Array.newInstance(rawComponentClass, 0);
    final Class<?> rawClass = sampleArray.getClass();
    return Nary.of(rawClass);
  }

  private Class<?> calculateRawComponentClass(GenericArrayType genericArrayType) {
    Type componentType = genericArrayType.getGenericComponentType();
    final Nary<Class<?>> rawComponentClasses = fromUnknown(componentType);
    return coalesce(rawComponentClasses);
  }

  /**
   * Gets the lowest superclass that is supertype of the given classes.<br>
   * @param rawClasses The classes for which a runtime supertype is needed
   * @return The class that can be assigned from instances of the given types
   */
  public Class<?> coalesce(Nary<Class<?>> rawClasses) {
    final List<Class<?>> classes = rawClasses.collect(Collectors.toList());
    if(classes.size() == 1){
      // Only if a single class is given can we use it as is
      return classes.get(0);
    }
    // Otherwise we should look for a supertype. TODO: use a more specific type than Object
    return Object.class;
  }

  public static RawClassesCalculator create() {
    RawClassesCalculator calculator = new RawClassesCalculator();
    return calculator;
  }

}
