package ar.com.kfgodel.diamond.api.sources;

/**
 * This type represents the sources of class names with the class defined
 * Created by kfgodel on 21/09/14.
 */
public interface TypeNames {

    /**
     * The name of the class without the package prefix. This is the shortest version
     * @return The short name for this type (for classes this is getSimpleName() )
     */
    String shortName();

    /**
     * The name prefixed with the package of this instance that identifies the type and can be used to load the native class instance
     * @return The VM name of the type ( for classes this is the name that can be used to load the class)
     */
    String classloaderName();

    /**
     * The name of the class without generics information as declared on source code
     * @return The name that identifies this type ( this is the name with dots instead of strange characters)
     */
    String canonicalName();

    /**
     * The name that identifies this type with its annotations and generics information.<br> This name could be used to declare
     * this exact type in source code
     * @return The name as a full type declaration (This is roughly equivalent to the source code for this type declaration)
     */
    String declarationName();

}
