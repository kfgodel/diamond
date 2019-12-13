package ar.com.kfgodel.diamond.impl.types.parts.extendedtype;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This type represents the code that can extract the implemented types from a raw class
 * Created by kfgodel on 12/02/15.
 */
public class ImplementedTypesCalculator implements Supplier<Nary<TypeInstance>> {

  private Class<?> nativeClass;
  private Supplier<Nary<TypeInstance>> typeArguments;

  @Override
  public Nary<TypeInstance> get() {
    AnnotatedType[] annotatedInterfaces = nativeClass.getAnnotatedInterfaces();
    if (annotatedInterfaces.length == 0) {
      //Optimization if there are no interfaces
      return Nary.empty();
    } else {
      Type[] genericInterfaces = nativeClass.getGenericInterfaces();
      List<TypeInstance> implementedTypes = calculateDiamondFor(annotatedInterfaces, genericInterfaces);
      return Nary.from(implementedTypes);
    }
  }

  private List<TypeInstance> calculateDiamondFor(AnnotatedType[] annotatedInterfaces, Type[] genericInterfaces) {
    if(genericInterfaces.length != annotatedInterfaces.length){
      throw new DiamondException("Annotated interfaces["+annotatedInterfaces.length+"] and " +
        "generic interfaces["+genericInterfaces.length+"] arrays do not match size. " +
        "Can't calculate implemented types");
    }
    List<TypeInstance> actualArguments = getActualTypeArguments();
    List<TypeInstance> implementedTypes = new ArrayList<>(annotatedInterfaces.length);
    for (int i = 0; i < annotatedInterfaces.length; i++) {
      AnnotatedType annotatedInterface = annotatedInterfaces[i];
      Type genericInterface = genericInterfaces[i];
      TypeInstance describedType = Diamond.types()
        .fromParameterizedNativeTypes(nativeClass, actualArguments, annotatedInterface, genericInterface);
      implementedTypes.add(describedType);
    }
    return implementedTypes;
  }

  private List<TypeInstance> getActualTypeArguments() {
    final Nary<TypeInstance> typeArguments = this.typeArguments.get();
    return typeArguments.collect(Collectors.toList());
  }

  public static Supplier<Nary<TypeInstance>> create(Class<?> nativeClass, Supplier<Nary<TypeInstance>> typeArguments) {
    ImplementedTypesCalculator calculator = new ImplementedTypesCalculator();
    calculator.nativeClass = nativeClass;
    calculator.typeArguments = typeArguments;
    return calculator;
  }

}
