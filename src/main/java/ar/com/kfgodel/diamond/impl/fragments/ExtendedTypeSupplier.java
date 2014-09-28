package ar.com.kfgodel.diamond.impl.fragments;

import ar.com.kfgodel.diamond.api.ClassInstance;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.generics.ParametrizationAnalyzer;
import ar.com.kfgodel.diamond.impl.generics.X24;

import java.lang.reflect.AnnotatedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This type represent a fragment of code that can extract the extended type from a native class instance
 * Created by kfgodel on 27/09/14.
 */
public class ExtendedTypeSupplier implements Supplier<Optional<ClassInstance>> {

    private Class<?> nativeClass;
    private List<TypeInstance> typeArguments;

    @Override
    public Optional<ClassInstance> get() {
        AnnotatedType annotatedSuperclass = nativeClass.getAnnotatedSuperclass();
        if(annotatedSuperclass == null){
            return Optional.empty();
        }
        X24 x24 = X24.create();
        x24.setAnnotatedType(annotatedSuperclass);
        x24.setActualArgumentsReplacer(ActualArgumentReplacer.create(typeArguments, ParametrizationAnalyzer.create(nativeClass).get()));
        return Optional.of(Diamond.types().from(x24));
    }

    public static ExtendedTypeSupplier create(Class<?> nativeClass, List<TypeInstance> typeArguments) {
        ExtendedTypeSupplier supplier = new ExtendedTypeSupplier();
        supplier.nativeClass = nativeClass;
        supplier.typeArguments = typeArguments;
        return supplier;
    }

}
