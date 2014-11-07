package ar.com.kfgodel.diamond.impl.members.executables;

import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import ar.com.kfgodel.streams.StreamEquality;

import java.util.Arrays;

/**
 * This type represents a filter of member types by an array of parameter types
 * Created by kfgodel on 07/11/14.
 */
public class FilterByParameterType {

    public static<T extends TypeMember> Nary<T> create(Nary<T> all, TypeInstance[] paramTypes) {
        return NaryFromNative.create(all
                .filter((member) -> StreamEquality.INSTANCE.areEquals(member.parameterTypes(), Arrays.stream(paramTypes))));
    }
}
