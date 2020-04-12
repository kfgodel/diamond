package ar.com.kfgodel.diamond.unit.testobjects.constructors;

import com.google.common.base.MoreObjects;

/**
 * This type serves as a test object for functional constructors
 * Created by kfgodel on 26/10/14.
 */
public class FunctionalConstructorTestObject {

  private Object[] args;

  FunctionalConstructorTestObject() {
    args = new Object[0];
  }

  FunctionalConstructorTestObject(Integer a) {
    args = new Object[]{a};
  }

  FunctionalConstructorTestObject(Integer a, String b) {
    args = new Object[]{a, b};
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("args", args)
      .toString();
  }
}
