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
public class ExtendedTypeCalculator implements Supplier<TypeInstance> {

  private Class<?> nativeClass;
  private Supplier<Nary<TypeInstance>> typeArguments;

  @Override
  public TypeInstance get() {
    final AnnotatedType annotatedSuperclass = nativeClass.getAnnotatedSuperclass();
    if(annotatedSuperclass == null){
      // It may be null for some classes
      return null;
    }
    return describeAsTypeInstance(nativeClass, annotatedSuperclass);
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
