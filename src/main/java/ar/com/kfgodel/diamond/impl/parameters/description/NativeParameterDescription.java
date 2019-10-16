package ar.com.kfgodel.diamond.impl.parameters.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.natives.suppliers.AnnotatedElementAnnotationsSupplier;
import ar.com.kfgodel.diamond.impl.parameters.ParameterEquality;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.function.Function;
import java.util.function.Supplier;

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
  public Supplier<Nary<Annotation>> getAnnotations() {
    return AnnotatedElementAnnotationsSupplier.create(nativeParameter);
  }

  @Override
  public Function<ExecutableParameter, Object> getIdentityToken() {
    return CachedTokenCalculator.create(ParameterEquality.INSTANCE::calculateTokenFor);
  }

  public static NativeParameterDescription create(Parameter nativeParameter) {
    NativeParameterDescription description = new NativeParameterDescription();
    description.nativeParameter = nativeParameter;
    return description;
  }

}
