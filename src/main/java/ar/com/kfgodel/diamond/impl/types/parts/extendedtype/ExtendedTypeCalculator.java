package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the object that knows how to calculate the extended type (with type arguments)
 * from a {@link Class} instance
 *
 * Created by kfgodel on 27/09/14.
 */
public class ExtendedTypeCalculator implements Supplier<Nary<TypeInstance>> {

  private Class<?> nativeClass;
  private Supplier<Nary<TypeInstance>> typeArguments;

  @Override
  public Nary<TypeInstance> get() {
    return Nary.of(nativeClass.getAnnotatedSuperclass()) // It may be null
      .map(annotatedType -> describeAsTypeInstance(nativeClass, annotatedType));
  }

  private TypeInstance describeAsTypeInstance(Class<?> nativeClass, AnnotatedType annotatedSuperclass) {
    TypeInstance describedType = Diamond.types().fromParameterizedNativeTypes(
      nativeClass,
      typeArguments.get().collect(Collectors.toList()),
      annotatedSuperclass,
      nativeClass.getGenericSuperclass()
    );
    return describedType;
  }

  public static ExtendedTypeCalculator create(Class<?> nativeClass, Supplier<Nary<TypeInstance>> typeArguments) {
    ExtendedTypeCalculator calculator = new ExtendedTypeCalculator();
    calculator.nativeClass = nativeClass;
    calculator.typeArguments = typeArguments;
    return calculator;
  }

}
