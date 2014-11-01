package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import ar.com.kfgodel.streams.StreamFromCollectionSupplier;

import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the immutable parameters list supplier for Executable instances
 * Created by kfgodel on 01/11/14.
 */
public class ImmutableMemberParameters {

    public static Supplier<Stream<TypeInstance>> create(Executable nativeExecutable) {
        return StreamFromCollectionSupplier.using(SuppliedValue.lazilyBy(
                () -> Arrays.stream(nativeExecutable.getAnnotatedParameterTypes())
                    .map((annotated) -> Diamond.types().from(annotated))
                    .collect(Collectors.toList())));
    }

}
