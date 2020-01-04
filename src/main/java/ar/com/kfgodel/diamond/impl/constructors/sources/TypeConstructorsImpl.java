package ar.com.kfgodel.diamond.impl.constructors.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.members.executables.FilterByParameterType;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents a fixed set of constructors for a type
 * Created by kfgodel on 15/10/14.
 */
public class TypeConstructorsImpl implements TypeConstructors {

  private Supplier<Nary<TypeConstructor>> typeConstructors;

  @Override
  public Nary<TypeConstructor> all() {
    return typeConstructors.get();
  }

  @Override
  public Unary<TypeConstructor> niladic() {
    return withParameters()
      .asUni();
  }

  @Override
  public Nary<TypeConstructor> withParameters(TypeInstance... paramTypes) {
    return FilterByParameterType.create(all(), paramTypes);
  }

  @Override
  public Nary<TypeConstructor> withNativeParameters(Type... parameterTypes) {
    return withParameters(Diamond.ofNative(parameterTypes));
  }

  public static TypeConstructorsImpl create(Supplier<Nary<TypeConstructor>> constructorSupplier) {
    TypeConstructorsImpl methodSource = new TypeConstructorsImpl();
    methodSource.typeConstructors = constructorSupplier;
    return methodSource;
  }

}
