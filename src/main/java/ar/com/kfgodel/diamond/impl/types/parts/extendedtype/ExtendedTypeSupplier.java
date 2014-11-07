package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.TypeDescriptor;
import ar.com.kfgodel.diamond.impl.types.description.extended.ExtendedTypeDescription;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.ActualArgumentReplacer;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.ParametrizationAnalyzer;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.lang.reflect.AnnotatedType;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represent a fragment of code that can extract the extended type from a native class instance
 * Created by kfgodel on 27/09/14.
 */
public class ExtendedTypeSupplier implements Supplier<Nary<TypeInstance>> {

    private CachedValue<TypeInstance> extendedType;

    @Override
    public Nary<TypeInstance> get() {
        TypeInstance typeInstance = extendedType.get();
        if(typeInstance == null){
            return NaryFromNative.empty();
        }
        return NaryFromNative.of(typeInstance);
    }


    public static Supplier<Nary<TypeInstance>> create(Class<?> nativeClass, Stream<TypeInstance> typeArguments) {
        ExtendedTypeSupplier supplier = new ExtendedTypeSupplier();
        supplier.extendedType = CachedValue.lazilyBy(() -> {
            AnnotatedType annotatedSuperclass = nativeClass.getAnnotatedSuperclass();
            if (annotatedSuperclass == null) {
                // There's no extended type
                return null;
            }
            TypeDescription supertypeDescription = TypeDescriptor.INSTANCE.describe(annotatedSuperclass);
            ActualArgumentReplacer typeArgumentsReplacer = ActualArgumentReplacer.create(typeArguments.collect(Collectors.toList()), ParametrizationAnalyzer.create(nativeClass).get());
            ExtendedTypeDescription extendedTypeDescription = ExtendedTypeDescription.create(supertypeDescription, typeArgumentsReplacer);
            return Diamond.types().fromDescription(extendedTypeDescription);
        });
        return supplier;
    }

}
