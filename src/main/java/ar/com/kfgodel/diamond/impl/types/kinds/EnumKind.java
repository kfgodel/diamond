package ar.com.kfgodel.diamond.impl.types.kinds;

import java.util.function.Predicate;

/**
 * This type represents the kind of enum types
 * Created by kfgodel on 03/02/15.
 */
public class EnumKind extends NativeClassKindSupport {

  @Override
  protected Predicate<Class<?>> getClassPredicate() {
    return Class::isEnum;
  }

  public static EnumKind create() {
    EnumKind kind = new EnumKind();
    return kind;
  }

}
