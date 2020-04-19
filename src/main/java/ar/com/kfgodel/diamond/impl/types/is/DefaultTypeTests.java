package ar.com.kfgodel.diamond.impl.types.is;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;

import java.util.function.BiPredicate;

/**
 * This class is the default implementation for a type tests api
 * Date: 8/12/19 - 18:11
 */
public class DefaultTypeTests implements TypeTests {

  private TypeInstance type;
  private BiPredicate<TypeInstance, TypeInstance> assignabilityPredicate;
  private BiPredicate<TypeInstance, Object> instancePredicate;

  @Override
  public boolean a(TypeCategory testedTypeCategory) {
    return type.categories().anyMatch(testedTypeCategory::equals);
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
  public boolean instance(Object anObject) {
    return instancePredicate.test(type, anObject);
  }

  @Override
  public boolean subTypeOf(TypeInstance objectType) {
    return type.runtime().type().is().assignableTo(objectType.runtime().type());
  }

  @Override
  public boolean superTypeOf(TypeInstance objectType) {
    return objectType.is().subTypeOf(type);
  }


  public static DefaultTypeTests create(TypeInstance type,
                                        BiPredicate<TypeInstance, TypeInstance> assignabilityPredicate,
                                        BiPredicate<TypeInstance, Object> instancePredicate) {
    DefaultTypeTests tests = new DefaultTypeTests();
    tests.type = type;
    tests.assignabilityPredicate = assignabilityPredicate;
    tests.instancePredicate = instancePredicate;
    return tests;
  }


}
