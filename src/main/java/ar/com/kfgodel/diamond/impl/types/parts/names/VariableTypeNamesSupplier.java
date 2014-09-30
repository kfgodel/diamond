package ar.com.kfgodel.diamond.impl.types.parts.names;

import ar.com.kfgodel.diamond.api.sources.TypeNames;
import ar.com.kfgodel.diamond.impl.naming.SingleName;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents the name supplier for variable types (type variables and wildcards)
 * Created by kfgodel on 28/09/14.
 */
public class VariableTypeNamesSupplier implements Supplier<TypeNames> {

    private Object nativeType;

    public static VariableTypeNamesSupplier create(Object nativeType) {
        VariableTypeNamesSupplier supplier = new VariableTypeNamesSupplier();
        supplier.nativeType = nativeType;
        return supplier;
    }

    @Override
    public TypeNames get() {
        Type baseType;
        if(nativeType instanceof AnnotatedType){
            baseType = ((AnnotatedType) nativeType).getType();
        }else {
            baseType = (Type) nativeType;
        }
        return SingleName.create(baseType.getTypeName());
    }
}
