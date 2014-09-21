package ar.com.kfgodel.diamond.api.sources;

/**
 * This type represents the sources of class names with the class defined
 * Created by kfgodel on 21/09/14.
 */
public interface ClassDefinedClassNameSource {

    /**
     * The name of the class without the package prefix. This is the shortest version
     * @return The name of the class
     */
    String shortName();

    /**
     * The name prefixed with the package of this instance that identifies the type and can be used to load the native class instance
     * @return The VM name of the class
     */
    String classloaderName();

    /**
     * The name of the class without generics information as declared on source code
     * @return The name that identifies this type as a raw class
     */
    String canonicalName();

    /**
     * The name that identifies this type with its annotations and generics information.<br> This name could be used to declare
     * this exact type in source code
     * @return The name as a full type declaration
     */
    String declarationName();

}
