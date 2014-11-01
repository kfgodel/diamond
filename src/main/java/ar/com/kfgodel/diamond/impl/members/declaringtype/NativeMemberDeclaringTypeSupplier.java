package ar.com.kfgodel.diamond.impl.members.declaringtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;

import java.lang.reflect.Member;
import java.util.function.Supplier;

/**
 * This type represents the supplier of declaring types for native class members
 * Created by kfgodel on 18/10/14.
 */
public class NativeMemberDeclaringTypeSupplier {

    public static Supplier<TypeInstance> create(Member nativeMember) {
        return SuppliedValue.lazilyBy(()-> Diamond.of(nativeMember.getDeclaringClass()));
    }

}
