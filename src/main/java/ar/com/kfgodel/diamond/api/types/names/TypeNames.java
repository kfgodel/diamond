package ar.com.kfgodel.diamond.api.types.names;

import java.lang.reflect.Type;

/**
 * This type defines accessor methods for the different names of a type.<br>
 * <br>
 * As different java types have different alternatives for names and they are differently implemented on each class
 * this interface tries to normalize or standarize. When a type doesn't have a name {@link #typeName()} is used.<br>
 * <br>
 * Check: https://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav
 * for all the details on different class names in the JVM
 *
 * Created by kfgodel on 21/09/14.
 */
public interface TypeNames {

  /**
   * The shortest name of this type. It doesn't include package prefix, generics information or annotations.<br>
   * For projects without duplicated classes this is usually the name you want to use in short string messages.<br>
   * <br>
   * For classes this is {@link Class#getSimpleName()}<br>
   * For arrays this is the short name of the component type + '[]'<br>
   * For wildcards this is '?'<br>
   * For type variables this is the name of the variable.<br>
   *
   * @return The shortest name to identify the type
   */
  String shortName();

  /**
   * The name of the type that is commonly used to identify it. This name usually doesn't have all the information
   * but enough for identification. It doesn't include type parameters or bounds.<br>
   * For classes, this is the {@link Class#getName()} which may be the binary name.<br>
   * For non classes types this is equal to {@link #typeName()}.<br>
   * <br>
   * Use this name with {@link Class#forName(String)} method
   *
   * @return The name this type on runtime
   */
  String commonName();

  /**
   * The full name of this type without type symbols and without generics information as you would declare the raw
   * type. It doesn't include generics or annotations.
   * For classes it's the name defined in the Java Language Specification <br>
   * For non classes types this is equal to {@link #typeName()}.
   *
   * @return The name that identifies this type ( for classes this is getCanonicalName() )
   */
  String canonicalName();

  /**
   * This is an informative string describing the type. Includes generics information (if available) as type
   * parameters or bounds. It doesn't include annotations.<br>
   * Because this methods is based on {@link Type#getTypeName()} results, it's not consistent on type bounds
   * (wildcards and variables behave differently).<br>
   *
   * @return The most complete native generated name
   */
  String typeName();

  /**
   * The complete name of this type stripped of annotations, type arguments, component type or type bounds.<br>
   * For classes this is the fully qualified name<br>
   * For arrays this is '[]'<br>
   * For wildcards this is '?'<br>
   * For type variables this is the name of the variable.<br>
   * <br>
   * This name allows you to discriminate the core of this type without considering its relations with other types
   *
   * @return The name that identifies this type without including relations with other types
   * (bounds, arguments, components, etc)
   */
  String bareName();

}
