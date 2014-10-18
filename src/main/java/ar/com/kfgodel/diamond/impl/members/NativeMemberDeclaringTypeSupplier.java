package ar.com.kfgodel.diamond.impl.members;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.Member;
import java.util.function.Supplier;

/**
 * This type represents the supplier of declaring types for native class members
 * Created by kfgodel on 18/10/14.
 */
public class NativeMemberDeclaringTypeSupplier implements Supplier<TypeInstance> {

    private Member nativeMember;

    @Override
    public TypeInstance get() {
        return Diamond.of(nativeMember.getDeclaringClass());
    }

    public static NativeMemberDeclaringTypeSupplier create(Member nativeMember) {
        NativeMemberDeclaringTypeSupplier supplier = new NativeMemberDeclaringTypeSupplier();
        supplier.nativeMember = nativeMember;
        return supplier;
    }

}
