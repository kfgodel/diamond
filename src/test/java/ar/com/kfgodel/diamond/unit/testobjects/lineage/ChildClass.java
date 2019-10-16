package ar.com.kfgodel.diamond.unit.testobjects.lineage;

import ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface1;
import ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface2;

import java.io.Serializable;

/**
 * This type represents the lower descendant of the test lineage
 * Created by kfgodel on 19/09/14.
 */
public class ChildClass<C> extends ParentClass<C, Integer> implements ChildInterface1<Integer>, ChildInterface2<String> {

  private int aPrivateField;
  protected float aProtectedField;
  public Object aPublicField;
  String aDefaultField;
  static public Double aStaticField;


  private ChildClass() {
    super(0);
  }

  protected ChildClass(String string) {
    super(0);
  }

  public ChildClass(String string, Serializable serializable) {
    super(0);
  }

  ChildClass(String string, Double doubleValue, Serializable serializable) {
    super(0);
  }


  public void aPublicMethod() {
  }

  protected void aProtectedMethod() {

  }

  private void aPrivateMethod() {

  }

  void aDefaultMethod() {

  }

  public static void aStaticMethod() {

  }
}
