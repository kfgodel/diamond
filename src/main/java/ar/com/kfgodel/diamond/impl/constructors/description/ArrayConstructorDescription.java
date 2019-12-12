package ar.com.kfgodel.diamond.impl.constructors.description;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.invokable.PolymorphicInvokable;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.constructors.equality.ConstructorEquality;
import ar.com.kfgodel.diamond.impl.equals.CachedTokenCalculator;
import ar.com.kfgodel.diamond.impl.members.exceptions.NoExceptionsSupplier;
import ar.com.kfgodel.diamond.impl.members.generics.UnGenerifiedMemberGenerics;
import ar.com.kfgodel.diamond.impl.natives.invokables.constructors.NativeArrayConstructor;
import ar.com.kfgodel.diamond.impl.types.parts.annotations.NoAnnotationsSupplier;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.lazyvalue.impl.SelfSupplier;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NarySupplierFromElement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This type represents the description of an artificial array type constructor
 * Created by kfgodel on 16/10/14.
 */
public class ArrayConstructorDescription implements ConstructorDescription {

  private Class<?> nativeArrayClass;

  @Override
  public Supplier<Nary<ExecutableParameter>> getParameters() {
    return NarySupplierFromElement.lazilyBy(() -> {
      return Diamond.parameters().fromDescription(ArrayConstructorParameterDescription.INSTANCE);
    });
  }

  @Override
  public Supplier<TypeInstance> getDeclaringType() {
    return SelfSupplier.of(Diamond.of(nativeArrayClass));
  }

  @Override
  public Supplier<Nary<Modifier>> getModifiers() {
    // Array creation is similar to public visibility
    return NarySupplierFromElement.from(Modifiers.PUBLIC);
  }

  @Override
  public Supplier<PolymorphicInvokable> getInvoker() {
    return CachedValue.from(() -> NativeArrayConstructor.create(nativeArrayClass));
  }

  @Override
  public Supplier<String> getName() {
    return CachedValue.from(nativeArrayClass::getSimpleName);
  }

  @Override
  public Supplier<Nary<Annotation>> getAnnotations() {
    return NoAnnotationsSupplier.INSTANCE;
  }

  @Override
  public Supplier<Generics> getGenerics() {
    return UnGenerifiedMemberGenerics::instance;
  }

  @Override
  public Supplier<Nary<Constructor>> getNativeConstructor() {
    return Nary::empty;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getDeclaredExceptions() {
    return NoExceptionsSupplier::create;
  }

  public static ArrayConstructorDescription create(Class<?> nativeArrayType) {
    ArrayConstructorDescription description = new ArrayConstructorDescription();
    description.nativeArrayClass = nativeArrayType;
    return description;
  }

  @Override
  public Function<TypeConstructor, Object> getIdentityToken() {
    return CachedTokenCalculator.create(ConstructorEquality.INSTANCE::calculateTokenFor);
  }
}
