package ar.com.kfgodel.diamond.impl.types.is;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

import java.util.function.BiPredicate;

/**
 * This class is the default implementation for a type tests api
 * Date: 8/12/19 - 18:11
 */
public class DefaultTypeTests implements TypeTests {

  private TypeInstance type;
  private BiPredicate<TypeInstance, TypeInstance> assignabilityPredicate;

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


  public static DefaultTypeTests create(TypeInstance type, BiPredicate<TypeInstance, TypeInstance> assignabilityPredicate) {
    DefaultTypeTests tests = new DefaultTypeTests();
    tests.type = type;
    tests.assignabilityPredicate = assignabilityPredicate;
    return tests;
  }


}
