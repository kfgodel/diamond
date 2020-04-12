package ar.com.kfgodel.diamond.unit.testobjects.modifiers;

import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;
import com.google.common.base.MoreObjects;

/**
 * This type serves as a test object for modifier tests
 * Created by kfgodel on 18/10/14.
 */
public class PublicMembersTestObject {
  private Object[] args;


  public PublicMembersTestObject() {
    args = new Object[]{};
  }

  public PublicMembersTestObject(@TestAnnotation1 final Integer parameter) {
    args = new Object[]{parameter};
  }

  public int field;

  public void method() {
    args = new Object[]{};
  }

  public void methodWithEqualParam(Integer parameter) {
    args = new Object[]{parameter};
  }

  public void methodWithDiffParamType(String parameter) {
    args = new Object[]{parameter};
  }

  public void methodWithDiffParamName(Integer otherName) {
    args = new Object[]{otherName};
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("args", args)
      .toString();
  }
}
