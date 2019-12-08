package ar.com.kfgodel.diamond.impl.types.is;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.is.TypeTests;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;

/**
 * This class is the default implementation for a type tests api
 * Date: 8/12/19 - 18:11
 */
public class DefaultTypeTests implements TypeTests {

  private TypeInstance type;

  @Override
  public boolean ofKind(Kind testedKind) {
    return type.kinds().anyMatch(testedKind::equals);
  }

  public static DefaultTypeTests create(TypeInstance type) {
    DefaultTypeTests tests = new DefaultTypeTests();
    tests.type = type;
    return tests;
  }


}
