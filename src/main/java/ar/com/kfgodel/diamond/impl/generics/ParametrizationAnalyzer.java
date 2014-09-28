package ar.com.kfgodel.diamond.impl.generics;

import ar.com.kfgodel.diamond.impl.generics.parameters.SubstitutionAnalizer;
import ar.com.kfgodel.diamond.impl.generics.parameters.ValueSubstitution;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * This type represents the analyzer code that based on a class and its parameterized supertype generates
 * a parametrization that maps type arguments from a type and its supertype
 *
 * Created by kfgodel on 27/09/14.
 */
public class ParametrizationAnalyzer implements Supplier<SupertypeParameterization> {

    private Class<?> nativeClass;

    @Override
    public SupertypeParameterization get() {
        Type genericSupertype = nativeClass.getGenericSuperclass();
        if (!(genericSupertype instanceof ParameterizedType)) {
            // There's no parameters at all
            return NoParameterization.INSTANCE;
        }
        ParameterizedType parameterizedSupertype = (ParameterizedType) genericSupertype;
        List<Type> supertypeArguments = Arrays.asList(parameterizedSupertype.getActualTypeArguments());

        List<TypeVariable> declaredParameters = Arrays.asList(nativeClass.getTypeParameters());
        return createParametrizationFrom(declaredParameters, supertypeArguments);
    }

    private SupertypeParameterization createParametrizationFrom(List<TypeVariable> declaredParameters, List<Type> supertypeArguments) {
        List<ValueSubstitution> substitutions = SubstitutionAnalizer.analyze(declaredParameters, supertypeArguments);
        if(substitutions.isEmpty()){
            return NoParameterization.INSTANCE;
        }
        return GenericParameterization.create(substitutions);
    }

    public static ParametrizationAnalyzer create(Class<?> nativeClass) {
        ParametrizationAnalyzer analyzer = new ParametrizationAnalyzer();
        analyzer.nativeClass = nativeClass;
        return analyzer;
    }

}
