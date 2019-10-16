package ar.com.kfgodel.diamond.unit.testobjects.modifiers;

import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;

/**
 * This type serves as a test object for modifier tests
 * Created by kfgodel on 18/10/14.
 */
public class PublicMembersTestObject {

  public PublicMembersTestObject() {

  }

  public PublicMembersTestObject(@TestAnnotation1 final Integer parameter) {

  }

  public int field;

  public void method() {


  }

  public void methodWithEqualParam(Integer parameter) {

  }

  public void methodWithDiffParamType(String parameter) {

  }

  public void methodWithDiffParamName(Integer otherName) {

  }

}
