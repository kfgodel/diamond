package ar.com.kfgodel.diamond.api.types.categories;

import ar.com.kfgodel.diamond.impl.types.categories.AnnotationCategory;
import ar.com.kfgodel.diamond.impl.types.categories.AnonymousCategory;
import ar.com.kfgodel.diamond.impl.types.categories.ArrayCategory;
import ar.com.kfgodel.diamond.impl.types.categories.BooleanCategory;
import ar.com.kfgodel.diamond.impl.types.categories.ClassCategory;
import ar.com.kfgodel.diamond.impl.types.categories.ContainerCategory;
import ar.com.kfgodel.diamond.impl.types.categories.EnumCategory;
import ar.com.kfgodel.diamond.impl.types.categories.InterfaceCategory;
import ar.com.kfgodel.diamond.impl.types.categories.NumericCategory;
import ar.com.kfgodel.diamond.impl.types.categories.PrimitiveCategory;
import ar.com.kfgodel.diamond.impl.types.categories.ReferenceCategory;
import ar.com.kfgodel.diamond.impl.types.categories.TextCategory;
import ar.com.kfgodel.diamond.impl.types.categories.ValueCategory;
import ar.com.kfgodel.nary.api.Nary;

/**
 * This type serves as single access point to all pre-defined categories
 *
 * Created by kfgodel on 03/02/15.
 */
public class Categories {

  /**
   * Types that represent objects (non-primitive types)
   */
  public static ReferenceCategory REFERENCE = ReferenceCategory.create();
  /**
   * Primitive types as numbers
   */
  public static PrimitiveCategory PRIMITIVE = PrimitiveCategory.create();
  /**
   * Array types
   */
  public static ArrayCategory ARRAY = ArrayCategory.create();
  /**
   * Numeric, boolean and text types
   */
  public static ValueCategory VALUE = ValueCategory.create();
  /**
   * boolean and Boolean types
   */
  public static BooleanCategory BOOLEAN = BooleanCategory.create();
  /**
   * Numeric primitive and object types
   */
  public static NumericCategory NUMERIC = NumericCategory.create();
  /**
   * Text types (CharSequence)
   */
  public static TextCategory TEXT = TextCategory.create();
  /**
   * Types the represent enums
   */
  public static EnumCategory ENUM = EnumCategory.create();
  /**
   * Types that are interfaces
   */
  public static InterfaceCategory INTERFACE = InterfaceCategory.create();
  /**
   * Types that are classes (not interfaces)
   */
  public static ClassCategory CLASS = ClassCategory.create();
  /**
   * Types that represent annotations
   */
  public static AnnotationCategory ANNOTATION = AnnotationCategory.create();
  /**
   * Types that don't own a name (anonymous classes)
   */
  public static AnonymousCategory ANONYMOUS = AnonymousCategory.create();
  /**
   * Type that are collections or maps
   */
  public static ContainerCategory CONTAINER = ContainerCategory.create();

  private static final TypeCategory[] values = new TypeCategory[]{
    REFERENCE,
    PRIMITIVE,
    ARRAY,
    VALUE,
    BOOLEAN,
    NUMERIC,
    TEXT,
    ENUM,
    INTERFACE,
    CLASS,
    ANNOTATION,
    ANONYMOUS,
    CONTAINER
  };

  public static Nary<TypeCategory> values() {
    return Nary.from(values);
  }

}
