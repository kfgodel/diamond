package ar.com.kfgodel.diamond.impl.types.is;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * This class is the default implementation for a type tests api
 * Date: 8/12/19 - 18:11
 */
public class DefaultTypeTests implements TypeTests {

  private TypeInstance type;
  private BiPredicate<TypeInstance, TypeInstance> assignabilityPredicate;
  private Predicate<Object> typeForPredicate;

  @Override
  public boolean ofKind(Kind testedKind) {
    return type.kinds().anyMatch(testedKind::equals);
  }

  @Override
  public boolean assignableFrom(TypeInstance possibleSubtype) {
    return assignabilityPredicate.test(type, possibleSubtype);
  }

  @Override
  public boolean assignableTo(TypeInstance possibleSuperType) {
    return possibleSuperType.is().assignableFrom(type);
  }

  @Override
  public boolean typeFor(Object anObject) {
    return typeForPredicate.test(anObject);
  }

  @Override
  public boolean subTypeOf(TypeInstance objectType) {
    return type.generics().runtimeType().is().assignableTo(objectType.generics().runtimeType());
  }

  @Override
  public boolean superTypeOf(TypeInstance objectType) {
    return objectType.is().subTypeOf(type);
  }


  public static DefaultTypeTests create(TypeInstance type,
                                        BiPredicate<TypeInstance, TypeInstance> assignabilityPredicate,
                                        Predicate<Object> typeForPredicate) {
    DefaultTypeTests tests = new DefaultTypeTests();
    tests.type = type;
    tests.assignabilityPredicate = assignabilityPredicate;
    tests.typeForPredicate = typeForPredicate;
    return tests;
  }


}
