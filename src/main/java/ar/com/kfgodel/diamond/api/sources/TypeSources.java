package ar.com.kfgodel.diamond.api.sources;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This type represents the possible sources for a type instance
 * Created by kfgodel on 20/09/14.
 */
public interface TypeSources {

  /**
   * Retrieves a type instance from an unspecific type whose type is not known.<br>
   * This method is needed to be able to treat AnnotatedType and Type with the same signature
   *
   * @param nativeType The native object that represents a type (AnnotatedType or Type)
   * @return The type instance that represents the object
   * @throws ar.com.kfgodel.diamond.api.exceptions.DiamondException if the object doesn't represent a type
   */
  TypeInstance from(Object nativeType) throws DiamondException;

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
   * Retrieves a type instance based on the actual parametrization of a subtype to get the actual arguments of the generic type
   *
   * @param parameterizableSubtype The parameterizable subtype used to match parameters on the subtype
   * @param subtypeArguments       The actual subtype arguments
   * @param annotatedSuperType     The annotated type definition (contains annotations)
   * @param genericSupertype       the generic type definition (contains generic information)
   * @return The complete type with annotations, generics, and the calculated actual arguments according to the sub-super type parametrization
   */
  TypeInstance fromParameterizedNativeTypes(Class<?> parameterizableSubtype, List<TypeInstance> subtypeArguments, AnnotatedType annotatedSuperType, Type genericSupertype);
}
