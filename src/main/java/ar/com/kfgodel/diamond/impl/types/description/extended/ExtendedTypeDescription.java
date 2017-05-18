package ar.com.kfgodel.diamond.impl.types.description.extended;

import ar.com.kfgodel.diamond.api.types.TypeDescription;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.inheritance.InheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.inheritance.FixedTypeInheritanceDescription;
import ar.com.kfgodel.diamond.impl.types.description.support.DelegatedDescriptionSupport;
import ar.com.kfgodel.diamond.impl.types.parts.typearguments.ExtendedTypeArgumentsSupplier;
import ar.com.kfgodel.nary.api.Nary;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This type represents the description of a type that is extended by another with actual type arguments.
 * The described type has type parameters, and its subtype has type arguments defined for them
 * Created by kfgodel on 29/09/14.
 */
public class ExtendedTypeDescription extends DelegatedDescriptionSupport {

  private TypeDescription baseDescription;
  private Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer;

  @Override
  protected TypeDescription getDelegateDescription() {
    return baseDescription;
  }

  @Override
  public Supplier<Nary<TypeInstance>> getTypeArguments() {
    Supplier<Nary<TypeInstance>> typeArguments = baseDescription.getTypeArguments();
    return ExtendedTypeArgumentsSupplier.create(typeArguments, extendedTypeArgumentsReplacer);
  }

  @Override
  public InheritanceDescription getInheritanceDescription() {
    Nary<Class<?>> rawClasses = baseDescription.getRawClassSupplier().get();
    Class<?> singleRawClass = rawClasses.get(); // Aca asumimos que solo hay una
    return FixedTypeInheritanceDescription.create(singleRawClass, getTypeArguments());
  }

  public static ExtendedTypeDescription create(TypeDescription otherTypeDescription, Consumer<List<TypeInstance>> extendedTypeArgumentsReplacer) {
    ExtendedTypeDescription description = new ExtendedTypeDescription();
    description.baseDescription = otherTypeDescription;
    description.extendedTypeArgumentsReplacer = extendedTypeArgumentsReplacer;
    return description;
  }

}
