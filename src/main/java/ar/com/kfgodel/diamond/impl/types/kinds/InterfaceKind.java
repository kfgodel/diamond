package ar.com.kfgodel.diamond.impl.types.kinds;

import java.util.function.Predicate;

/**
 * This type represents the kind of interface types
 * Created by kfgodel on 03/02/15.
 */
public class InterfaceKind extends NativeClassKindSupport {
    @Override
    protected Predicate<Class<?>> getClassPredicate() {
        return Class::isInterface;
    }

    public static InterfaceKind create() {
        InterfaceKind kind = new InterfaceKind();
        return kind;
    }

}
