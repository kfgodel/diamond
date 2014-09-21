package ar.com.kfgodel.diamond.impl.naming;

import ar.com.kfgodel.diamond.api.sources.ClassDefinedClassNameSource;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassNames implements ClassDefinedClassNameSource {
    private String shortName;
    private String classloaderName;
    private String canonicalName;

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
        // We will use canonical for now (until other types are merged)
        return canonicalName;
    }

    public static ClassNames create(Class<?> nativeClass) {
        ClassNames classNames = new ClassNames();
        classNames.shortName = nativeClass.getSimpleName();
        classNames.classloaderName = nativeClass.getName();
        classNames.canonicalName = nativeClass.getCanonicalName();
        return classNames;
    }

}
