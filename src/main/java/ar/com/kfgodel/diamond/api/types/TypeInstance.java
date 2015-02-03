package ar.com.kfgodel.diamond.api.types;

import ar.com.kfgodel.diamond.api.DiamondReflection;
import ar.com.kfgodel.diamond.api.annotations.Annotated;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructors;
import ar.com.kfgodel.diamond.api.declaration.Declarable;
import ar.com.kfgodel.diamond.api.equals.TokenIdentifiable;
import ar.com.kfgodel.diamond.api.fields.TypeFields;
import ar.com.kfgodel.diamond.api.generics.Generified;
import ar.com.kfgodel.diamond.api.members.TypeMember;
import ar.com.kfgodel.diamond.api.methods.TypeMethods;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.generics.TypeGenerics;
import ar.com.kfgodel.diamond.api.types.inheritance.TypeInheritance;
import ar.com.kfgodel.diamond.api.types.kinds.Kind;
import ar.com.kfgodel.diamond.api.types.names.TypeNames;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.nary.api.Nary;

import java.util.function.Supplier;

/**
 * This type represents a java type.<br>
 * Instances of this interface represent one of the possible types in Java language including type variables and wildcards.<br>
 *
 * Created by kfgodel on 20/09/14.
 */
public interface TypeInstance extends Named, Annotated, Supplier<Object>, Declarable, Generified, DiamondReflection, TokenIdentifiable {

    /**
     * Returns the accessor object for this type names (in all their varieties)
     * @return The source of type names for this instance
     */
    TypeNames names();

    /**
     * The name that identifies this type with its annotations and generics information.<br> This name could be used to declare
     * this exact type in source code including actual type arguments
     * @return The name as a full type declaration (This is equivalent to the source code for this type declaration)
     */
    String declaration();

    /**
     * @return The information about this type generification.<br>
     *     TypeGenerics holds the relationships between this type and its parameters, arguments and bounds
     */
    TypeGenerics generics();

    /**
     * @return The component type of this container type.<br>
     * Component type is only present on arrays that reify their component types
     */
    Nary<TypeInstance> componentType();

    /**
     * @return The information about this type inheritance.<br>
     *     TypeInheritance holds the relationships between this type and its parent types
     */
    TypeInheritance inheritance();

    /**
     * @return The information about this type methods.<br>
     *     TypeMethods holds the relationship between this type and the methods that instances of this type understand
     */
    TypeMethods methods();

    /**
     * @return The information about this type fields.<br>
     *     TypeFields holds the relationship between this type and the state fields that instances of this type have
     */
    TypeFields fields();

    /**
     * @return The information about this type constructors<br>
     *     TypeConstructors holds the relationship between this type and constructors to create instances of this type
     */
    TypeConstructors constructors();

    /**
     * Creates a new instance of this type using the niladic constructor.<br>
     *     If this type cannot be instantiated, or doesn't have a niladic constructor, and exception is thrown
     * @return The newly created instance
     */
    Object newInstance();

    /**
     * Indicates if the given object is assignable to this type.<br>
     *     Or this type is part if the object's type hierarchy. This method follows the same semantics as Class.isInstance().<br>
     * <br>
     * For upper bounded types with more than one bound this is true if any of the upper bounds is true.
     * @param anObject The object to test
     * @return true if the given object can be casted to this type without an exception, false if object is null or otherwise
     */
    boolean isTypeFor(Object anObject);

    /**
     * @return Sames as calling newInstance()
     */
    @Override
    Object get();

    /**
     * @return The package declared for this type, or empty if this type has none
     */
    Nary<TypePackage> declaredPackage();

    /**
     * @return The nary of all the members (methods, fields and constructors)
     */
    Nary<TypeMember> members();

    /**
     * @return The nary of classes that represent this type in runtime (if any).<br>
     *     More than one can be present if this is an upper bounded type
     */
    Nary<Class<?>> nativeTypes();

    /**
     * Answers if this type belongs to the given kind. Kinds are groups of types
     * usually defined by the language designers
     * @param testedKind The kind to test this type on
     * @return true if this type can be considered of the given kind
     */
    boolean is(Kind testedKind);

    /**
     * The set of kinds this type belongs to.<br> This set is usually defined on the language
     * specification
     * @return The kinds for this type
     */
    Nary<Kind> kinds();
}
