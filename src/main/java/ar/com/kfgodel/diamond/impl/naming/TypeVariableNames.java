package ar.com.kfgodel.diamond.impl.naming;

import ar.com.kfgodel.diamond.api.naming.TypeNames;

/**
 * This type represents the a type variable names
 * Created by kfgodel on 04/10/14.
 */
public class TypeVariableNames implements TypeNames {

    private String variableName;
    private String typeName;

    @Override
    public String shortName() {
        return variableName;
    }

    @Override
    public String classloaderName() {
        return typeName();
    }

    @Override
    public String canonicalName() {
        return typeName();
    }

    @Override
    public String typeName() {
        return typeName;
    }

    @Override
    public String bareName() {
        return variableName;
    }

    public static TypeVariableNames create(String variableName, String typeName) {
        TypeVariableNames names = new TypeVariableNames();
        names.variableName = variableName;
        names.typeName = typeName;
        return names;
    }

}
