package ar.com.kfgodel.diamond.api.types.names;

/**
 * This type defines accessor methods for the different names of a type
 * Created by kfgodel on 21/09/14.
 */
public interface TypeNames {

  /**
   * The shortest name of this type. It doesn't include package prefix, generics information or annotations.<br>
   * For projects without duplicated classes this is usually the name you want to use in string messages.<br>
   * <br>
   * For classes this is the simple name<br>
   * For arrays this is the short name of the component type + '[]'<br>
   * For wildcards this is '?'<br>
   * For type variables this is the name of the variable.<br>
   *
   * @return The shortest name to identify the type
   */
  String shortName();

  /**
   * The name of the type that is commonly used to identify it. This name usually doesn't have all the information
   * but enough for identification.<br>
   * For classes, this is the {@link Class#getName()} which may be the binary name.<br>
   * For non classes types this is equal to the typeName
   *
   * @return The name this type on runtime
   */
  String commonName();

  /**
   * The full name of this type without type symbols and without generics information as you would declare the raw
   * type.
   * For classes it's the name defined in the Java Language Specification <br>
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
