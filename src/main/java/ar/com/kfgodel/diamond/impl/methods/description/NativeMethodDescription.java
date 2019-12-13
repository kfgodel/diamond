package ar.com.kfgodel.diamond.impl.methods.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.methods.MethodDescription;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.members.defaults.MethodDefaultValueSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.ExecutableGenericsSupplier;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;
import ar.com.kfgodel.diamond.impl.natives.invokables.methods.NativeMethodInvokerGenerator;
import ar.com.kfgodel.diamond.impl.natives.suppliers.AnnotatedElementAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a the method description of a native method
 * Created by kfgodel on 07/10/14.
 */
public class NativeMethodDescription implements MethodDescription {

  private Method nativeMethod;

  @Override
  public Supplier<String> getName() {
    return nativeMethod::getName;
  }

  @Override
  public Supplier<TypeInstance> getReturnType() {
    return CachedValue.from(() -> Diamond.types().from(nativeMethod.getAnnotatedReturnType()));
  }

  @Override
  public Supplier<Nary<ExecutableParameter>> getParameters() {
    return CachedValues.adapting(() -> {
      return Diamond.parameters().from(nativeMethod.getParameters());
    });
  }

  @Override
  public Supplier<TypeInstance> getDeclaringType() {
    return CachedValue.from(() -> {
      return Diamond.of(nativeMethod.getDeclaringClass());
    });
  }

  @Override
  public Supplier<Nary<Modifier>> getModifiers() {
    return CachedValues.adapting(() -> {
      return Diamond.modifiers().from(nativeMethod);
    });
  }

  @Override
  public Supplier<PolymorphicInvokable> getInvoker() {
    return CachedValue.from(() -> NativeMethodInvokerGenerator.INSTANCE.generateFor(nativeMethod));
  }

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return AnnotatedElementAnnotationsSupplier.create(nativeMethod);
  }

  @Override
  public Supplier<Generics> getGenerics() {
    return CachedValue.from(() -> ExecutableGenericsSupplier.create(nativeMethod));
  }

  @Override
  public Supplier<Nary<Object>> getDefaultValue() {
    return MethodDefaultValueSupplier.create(nativeMethod);
  }

  @Override
  public Supplier<Nary<Method>> getNativeMethod() {
    return () -> Nary.of(nativeMethod);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getDeclaredExceptions() {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(nativeMethod.getAnnotatedExceptionTypes());
    });
  }

  public static NativeMethodDescription create(Method nativeMethod) {
    NativeMethodDescription description = new NativeMethodDescription();
    description.nativeMethod = nativeMethod;
    return description;
  }

  @Override
  public Function<TypeMethod, Object> getIdentityToken() {
    return CachedTokenCalculator.create(MethodEquality.INSTANCE::calculateTokenFor);
  }
}
