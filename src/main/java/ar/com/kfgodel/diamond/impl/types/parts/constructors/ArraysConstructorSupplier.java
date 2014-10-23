package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.constructors.description.ArrayConstructorDescription;
import ar.com.kfgodel.streams.StreamFromElementSupplier;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the artificial constructor supplier for native array types.<br>
 *  Arrays don't have a constructor method but they can be instantiated with an int argument using native reflection
 *  helpers. This supplier fills the gap to simulate a constructor for arrays
 * Created by kfgodel on 16/10/14.
 */
public class ArraysConstructorSupplier {

    public static Supplier<Stream<TypeConstructor>> create(Class<?> nativeArrayClass) {
        return StreamFromElementSupplier.using(() -> {
            ConstructorDescription arrayConstructorDescription = ArrayConstructorDescription.create(nativeArrayClass);
            return Diamond.constructors().from(arrayConstructorDescription);
        });
    }

}
