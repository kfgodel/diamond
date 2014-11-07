package ar.com.kfgodel.diamond.impl.members.parameters;

import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the supplier of member  when the supplier is not defined
 * Created by kfgodel on 07/11/14.
 */
public class UndefinedMemberParameters implements Supplier<Nary<ExecutableParameter>> {

    private TypeMember member;

    @Override
    public Nary<ExecutableParameter> get() {
        throw new DiamondException("The parameters for the member["+member+"] were not defined");
    }

    public static UndefinedMemberParameters create(TypeMember type) {
        UndefinedMemberParameters supplier = new UndefinedMemberParameters();
        supplier.member = type;
        return supplier;
    }

}
