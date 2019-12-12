package ar.com.kfgodel.diamond.impl.types.parts.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.constructors.description.ArrayConstructorDescription;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents the object that knows how to extract {@link TypeConstructor}s from
 * a {@link Class} instance
 *
 * Created by kfgodel on 15/10/14.
 */
public class ClassConstructorExtractor implements Supplier<Nary<TypeConstructor>> {

  private Class<?> nativeClass;

  @Override
  public Nary<TypeConstructor> get() {
    if (nativeClass.isArray()) {
      // Artificial constructor for arrays: https://github.com/kfgodel/diamond/issues/88
      return userArtificialArrayConstructor();
    }else{
      return useDeclaredConstructors();
    }
  }

  private Nary<TypeConstructor> useDeclaredConstructors() {
    return Nary.from(nativeClass.getDeclaredConstructors())
      .map(declaredConstructors -> Diamond.constructors().from(declaredConstructors));
  }

  private Nary<TypeConstructor> userArtificialArrayConstructor() {
    return Nary.of(nativeClass)
      .map(ArrayConstructorDescription::create)
      .map(constructorDescription -> Diamond.constructors().fromDescription(constructorDescription));
  }

  public static ClassConstructorExtractor create(Class<?> nativeClass) {
    ClassConstructorExtractor calculator = new ClassConstructorExtractor();
    calculator.nativeClass = nativeClass;
    return calculator;
  }

}
