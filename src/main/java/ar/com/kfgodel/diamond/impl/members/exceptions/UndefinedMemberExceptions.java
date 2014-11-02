package ar.com.kfgodel.diamond.impl.members.exceptions;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents an undefined exceptions for a member type
 * Created by kfgodel on 02/11/14.
 */
public class UndefinedMemberExceptions implements Supplier<Stream<TypeInstance>> {

    private TypeMember member;

    @Override
    public Stream<TypeInstance> get() {
        throw new DiamondException("Exceptions are not defined for member["+member+"]");
    }

    public static UndefinedMemberExceptions create(TypeMember member) {
        UndefinedMemberExceptions exceptions = new UndefinedMemberExceptions();
        exceptions.member = member;
        return exceptions;
    }

}
