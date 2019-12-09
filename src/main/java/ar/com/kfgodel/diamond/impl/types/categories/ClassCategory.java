package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

/**
 * This type represents the category for instances of {@link Class} (abstract or concrete)
 * that do not represent interfaces.<br>
 * Equivalent to negation of {@link Class#isInterface()} for referential types
 *
 * Created by kfgodel on 03/02/15.
 */
public class ClassCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    //I think there's no way to define this in a positive manner
    return Categories.REFERENCE.contains(testedType) && !Categories.INTERFACE.contains(testedType);
  }

  public static ClassCategory create() {
    ClassCategory category = new ClassCategory();
    return category;
  }

}
