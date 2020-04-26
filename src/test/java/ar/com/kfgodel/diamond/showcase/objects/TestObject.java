package ar.com.kfgodel.diamond.showcase.objects;

import com.google.common.base.MoreObjects;

/**
 * Simple test object to show in presentation
 * Date: 25/4/20 - 16:38
 */
public class TestObject {

  private Integer id;
  private String fieldA;

  public TestObject(int newId) {
    this.id = newId;
  }

  public Integer getId() {
    return id;
  }

  public String getFieldA() {
    return fieldA;
  }

  public void setFieldA(String fieldA) {
    this.fieldA = fieldA;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("fieldA", fieldA)
      .toString();
  }
}
