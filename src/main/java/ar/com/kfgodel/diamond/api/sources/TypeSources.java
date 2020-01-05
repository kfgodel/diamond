package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This type represents the possible sources for a type instance
 * Created by kfgodel on 20/09/14.
 */
public interface TypeSources {

  /**
   * Retrieves a type instance for an object that is part of the native reflection API<br>
   * Using this method you can get the {@link TypeInstance} version of any reflection type.<br>
   * Because reflection uses {@link Type} and {@link AnnotatedType} and those types have no hierarchy
   * in common, we need to accept {@link Object} as the input argument
   *
   * @param nativeType The native reflection object that represents a type (AnnotatedType or Type)
   * @return The type instance that represents the object
   * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException if the object doesn't represent a type
   * @throws IllegalArgumentException if null is passed
   */
  TypeInstance from(Object nativeType) throws DiamondException, IllegalArgumentException;

  /**
   * Retrieves the type instances for an array of objects that are part of the native reflection API<br>
   * Using this method you can get many {@link TypeInstance} at once for an array of reflection types.<br>
   * Because reflection uses {@link Type} and {@link AnnotatedType} and those types have no hierarchy
   * in common, we need to accept {@link Object} as the input argument.<br>
   * <br>  
   * This methods is a variation of {@link #from(Object)}  
   *
   * @param nativeTypes The array of native reflection objects that represents a type (AnnotatedType or Type)
   * @return The nary matching on {@link TypeInstance} per element in the array
   * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException if the object doesn't represent a type
   * @throws IllegalArgumentException if null is passed
   */
  Nary<TypeInstance> from(Object[] nativeTypes) throws DiamondException, IllegalArgumentException;

  /**
   * Retrieves a type instance from its description.<br>
   * This method is the generic entry point for all type instance representations
   *
   * @param description The diamond description for the type
   * @return The type representation
   */
  TypeInstance fromDescription(TypeDescription description);

  /**
   * Retrieves a type instance by its class name
   *
   * @param typeName The complete class name
   * @return The type representation of the class
   * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException If the class name doesn't exists
   */
  TypeInstance named(String typeName) throws DiamondException;

  /**
   * Retrieves a type instance based on the actual parametrization of a subtype to get the
   * actual arguments of the generic type
   *
   * @param parameterizableSubtype The parameterizable subtype used to match parameters on the subtype
   * @param subtypeArguments       The actual subtype arguments
   * @param annotatedSuperType     The annotated type definition (contains annotations)
   * @param genericSupertype       the generic type definition (contains generic information)
   * @return The complete type with annotations, generics, and the calculated
   * actual arguments according to the sub-super type parametrization
   */
  TypeInstance fromParameterizedNativeTypes(Class<?> parameterizableSubtype,
                                            List<TypeInstance> subtypeArguments,
                                            AnnotatedType annotatedSuperType,
                                            Type genericSupertype);
}
