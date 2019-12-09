package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

/**
 * This type represents the category of referential types (non primitives)
 * Equivalent to negation of {@link Class#isPrimitive()}
 * Created by kfgodel on 03/02/15.
 */
public class ReferenceCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    //I think there's no current way to define this in a positive way
    return !Categories.PRIMITIVE.contains(testedType);
  }

  public static ReferenceCategory create() {
    ReferenceCategory category = new ReferenceCategory();
    return category;
  }

}
