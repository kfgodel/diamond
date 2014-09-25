package ar.com.kfgodel.diamond.impl.naming;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.sources.TypeDefinedTypeNamesSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

/**
 * This type implements a null object for names that signals the error.<br>
 *     This instance is used as an initial value for undefined names instance
 * Created by kfgodel on 25/09/14.
 */
public class NoNames implements TypeDefinedTypeNamesSource {

    private TypeInstance ownerType;

    @Override
    public String shortName() {
        return throwError();
    }

    @Override
    public String classloaderName() {
        return throwError();
    }

    @Override
    public String canonicalName() {
        return throwError();
    }

    @Override
    public String declarationName() {
        return throwError();
    }

    private String throwError() {
        throw new DiamondException("The type["+this.ownerType+"] has no names source defined");
    }

    public static NoNames create(TypeInstance ownerType) {
        NoNames names = new NoNames();
        names.ownerType = ownerType;
        return names;
    }

}
