package ar.com.kfgodel.diamond.api.constructors;

import ar.com.kfgodel.diamond.api.parameters.ParameterizedSource;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.Unary;

/**
 * This type represents the source of constructors for a given type
 * Created by kfgodel on 15/10/14.
 */
public interface TypeConstructors extends ParameterizedSource<TypeConstructor> {
  /**
   * @return All the constructors for a type
   */
  Nary<TypeConstructor> all();

  /**
   * Returns the constructor that takes no arguments from this type.<br>
   * This can be 0, or 1 element
   *
   * @return An optional with the niladic constructor or empty if this type doesn't have one
   */
  Unary<TypeConstructor> niladic();

  /**
   * Returns the constructor that matches the given param types.<br>
   * Because constructors only differentiate by parameters this can be 0 or 1 elements.
   *
   * @param paramTypes The type of constructor arguments declared for the constructor
   * @return The constructor that matches the given types or empty optional
   */
  Nary<TypeConstructor> withParameterTypes(TypeInstance... paramTypes);

}
