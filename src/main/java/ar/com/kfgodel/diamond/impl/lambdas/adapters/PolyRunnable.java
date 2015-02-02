package ar.com.kfgodel.diamond.impl.lambdas.adapters;

import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;

/**
 * This type is a poly invokable adapter around a runnable instance
 * Created by kfgodel on 02/02/15.
 */
public class PolyRunnable implements PolymorphicInvokable {
    
    private Runnable runnable;
    
    @Override
    public Object invoke(Object... arguments) {
        this.run();
        return null;
    }

    @Override
    public void run() {
        runnable.run();
    }

    public static PolyRunnable create(Runnable runnable) {
        PolyRunnable polyRunnable = new PolyRunnable();
        polyRunnable.runnable = runnable;
        return polyRunnable;
    }

}
