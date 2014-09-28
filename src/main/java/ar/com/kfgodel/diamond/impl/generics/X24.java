package ar.com.kfgodel.diamond.impl.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;

import java.lang.reflect.AnnotatedType;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by kfgodel on 27/09/14.
 */
public class X24 {

    private AnnotatedType annotatedType;
    private Consumer<List<TypeInstance>> actualArgumentsReplacer;

    public AnnotatedType getAnnotatedType() {
        return annotatedType;
    }

    public void setAnnotatedType(AnnotatedType annotatedType) {
        this.annotatedType = annotatedType;
    }


    public Consumer<List<TypeInstance>> getActualArgumentsReplacer() {
        return actualArgumentsReplacer;
    }

    public void setActualArgumentsReplacer(Consumer<List<TypeInstance>> actualArgumentsReplacer) {
        this.actualArgumentsReplacer = actualArgumentsReplacer;
    }

    public static X24 create() {
        X24 x24 = new X24();
        x24.actualArgumentsReplacer = (list)-> nothing();
        return x24;
    }

    private static void nothing() {

    }

}
