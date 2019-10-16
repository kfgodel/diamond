package ar.com.kfgodel.diamond.impl.types.generics.parameters;

import ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization.GenericParametrization;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization.NoParametrization;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.parametrization.SupertypeParametrization;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.substitutions.SubstitutionAnalyzer;
import ar.com.kfgodel.diamond.impl.types.generics.parameters.substitutions.ValueSubstitution;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * This type represents the analyzer code that based on a class and its parameterized supertype generates
 * a parametrization that maps type arguments from a type and its supertype
 * <p>
 * Created by kfgodel on 27/09/14.
 */
public class SupertypeParametrizationAnalyzer implements Supplier<SupertypeParametrization> {

  private Class<?> subtype;
  private Type superType;

  @Override
  public SupertypeParametrization get() {
    if (!(superType instanceof ParameterizedType)) {
      // There's no parameters at all
      return NoParametrization.INSTANCE;
    }
    ParameterizedType parameterizedSupertype = (ParameterizedType) superType;
    List<Type> supertypeArguments = Arrays.asList(parameterizedSupertype.getActualTypeArguments());

    List<TypeVariable> declaredParameters = Arrays.asList(subtype.getTypeParameters());
    return createParametrizationFrom(declaredParameters, supertypeArguments);
  }

  private SupertypeParametrization createParametrizationFrom(List<TypeVariable> declaredParameters, List<Type> supertypeArguments) {
    List<ValueSubstitution> substitutions = SubstitutionAnalyzer.analyze(declaredParameters, supertypeArguments);
    if (substitutions.isEmpty()) {
      return NoParametrization.INSTANCE;
    }
    return GenericParametrization.create(substitutions);
  }

  public static SupertypeParametrizationAnalyzer create(Class<?> nativeClass, Type superType) {
    SupertypeParametrizationAnalyzer analyzer = new SupertypeParametrizationAnalyzer();
    analyzer.subtype = nativeClass;
    analyzer.superType = superType;
    return analyzer;
  }

}
