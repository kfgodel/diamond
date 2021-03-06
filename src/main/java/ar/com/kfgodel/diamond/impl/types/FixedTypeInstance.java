package ar.com.kfgodel.diamond.impl.types;

import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.impl.constructors.sources.TypeConstructorsImpl;
import ar.com.kfgodel.diamond.impl.types.generics.ParameterizedTypeGenerics;
import ar.com.kfgodel.nary.api.Unary;

import java.util.function.Supplier;

/**
 * This type represents a concrete type that can be instantiated (class, parameterized type, array), excluding
 * variable types such as type variables or wildcards.<br>
 * <br>
 * Implementation notes:
 * - Due to class being an access point for many different use cases and data, most of their attributes are implemented
 * lazily so their don't prematurely explode the entire possible tree.
 * {@link ar.com.kfgodel.lazyvalue.impl.CachedValue} variables allow ignoring aspects of a class until needed
 * <p>
 * Created by kfgodel on 18/09/14.
 */
public class FixedTypeInstance extends TypeInstanceSupport {

  private Supplier<Unary<TypeInstance>> componentType;
  private TypeConstructors constructors;


  @Override
  public Unary<TypeInstance> componentType() {
    return componentType.get();
  }

  @Override
  public TypeConstructors constructors() {
    return constructors;
  }


  /**
   * Creates a class instance with its minimum data
   *
   * @param description the description for this type
   * @return The created instance
   */
  public static FixedTypeInstance create(TypeDescription description) {
    FixedTypeInstance fixedType = new FixedTypeInstance();
    fixedType.initializeSuper(description);
    fixedType.componentType = description.getComponentType();
    fixedType.constructors = TypeConstructorsImpl.create(description.getTypeConstructors());
    return fixedType;
  }

  @Override
  protected TypeGenerics createGenericsInfoFrom(TypeDescription description) {
    return ParameterizedTypeGenerics.create(
      description.getTypeParametersSupplier(),
      description.getTypeArguments()
    );
  }

}
