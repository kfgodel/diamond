package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

/**
 * This type represents the category of types that represent text
 * Created by kfgodel on 03/02/15.
 */
public class TextCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    return testedType.is().subTypeOf(Diamond.of(CharSequence.class));
  }

  public static TextCategory create() {
    TextCategory category = new TextCategory();
    return category;
  }

}
