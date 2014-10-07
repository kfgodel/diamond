package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.generics.ActualArgumentReplacer;
import ar.com.kfgodel.diamond.impl.generics.ParametrizationAnalyzer;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.extended.ExtendedTypeDescription;

import java.lang.reflect.AnnotatedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represent a fragment of code that can extract the extended type from a native class instance
 * Created by kfgodel on 27/09/14.
 */
public class ExtendedTypeSupplier implements Supplier<Optional<TypeInstance>> {

    private Class<?> nativeClass;
    private List<TypeInstance> typeArguments;

    @Override
    public Optional<TypeInstance> get() {
        AnnotatedType annotatedSuperclass = nativeClass.getAnnotatedSuperclass();
        if(annotatedSuperclass == null){
            // There's no extended type
            return Optional.empty();
        }
        TypeDescription supertypeDescription = TypeDescriptor.INSTANCE.describe(annotatedSuperclass);
        ActualArgumentReplacer typeArgumentsReplacer = ActualArgumentReplacer.create(typeArguments, ParametrizationAnalyzer.create(nativeClass).get());
        ExtendedTypeDescription extendedTypeDescription = ExtendedTypeDescription.create(supertypeDescription, typeArgumentsReplacer);
        return Optional.of(Diamond.types().fromDescription(extendedTypeDescription));
    }

    public static ExtendedTypeSupplier create(Class<?> nativeClass, Stream<TypeInstance> typeArguments) {
        ExtendedTypeSupplier supplier = new ExtendedTypeSupplier();
        supplier.nativeClass = nativeClass;
        supplier.typeArguments = typeArguments.collect(Collectors.toList());
        return supplier;
    }

}
