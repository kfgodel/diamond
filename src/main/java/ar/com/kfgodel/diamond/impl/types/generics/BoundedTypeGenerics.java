package ar.com.kfgodel.diamond.impl.types.generics;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeBounds;

import java.util.function.Supplier;

/**
 * This type represents the generics information of a bounded type
 * Created by kfgodel on 05/10/14.
 */
public class BoundedTypeGenerics extends TypeGenericsSupport {

  private Supplier<TypeBounds> typeBounds;

  @Override
  public TypeBounds bounds() {
    return typeBounds.get();
  }

  public static BoundedTypeGenerics create(Supplier<TypeBounds> bounds, Supplier<TypeInstance> runtimeType) {
    BoundedTypeGenerics generics = new BoundedTypeGenerics();
    generics.typeBounds = bounds;
    generics.setRuntimeType(runtimeType);
    return generics;
  }


}
