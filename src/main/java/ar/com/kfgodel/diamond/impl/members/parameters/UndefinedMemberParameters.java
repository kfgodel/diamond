package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents an undefined member parameters
 * Created by kfgodel on 01/11/14.
 */
public class UndefinedMemberParameters implements Supplier<Stream<TypeInstance>> {

    private TypeMember member;

    @Override
    public Stream<TypeInstance> get() {
        throw new DiamondException("The parameters for the member["+member+"] were not defined");
    }

    public static UndefinedMemberParameters create(TypeMember member) {
        UndefinedMemberParameters parameters = new UndefinedMemberParameters();
        parameters.member = member;
        return parameters;
    }

}
