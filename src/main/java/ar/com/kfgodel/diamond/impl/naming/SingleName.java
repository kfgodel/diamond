package ar.com.kfgodel.diamond.impl.naming;

import ar.com.kfgodel.diamond.api.sources.TypeNames;

/**
 * This type represents a source for types that only have one name
 * Created by kfgodel on 25/09/14.
 */
public class SingleName implements TypeNames {

    private String name;

    @Override
    public String shortName() {
        return name;
    }

    @Override
    public String classloaderName() {
        return name;
    }

    @Override
    public String canonicalName() {
        return name;
    }

    @Override
    public String declarationName() {
        return name;
    }

    public static SingleName create(String name) {
        SingleName names = new SingleName();
        names.name = name;
        return names;
    }

}
