package ar.com.kfgodel.diamond.impl.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.cache.DiamondCache;
import ar.com.kfgodel.diamond.api.constructors.ConstructorDescription;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.sources.ConstructorSources;
import ar.com.kfgodel.diamond.impl.constructors.TypeConstructorInstance;
import ar.com.kfgodel.diamond.impl.constructors.description.ConstructorDescriptor;

import java.lang.reflect.Constructor;

/**
 * This type is the implementation for constructor sources
 * Created by kfgodel on 15/10/14.
 */
public class ConstructorsSourceImpl implements ConstructorSources {

  private DiamondCache cache;

  public static ConstructorsSourceImpl create(DiamondCache cache) {
    ConstructorsSourceImpl source = new ConstructorsSourceImpl();
    source.cache = cache;
    return source;
  }

  @Override
  public TypeConstructor from(Constructor<?> nativeConstructor) {
    return cache.reuseOrCreateRepresentationFor(nativeConstructor, () -> fromDescription(ConstructorDescriptor.INSTANCE.describe(nativeConstructor)));
  }

  @Override
  public TypeConstructor fromDescription(ConstructorDescription constructorDescription) {
    return createConstructorFrom(constructorDescription);
  }

  @Override
  public TypeConstructors in(Class<?> nativeClass) {
    return Diamond.of(nativeClass).constructors();
  }

  /**
   * Creates a new instances of a type constructor from the given description
   */
  private TypeConstructor createConstructorFrom(ConstructorDescription constructorDescription) {
    return TypeConstructorInstance.create(constructorDescription);
  }
}
