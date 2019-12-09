package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

/**
 * This type represents the category of types that represent numbers
 * Created by kfgodel on 03/02/15.
 */
public class NumericCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    // Either is a boxed number, or is a primitive (excluding boolean)
    return testedType.is().subTypeOf(Diamond.of(Number.class))
      || (Categories.PRIMITIVE.contains(testedType) && !Categories.BOOLEAN.contains(testedType));
  }

  public static NumericCategory create() {
    NumericCategory category = new NumericCategory();
    return category;
  }

}
