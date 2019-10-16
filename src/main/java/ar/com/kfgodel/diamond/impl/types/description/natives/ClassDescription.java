package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedFixedTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.componenttype.ArrayComponentTypeSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated native class
 * Created by kfgodel on 29/09/14.
 */
public class ClassDescription extends UnannotatedFixedTypeDescriptionSupport {

  private Class<?> nativeType;

  @Override
  protected Type getNativeType() {
    return nativeType;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getComponentType() {
    return ArrayComponentTypeSupplier.create(nativeType);
  }

  public static ClassDescription create(Class<?> nativeType) {
    ClassDescription description = new ClassDescription();
    description.nativeType = nativeType;
    return description;
  }

}
