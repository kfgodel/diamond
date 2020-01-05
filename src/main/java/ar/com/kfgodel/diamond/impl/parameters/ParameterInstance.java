package ar.com.kfgodel.diamond.impl.parameters;

import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.parameters.description.ParameterDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.strings.DebugPrinter;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents an executable parameter implementation
 * Created by kfgodel on 07/11/14.
 */
public class ParameterInstance implements ExecutableParameter {

  private Supplier<TypeInstance> declaredType;
  private Supplier<String> name;
  private Supplier<? extends Nary<Modifier>> modifiers;
  private Supplier<? extends Nary<Annotation>> annotations;
  private Function<ExecutableParameter, Object> identityToken;

  @Override
  public TypeInstance declaredType() {
    return declaredType.get();
  }

  public static ParameterInstance create(ParameterDescription description) {
    ParameterInstance parameter = new ParameterInstance();
    parameter.declaredType = description.getDeclaredType();
    parameter.name = description.getName();
    parameter.modifiers = description.getModifiers();
    parameter.annotations = description.getAnnotations();
    parameter.identityToken = description.getIdentityToken();
    return parameter;
  }

  @Override
  public String name() {
    return name.get();
  }

  @Override
  public Nary<Modifier> modifiers() {
    return modifiers.get();
  }

  @Override
  public boolean is(Modifier expectedModifier) {
    return modifiers().anyMatch(expectedModifier);
  }

  @Override
  public Nary<Annotation> annotations() {
    return annotations.get();
  }

  @Override
  public String toString() {
    return DebugPrinter.print(this);
  }

  @Override
  public Object getIdentityToken() {
    return identityToken.apply(this);
  }

  @Override
  public boolean equals(Object obj) {
    return ParameterEquality.INSTANCE.areEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return getIdentityToken().hashCode();
  }
}
