package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.constructors.description.ArrayConstructorDescription;
import ar.com.kfgodel.streams.OneElementStream;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the artificial constructor supplier for native array types.<br>
 *  Arrays don't have a constructor method but they can be instantiated with an int argument using native reflection
 *  helpers. This supplier fills the gap to simulate a constructor for arrays
 * Created by kfgodel on 16/10/14.
 */
public class ArraysConstructorSupplier implements Supplier<Stream<TypeConstructor>> {
    private Class<?> nativeArrayType;

    @Override
    public Stream<TypeConstructor> get() {
        ConstructorDescription arrayConstructorDescription = ArrayConstructorDescription.create(nativeArrayType);
        return OneElementStream.create(Diamond.constructors().from(arrayConstructorDescription));
    }

    public static ArraysConstructorSupplier create(Class<?> nativeArrayClass) {
        ArraysConstructorSupplier supplier = new ArraysConstructorSupplier();
        supplier.nativeArrayType = nativeArrayClass;
        return supplier;
    }

}
