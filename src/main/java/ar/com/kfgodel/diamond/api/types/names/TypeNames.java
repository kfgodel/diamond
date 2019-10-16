package ar.com.kfgodel.diamond.api.types.names;

/**
 * This type represents the sources of class names with the class defined
 * Created by kfgodel on 21/09/14.
 */
public interface TypeNames {

  /**
   * The name of this type without package prefix. This is the shortest version of the name and doesn't have generics information
   *
   * @return The short name version for this type (for classes this is getSimpleName() )
   */
  String shortName();

  /**
   * The type name prefixed with the package and type symbols like [, or $. This name identifies the type and can be
   * used to load the native class instance.<br>
   * For non classes types this is equal to the typeName
   *
   * @return The VM name of the type ( for classes this is getName() )
   */
  String classloaderName();

  /**
   * The full name of this type without type symboles and without generics information as you would declare the raw type.<br>
   * For non classes types this is equal to the typeName.
   *
   * @return The name that identifies this type ( for classes this is getCanonicalName() )
   */
  String canonicalName();

  /**
   * The full definition type name including generics information (if available). On some types this name doesn't include type arguments.<br>
   * This is the typeName() string available to all native types since Java 8.
   *
   * @return the name for this type (for Types this is getTypeName() )
   */
  String typeName();

  /**
   * The complete name of this type without annotations, type arguments, component type or type bounds.<br>
   * For classes this is the fully qualified name<br>
   * For arrays this is '[]'<br>
   * For wildcards this is '?'<br>
   * For type variables this is the name of the variable.<br>
   * <br>
   * This name allows you to discriminate the core of this type without considering its relations with other types
   *
   * @return The name that identifies this type without including relations with other types (bounds, arguments, components, etc)
   */
  String bareName();

}
