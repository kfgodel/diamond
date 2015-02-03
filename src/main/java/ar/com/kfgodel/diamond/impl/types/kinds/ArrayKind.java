package ar.com.kfgodel.diamond.impl.types.kinds;

import java.util.function.Predicate;

/**
 * This type represents the kind of array types
 * Created by kfgodel on 03/02/15.
 */
public class ArrayKind extends NativeClassKindSupport {
    @Override
    protected Predicate<Class<?>> getClassPredicate() {
        return Class::isArray;
    }

    public static ArrayKind create() {
        ArrayKind kind = new ArrayKind();
        return kind;
    }

}
