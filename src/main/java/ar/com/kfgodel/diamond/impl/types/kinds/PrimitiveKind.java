package ar.com.kfgodel.diamond.impl.types.kinds;

import java.util.function.Predicate;

/**
 * This type represents the kind of primitive types
 * *
 * Created by kfgodel on 03/02/15.
 */
public class PrimitiveKind extends NativeClassKindSupport {
    
    @Override
    protected Predicate<Class<?>> getClassPredicate() {
        return Class::isPrimitive;
    }

    public static PrimitiveKind create() {
        PrimitiveKind kind = new PrimitiveKind();
        return kind;
    }

}
