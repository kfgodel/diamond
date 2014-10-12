package ar.com.kfgodel.diamond.impl.naming;

import ar.com.kfgodel.diamond.api.types.TypeNames;

/**
 * This type represents the set of class names for a class
 * Created by kfgodel on 21/09/14.
 */
public class ClassNames implements TypeNames {
    private String shortName;
    private String classloaderName;
    private String canonicalName;
    private String typeName;
    private String bareName;

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
    public String typeName() {
        return typeName;
    }

    @Override
    public String bareName() {
        return bareName;
    }

    public static ClassNames create(Class<?> nativeClass, String typeName) {
        ClassNames classNames = new ClassNames();
        classNames.shortName = nativeClass.getSimpleName();
        classNames.classloaderName = nativeClass.getName();
        classNames.canonicalName = nativeClass.getCanonicalName();
        classNames.typeName = typeName;
        if(nativeClass.getComponentType() != null){
            //It's an array
            classNames.bareName = "[]";
        }else{
            classNames.bareName = nativeClass.getCanonicalName();
        }
        return classNames;
    }

}
