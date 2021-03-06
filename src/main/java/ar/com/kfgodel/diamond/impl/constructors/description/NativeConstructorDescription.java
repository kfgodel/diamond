package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.constructors.equality.ConstructorEquality;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.members.generics.ExecutableGenericsCalculator;
import ar.com.kfgodel.diamond.impl.natives.invokables.constructors.NativeConstructorInvoker;
import ar.com.kfgodel.diamond.impl.natives.suppliers.AnnotatedElementAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.CachedValues;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents a description for a constructor from a native instance
 * Created by kfgodel on 15/10/14.
 */
public class NativeConstructorDescription implements ConstructorDescription {

  private Constructor<?> nativeConstructor;

  @Override
  public Supplier<Nary<ExecutableParameter>> getParameters() {
    return CachedValues.adapting(() -> {
      return Diamond.parameters().from(nativeConstructor.getParameters());
    });
  }

  @Override
  public Supplier<TypeInstance> getDeclaringType() {
    return CachedValue.from(() -> {
      return Diamond.of(nativeConstructor.getDeclaringClass());
    });
  }

  @Override
  public Supplier<Nary<Modifier>> getModifiers() {
    return CachedValues.adapting(() -> {
      return Diamond.modifiers().from(nativeConstructor);
    });
  }

  @Override
  public Supplier<PolymorphicInvokable> getInvoker() {
    return CachedValue.from(() -> NativeConstructorInvoker.create(nativeConstructor));
  }

  @Override
  public Supplier<String> getName() {
    return CachedValue.from(nativeConstructor::getName);
  }

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return AnnotatedElementAnnotationsSupplier.create(nativeConstructor);
  }

  @Override
  public Supplier<Generics> getGenerics() {
    return CachedValue.from(ExecutableGenericsCalculator.create(nativeConstructor));
  }

  @Override
  public Supplier<Unary<Constructor>> getNativeConstructor() {
    return () -> Nary.of(nativeConstructor);
  }

  @Override
  public Supplier<Nary<TypeInstance>> getDeclaredExceptions() {
    return CachedValues.adapting(() -> {
      return Diamond.types().from(nativeConstructor.getAnnotatedExceptionTypes());
    });
  }

  public static NativeConstructorDescription create(Constructor<?> nativeConstructor) {
    NativeConstructorDescription description = new NativeConstructorDescription();
    description.nativeConstructor = nativeConstructor;
    return description;
  }

  @Override
  public Function<TypeConstructor, Object> getIdentityToken() {
    return CachedTokenCalculator.create(ConstructorEquality.INSTANCE::calculateTokenFor);
  }
}
