package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

/**
 * This type represents the category for boolean types
 * Created by kfgodel on 03/02/15.
 */
public class BooleanCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    return testedType.is().subTypeOf(Diamond.of(boolean.class))
      || testedType.is().subTypeOf(Diamond.of(Boolean.class));
  }

  public static BooleanCategory create() {
    BooleanCategory category = new BooleanCategory();
    return category;
  }

  @Override
  public String name() {
    return "BOOLEAN";
  }

  @Override
  public String toString() {
    return name();
  }
}
