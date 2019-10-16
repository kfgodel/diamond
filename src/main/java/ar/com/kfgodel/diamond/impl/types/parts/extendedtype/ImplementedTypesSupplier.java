package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.impl.NaryFromCollectionSupplier;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This type represents the code that can extract the implemented types from a raw class
 * Created by kfgodel on 12/02/15.
 */
public class ImplementedTypesSupplier implements Supplier<Nary<TypeInstance>> {
  private NaryFromCollectionSupplier<TypeInstance> implementedTypes;

  @Override
  public Nary<TypeInstance> get() {
    return implementedTypes.get();
  }


  public static Supplier<Nary<TypeInstance>> create(Class<?> nativeClass, Stream<TypeInstance> typeArguments) {
    ImplementedTypesSupplier supplier = new ImplementedTypesSupplier();
    supplier.implementedTypes = NaryFromCollectionSupplier.lazilyBy(() -> describeImplementedTypes(nativeClass, typeArguments));
    return supplier;
  }

  private static Collection<TypeInstance> describeImplementedTypes(Class<?> nativeClass, Stream<TypeInstance> typeArguments) {
    AnnotatedType[] annotatedInterfaces = nativeClass.getAnnotatedInterfaces();
    if (annotatedInterfaces.length == 0) {
      //Optimization if there are no interfaces
      return Collections.emptyList();
    }
    List<TypeInstance> actualArguments = typeArguments.collect(Collectors.toList());


    List<TypeInstance> describedTypes = new ArrayList<>(annotatedInterfaces.length);
    Type[] genericInterfaces = nativeClass.getGenericInterfaces();
    for (int i = 0; i < annotatedInterfaces.length; i++) {
      AnnotatedType annotatedInterface = annotatedInterfaces[i];
      Type genericInterface = genericInterfaces[i];
      TypeInstance describedType = Diamond.types().fromParameterizedNativeTypes(nativeClass, actualArguments, annotatedInterface, genericInterface);
      describedTypes.add(describedType);
    }
    return describedTypes;
  }

}
