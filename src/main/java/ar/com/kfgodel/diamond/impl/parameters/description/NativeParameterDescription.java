package ar.com.kfgodel.diamond.impl.parameters.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.annotations.NativeElementAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This type represents the description of a native parameter instance for diamond
 * Created by kfgodel on 07/11/14.
 */
public class NativeParameterDescription implements ParameterDescription {

    private Parameter nativeParameter;

    @Override
    public Supplier<TypeInstance> getDeclaredType() {
        return CachedValue.lazilyBy(() -> Diamond.types().from(nativeParameter.getAnnotatedType()));
    }

    @Override
    public Supplier<String> getName() {
        return nativeParameter::getName;
    }

    @Override
    public Supplier<Nary<Modifier>> getModifiers() {
        return NaryFromCollectionSupplier.lazilyBy(() -> Diamond.modifiers().fromParameter(nativeParameter.getModifiers()));
    }

    @Override
    public Supplier<Stream<Annotation>> getAnnotations() {
        return NativeElementAnnotationsSupplier.create(nativeParameter);
    }

    public static NativeParameterDescription create(Parameter nativeParameter) {
        NativeParameterDescription description = new NativeParameterDescription();
        description.nativeParameter = nativeParameter;
        return description;
    }

}
