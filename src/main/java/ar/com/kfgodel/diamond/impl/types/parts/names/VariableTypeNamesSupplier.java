package ar.com.kfgodel.diamond.impl.types.parts.names;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.impl.types.names.TypeVariableNames;
import ar.com.kfgodel.diamond.impl.types.names.WildCardNames;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.function.Supplier;

/**
 * This type represents the name supplier for variable types (type variables and wildcards)
 * Created by kfgodel on 28/09/14.
 */
public class VariableTypeNamesSupplier  {

    public static Supplier<TypeNames> create(Object nativeType) {
        return CachedValue.lazilyBy(() -> {
            Type unannotatedType;
            if (nativeType instanceof AnnotatedType) {
                unannotatedType = ((AnnotatedType) nativeType).getType();
            } else {
                unannotatedType = (Type) nativeType;
            }
            if (unannotatedType instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) unannotatedType;
                return TypeVariableNames.create(typeVariable.getName(), typeVariable.getTypeName());
            } else if (unannotatedType instanceof WildcardType) {
                return WildCardNames.create(unannotatedType.getTypeName());
            }
            throw new DiamondException("Variable type is unknown:" + unannotatedType);
        });
    }
}
