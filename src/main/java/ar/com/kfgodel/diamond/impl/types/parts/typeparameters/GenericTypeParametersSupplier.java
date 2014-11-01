package ar.com.kfgodel.diamond.impl.types.parts.typeparameters;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.streams.TypeStreamSupplierFromNativeTypeArray;

import java.lang.reflect.GenericDeclaration;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents a fragment of code that allows you to get the type parameters of a class
 * Created by kfgodel on 25/09/14.
 */
public class GenericTypeParametersSupplier {

    public static Supplier<Stream<TypeInstance>> create(GenericDeclaration genericDeclaration) {
        return TypeStreamSupplierFromNativeTypeArray.apply(genericDeclaration::getTypeParameters);
    }

}
