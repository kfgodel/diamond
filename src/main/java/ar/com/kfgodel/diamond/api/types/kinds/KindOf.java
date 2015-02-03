package ar.com.kfgodel.diamond.api.types.kinds;

import ar.com.kfgodel.diamond.impl.types.kinds.*;

/**
 * This type serves as single access point to all standard kinds
 * Created by kfgodel on 03/02/15.
 */
public class KindOf {

    /**
     * Types that represent objects (non-primitive types)
     */
    public static ReferenceKind REFERENCE = ReferenceKind.create();
    /**
     * Primitive types as numbers
     */
    public static PrimitiveKind PRIMITIVE = PrimitiveKind.create();
    /**
     * Array types 
     */
    public static ArrayKind ARRAY = ArrayKind.create();
    /**
     * Numeric, boolean and text types
     */
    public static ValueKind VALUE = ValueKind.create();
    /**
     * boolean and Boolean types
     */
    public static BooleanKind BOOLEAN = BooleanKind.create();
    /**
     * Numeric primitive and object types 
     */
    public static NumericKind NUMERIC = NumericKind.create();
    /**
     * Text types (CharSequence)
     */
    public static TextKind TEXT = TextKind.create();
    /**
     * Types the represent enums
     */
    public static EnumKind ENUM = EnumKind.create();
    /**
     * Types that are interfaces
     */
    public static InterfaceKind INTERFACE = InterfaceKind.create();
    /**
     * Types that are classes (not interfaces)
     */
    public static ClassKind CLASS = ClassKind.create();
    /**
     * Types that represent annotations
     */
    public static AnnotationKind ANNOTATION = AnnotationKind.create();
    /**
     * Types that don't own a name (anonymous classes)
     */
    public static AnonymousKind ANONYMOUS = AnonymousKind.create();
    /**
     * Type that are collections or maps
     */
    public static ContainerKind CONTAINER = ContainerKind.create();

    private static final Kind[] values = new Kind[]{REFERENCE,
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
    CONTAINER};

    public static Kind[] values(){
        return values;
    }

}
