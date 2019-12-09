package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.lazyvalue.impl.CachedValue;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represent a fragment of code that can extract the extended type from a native class instance
 * Created by kfgodel on 27/09/14.
 */
public class ExtendedTypeSupplier implements Supplier<Nary<TypeInstance>> {

  private Supplier<TypeInstance> extendedType;

  @Override
  public Nary<TypeInstance> get() {
    TypeInstance typeInstance = extendedType.get();
    if (typeInstance == null) {
      return Nary.empty();
    }
    return Nary.of(typeInstance);
  }


  public static Supplier<Nary<TypeInstance>> create(Class<?> nativeClass, Stream<TypeInstance> typeArguments) {
    ExtendedTypeSupplier supplier = new ExtendedTypeSupplier();
    supplier.extendedType = CachedValue.from(() -> describeExtendedType(nativeClass, typeArguments));
    return supplier;
  }

  private static TypeInstance describeExtendedType(Class<?> nativeClass, Stream<TypeInstance> typeArguments) {
    AnnotatedType annotatedSuperclass = nativeClass.getAnnotatedSuperclass();
    if (annotatedSuperclass == null) {
      // There's no extended type
      return null;
    }
    TypeInstance describedType = Diamond.types().fromParameterizedNativeTypes(nativeClass, typeArguments.collect(Collectors.toList()),
      annotatedSuperclass, nativeClass.getGenericSuperclass());
    return describedType;
  }

}
