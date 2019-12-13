package ar.com.kfgodel.diamond.impl.members.generics;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.generics.Generics;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Executable;
import java.lang.reflect.TypeVariable;
import java.util.function.Supplier;

/**
 * This type represents the generics supplier for
 * Created by kfgodel on 01/11/14.
 */
public class ExecutableGenericsCalculator implements Supplier<Generics> {

  private Executable nativeExecutable;

  @Override
  public Generics get() {
    final TypeVariable<?>[] nativeTypes = nativeExecutable.getTypeParameters();
    final Nary<TypeInstance> typeParameters = Diamond.types().from(nativeTypes);
    return ParameterizedMemberGenerics.create(typeParameters);
  }

  public static ExecutableGenericsCalculator create(Executable nativeExecutable) {
    ExecutableGenericsCalculator calculator = new ExecutableGenericsCalculator();
    calculator.nativeExecutable = nativeExecutable;
    return calculator;
  }


}
