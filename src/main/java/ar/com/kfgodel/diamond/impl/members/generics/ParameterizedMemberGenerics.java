package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the generics information of a parameterized type member
 * Created by kfgodel on 01/11/14.
 */
public class ParameterizedMemberGenerics implements Generics {

    private Supplier<Stream<TypeInstance>> typeParameters;

    @Override
    public Stream<TypeInstance> parameters() {
        return typeParameters.get();
    }

    public static ParameterizedMemberGenerics create(Supplier<Stream<TypeInstance>> typeParameters) {
        ParameterizedMemberGenerics generics = new ParameterizedMemberGenerics();
        generics.typeParameters = typeParameters;
        return generics;
    }

}
