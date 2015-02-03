package ar.com.kfgodel.diamond.impl.types.kinds;

import java.util.function.Predicate;

/**
 * This type represents the kind of anonymous classes
 * Created by kfgodel on 03/02/15.
 */
public class AnonymousKind extends NativeClassKindSupport {
    @Override
    protected Predicate<Class<?>> getClassPredicate() {
        return Class::isAnonymousClass;
    }

    public static AnonymousKind create() {
        AnonymousKind kind = new AnonymousKind();
        return kind;
    }

}
