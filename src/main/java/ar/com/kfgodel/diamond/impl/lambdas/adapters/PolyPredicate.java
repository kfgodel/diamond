package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import java.util.function.Predicate;

/**
 * This type represents a poly invokable adapter around a predicate instance
 * Created by kfgodel on 02/02/15.
 */
public class PolyPredicate extends PolyAdapterSupport {
    
    private Predicate predicate;

    @Override
    public Object invoke(Object... arguments) {
        if(arguments == null){
            throw new IllegalArgumentException("null is not accepted as valid predicate argument");
        }
        if(arguments.length != 1){
            throw new IllegalArgumentException("Predicate invokable only accepts 1 argument");
        }
        return this.test(arguments[0]);
    }

    @Override
    public boolean test(Object predicated) {
        return predicate.test(predicated);
    }

    public static PolyPredicate create(Predicate predicate) {
        PolyPredicate polyPredicate = new PolyPredicate();
        polyPredicate.predicate = predicate;
        return polyPredicate;
    }

    @Override
    public Object adaptedCode() {
        return predicate;
    }
}
