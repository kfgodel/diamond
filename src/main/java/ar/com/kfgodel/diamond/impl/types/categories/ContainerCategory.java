package ar.com.kfgodel.diamond.impl.types.categories;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This type represents the category of types that represent containers of elements (have other objects inside)
 * Created by kfgodel on 03/02/15.
 */
public class ContainerCategory implements TypeCategory {
  @Override
  public boolean contains(TypeInstance testedType) {
    return Categories.ARRAY.contains(testedType)
      || isSubTypeOf(testedType,
        Collection.class,
        Map.class,
        Optional.class,
        Stream.class,
        Enumeration.class,
        Iterable.class,
        Iterator.class
      );
  }

  /**
   * Indicates if the tested type is a subtype of any of the given classes
   * @param testedType The type to verify
   * @param possibleSuperClasses the classes to verify inheritance from
   * @return false if the tested type is not a subtype of any of the given classes
   */
  private boolean isSubTypeOf(TypeInstance testedType, Class<?>... possibleSuperClasses) {
    for (Class<?> possibleSuperClass : possibleSuperClasses) {
      if(testedType.is().subTypeOf(Diamond.of(possibleSuperClass))){
        return true;
      }
    }
    return false;
  }

  public static ContainerCategory create() {
    ContainerCategory category = new ContainerCategory();
    return category;
  }

}
