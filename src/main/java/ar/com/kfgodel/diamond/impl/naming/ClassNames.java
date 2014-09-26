package ar.com.kfgodel.diamond.impl.naming;

import ar.com.kfgodel.diamond.api.sources.TypeDefinedTypeNamesSource;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassNames implements TypeDefinedTypeNamesSource {
    private String shortName;
    private String classloaderName;
    private String canonicalName;
    private String declarationName;

    @Override
    public String shortName() {
        return shortName;
    }

    @Override
    public String classloaderName() {
        return classloaderName;
    }

    @Override
    public String canonicalName() {
        return canonicalName;
    }

    @Override
    public String declarationName() {
        return declarationName;
    }

    public static ClassNames create(Class<?> nativeClass, String declarationSupplier) {
        ClassNames classNames = new ClassNames();
        classNames.shortName = nativeClass.getSimpleName();
        classNames.classloaderName = nativeClass.getName();
        classNames.canonicalName = nativeClass.getCanonicalName();
        classNames.declarationName = declarationSupplier;
        return classNames;
    }

}
