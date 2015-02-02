package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

/**
 * This type represents a poly invokable adapter around an invokable instance
 * Created by kfgodel on 02/02/15.
 */
public class PolyInvokable implements PolymorphicInvokable {

    private Invokable invokable;

    @Override
    public Object invoke(Object... arguments) {
        return invokable.invoke(arguments);
    }

    public static PolyInvokable create(Invokable invokable) {
        PolyInvokable polyInvokable = new PolyInvokable();
        polyInvokable.invokable = invokable;
        return polyInvokable;
    }

}
