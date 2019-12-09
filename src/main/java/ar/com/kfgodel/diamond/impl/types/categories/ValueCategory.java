package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

/**
 * This type represents the category of types that can be defined as literal values on variables.<br>
 * In contrast with {@link PrimitiveCategory} this includes referential types as {@link Boolean}
 * and {@link String}
 * Created by kfgodel on 03/02/15.
 */
public class ValueCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    return Categories.TEXT.contains(testedType)
      || Categories.BOOLEAN.contains(testedType)
      || Categories.NUMERIC.contains(testedType);
  }

  public static ValueCategory create() {
    ValueCategory category = new ValueCategory();
    return category;
  }

}
