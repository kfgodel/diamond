package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.parameters.ParameterEquality;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the description of the parameter needed for the artificial array constructor
 * Created by kfgodel on 07/11/14.
 */
public class ArrayConstructorParameterDescription implements ParameterDescription {

  public static final String SIZE_PARAMETER_NAME = "size";
  public static ArrayConstructorParameterDescription INSTANCE = new ArrayConstructorParameterDescription();

  @Override
  public Supplier<TypeInstance> getDeclaredType() {
    return CachedValue.from(() -> Diamond.of(int.class));
  }

  @Override
  public Supplier<String> getName() {
    return () -> SIZE_PARAMETER_NAME;
  }

  @Override
  public Supplier<Nary<Modifier>> getModifiers() {
    return Nary::empty;
  }

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return Nary::empty;
  }

  @Override
  public Function<ExecutableParameter, Object> getIdentityToken() {
    return CachedTokenCalculator.create(ParameterEquality.INSTANCE::calculateTokenFor);
  }


  public static ArrayConstructorParameterDescription create() {
    ArrayConstructorParameterDescription description = new ArrayConstructorParameterDescription();
    return description;
  }

}
