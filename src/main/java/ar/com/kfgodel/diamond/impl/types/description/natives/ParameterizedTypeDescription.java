package ar.com.kfgodel.diamond.impl.types.description.natives;

import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.types.description.support.UnannotatedFixedTypeDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.ParameterizedTypeArgumentsSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Supplier;

/**
 * This type represents the description of an unannotated parameterized native type
 * Created by kfgodel on 29/09/14.
 */
public class ParameterizedTypeDescription extends UnannotatedFixedTypeDescriptionSupport {

  private ParameterizedType nativeType;

  @Override
  protected Type getNativeType() {
    return nativeType;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    return ParameterizedTypeArgumentsSupplier.create(nativeType);
  }

  public static ParameterizedTypeDescription create(ParameterizedType nativeType) {
    ParameterizedTypeDescription description = new ParameterizedTypeDescription();
    description.nativeType = nativeType;
    return description;
  }

}
