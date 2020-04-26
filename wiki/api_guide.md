# Structure of Diamond API
This doc shows an overview of the Diamond API structure

### Introduction
There are 4 main concepts on Diamond:
- **TypeInstance:**   
  Represents a single type and unifies all the variations that native reflection
  has for Java types (Class, ParameterizedType, AnnotatedType, TypeVariable, etc)
- **TypeField:**   
  Represents a class field and allows its manipulation. Equivalent to `java.lang.Field`
- **TypeMethod:**  
  Represents a class or interface method and allows its manipulation. Equivalent to `java.lang.Method`
- **TypeConstructor:**  
  Represents a class constructor and allows instantiations. Equivalent to `java.lang.Constructor`

See [how to get Diamond objects](sources_api_guide.md)
for a detailed explanation on how to obtain each type of Diamond abstraction

## TypeInstance
This is probably the starting object to access the rest of the API.  
Usually created from a class: `Diamond.of(String.class)`

- [names](names_api_guide.md)
  Allows access to the different names of a type
  - [shortName](names_api_guide.md#how-to-get-the-simple-name-of-a-class-typenamesshortname)
    The short identifier (or simple name)
  - [commonName](names_api_guide.md#how-to-get-the-name-of-a-class-typenamescommonname)
    The commonly used or referred name in native reflection (or binary name)
  - [canonicalName](names_api_guide.md#hot-to-get-the-canonical-name-of-a-class-typenamescanonicalname)
    The human readable common name
  - [typeName](names_api_guide.md#how-to-get-the-typename-of-a-type-typenamestypename)
    The type name given by the reflection api (unifying other than classes)
  - [completeName](names_api_guide.md#how-to-get-the-annotated-name-of-a-type-typenamescompletename)
    The full name with annotations and decorations
  - [bareName](names_api_guide.md#how-to-get-the-minimum-name-of-a-type-typenamesbarename)
    The bare minimum name stripped of any relationship

- [members](accessing-members_api_guide.md)
  Allows access to the members of a type
  - [all](accessing-members_api_guide.md#how-to-get-all-the-members-of-a-type-typeinstancemembers)
    Combination of methods, fields and constructors in a single getter
- [fields](accessing-members_api_guide.md#accessing-fields)
  Ways of accessing the fields of a type
  - [all](accessing-members_api_guide.md#how-to-get-all-the-fields-of-a-type-typefieldsall)
    Access all the fields (including inherited)
  - [named](accessing-members_api_guide.md#how-to-get-a-field-by-name-typefieldsnamed)
    Filter fields by name
- [methods](accessing-members_api_guide.md#accessing-methods)
  Ways of accessing the methods of a type    
  - [all](accessing-members_api_guide.md#how-to-get-all-the-methods-of-a-type-typemethodsall)
    Access all the methods (including inherited and overriden)
  - [named](accessing-members_api_guide.md#how-to-get-a-method-by-name-typemethodsnamed)
    Filter methods by name
  - [withSignature](accessing-members_api_guide.md#how-to-get-a-method-by-its-signature-typemethodswithsignature)
    Filter methods by their full signature
  - [withParameterTypes](accessing-members_api_guide.md#how-to-get-a-method-by-its-parameter-types-typemethodswithparametertypes)
    Filter methods by their type parameters
- [constructors](accessing-members_api_guide.md#accessing-constructors)
    Ways to access the constructors of a type
  - [all](accessing-members_api_guide.md#how-to-get-all-the-constructors-of-a-type-typeconstructorsall)
    Access all the consctructors of a type
  - [niladic](accessing-members_api_guide.md#how-to-get-the-no-arg-constructor-of-a-type-typeconstructorsniladic)
    Access the no-arg consturctor
  - [withParameters](accessing-members_api_guide.md#how-to-get-a-constructor-by-its-parameter-types-typeconstructorswithparametertypes)
    Filter the constructors by their parameter types

- [newInstance](instantiation_api_guide.md#how-to-quickly-create-an-instance-of-a-type-using-the-niladic-constructor-typeinstancenewinstance)
  Creates a new instance of a type 
- [get](instantiation_api_guide.md#how-to-use-a-type-as-a-supplier-typeinstanceget)
  Alias to create that allows a type being used as a Supplier lambda

- [is](type-tests_api_guide.md#is-questions)
  Allows access to several questions that can be made to a type
  - [instance](type-tests_api_guide.md#how-to-test-if-an-instance-is-from-a-type-typetestsinstance)
    Indicates if an object is instance of the type
  - [a](type-tests_api_guide.md#how-to-test-if-a-type-represents-an-interface-typetestsa)
    Indicates if a type belongs to a category 
  - [subTypeOf](type-tests_api_guide.md#how-to-test-if-a-type-is-a-compile-time-subtype-typetestssubtypeof)
    Indicates if a type is sub-type from another  
  - [superTypeOf](type-tests_api_guide.md#how-to-test-if-a-type-is-a-compile-time-supertype-typetestssupertypeof)
    Indicates if a type is super-type from another  
  - [assignableFrom](type-tests_api_guide.md#how-to-test-if-a-type-is-a-runtime-subtype-typetestsassignablefrom)
    Indicates if a type can accept instances of another type   
  - [assignableTo](type-tests_api_guide.md#how-to-test-if-a-type-is-a-runtime-supertype-typetestsassignableto)
    Indicates if instances of a type can be assigned to another type   

- [declaredPackage](type-info_api_guide.md#how-to-know-the-package-where-a-type-is-declared-typeinstancedeclaredpackage)
  Get access to the package a type is declared into   
- [declaration](type-info_api_guide.md#how-to-get-a-string-representation-of-the-type-typeinstancedeclaration)
  A string representation for the type   
- [categories](type-info_api_guide.md#how-to-know-the-categories-a-type-belongs-to-typeinstancecategories)
  The set of categories the type belongs to    
- [componentType](type-info_api_guide.md#how-to-know-the-component-type-of-an-array-type-typeinstancecomponenttype)
  The type of component an array type has     
- [reflectedAs](type-info_api_guide.md#how-to-get-the-native-reflection-instance-from-a-type-typeinstancereflectedas)
  The native reflection instance     

- [generic](generics_api_guide.md#type-generics)
  Gives information about the generics used on the type     
  - [bounds](generics_api_guide.md#the-bounds-of-a-type-can-be-accessed-with-typegenericsbounds)
    Allows access to the bounds of the type (if any)     
    - [upper](generics_api_guide.md#how-to-get-the-upper-bounds-of-a-type-typeboundsupper)
      The upper bounds of the type (if bound)     
    - [lower](generics_api_guide.md#how-to-get-the-lower-bounds-of-a-type-typeboundsupper)
      The lower bounds of the type (if bound)     
  - [arguments](generics_api_guide.md#how-to-get-the-type-arguments-of-a-parameterized-type-typegenericsarguments)
    The actual type arguments used to parameterize the type (if any)     
  - [parameters](generics_api_guide.md#how-to-get-the-type-parameters-of-a-parameterizable-type-typegenericsparameters)
    The declared type parameters used to parameterize this type (if any)     
  
- [hierarchy (compile time)](compile-time-hierarchy_api_guide.md#compile-time-hierarchy)
  Allows access to the compile time hierarchy of the type
  - [extendedType](compile-time-hierarchy_api_guide.md#how-to-know-the-type-a-type-directly-extends-from-compiletimehierarchyextendedtype)
    The directly super type extended by the type at compile time 
  - [implementedTypes](compile-time-hierarchy_api_guide.md#how-to-know-the-different-types-a-type-directly-implements-compiletimehierarchyimplementedtypes)
    The directly super types implemented by the type at compile time 
  - [supertypes](compile-time-hierarchy_api_guide.md#how-to-know-all-the-direct-super-types-of-a-type-compiletimehierarchysupertypes)
    Get all the directly supertypes. Combination of extended type and extended types. 
  - [lineage](compile-time-hierarchy_api_guide.md#type-lineage)
    Allows access to the different levels of the hierarchy 
    - [allExtendedTypes](compile-time-hierarchy_api_guide.md#how-to-get-all-the-types-extended-from-a-type-typelineageallextendedtypes)
      All the extended types in the hierarchy (at compile time) 
    - [allImplementedTypes](compile-time-hierarchy_api_guide.md#how-to-get-all-the-implemented-types-from-a-type-typelineageallimplementedtypes)
      All implemented types in the hierarchy (at compile time)
    - [allRelatedTypes](compile-time-hierarchy_api_guide.md#how-to-get-all-types-involved-in-a-type-hierarchy-typelineageallrelatedtypes)
      All the types in the hierarchy (at compile time)
    - [genericArgumentsOf](compile-time-hierarchy_api_guide.md#how-to-get-the-actual-type-arguments-of-a-super-type-in-the-hierarchy-typelineagegenericargumentsof)
      Gets the actual types arguments used to parameterize any type in the hierarchy
    - [ancestorOf](compile-time-hierarchy_api_guide.md#how-to-get-the-direct-ancestor-of-a-type-in-a-lineage-typelineageancestorof)
      Gets the direct ancestor of a type in the extension line of the hierarchy
    - [descendantOf](compile-time-hierarchy_api_guide.md#how-to-get-the-direct-descendant-of-a-type-in-a-lineage-typelineagedescendantof)
      Gets the direct descendant of a type in the extension line of the hierarchy
    - [lowestDescendant](compile-time-hierarchy_api_guide.md#how-to-get-the-lowest-descendant-in-a-hierarchy-lineage-typelineagelowestdescendant)
      Gets the lowest descendant in the extension line of the hierarchy
    - [highestAncestor](compile-time-hierarchy_api_guide.md#how-to-get-the-highest-ancestor-in-a-hierarchy-lineage-typelineagehighestancestor)
      Gets the highest ancestor in the extension line of the hierarchy
  
- [runtime](runtime-hierarchy_api_guide.md#runtime-information)
  Allows access to the runtime information of the type
  - [type](runtime-hierarchy_api_guide.md#how-to-get-the-actual-runtime-type-of-a-type-typeruntimetype)
    Get the actual type used in runtime to represent a type (usually a class type)
  - [classes](runtime-hierarchy_api_guide.md#how-to-get-the-actual-classes-that-can-be-used-at-runtime-to-hold-a-variable-of-a-type-typeruntimeclasses)
    Get the classes that at run time are used to assign a type (multiple if multi-bound variable)
  - [hierarchy](runtime-hierarchy_api_guide.md#runtime-hierarchy)
   Allows access to the run time hierarchy of the type
    - [superclass](runtime-hierarchy_api_guide.md#how-to-know-the-superclass-a-type-directly-extends-from-runtimetypehierarchysuperclass)
      The directly super type extended by the type at run time
    - [interfaces](runtime-hierarchy_api_guide.md#how-to-know-the-interfaces-a-type-directly-implements-runtimetypehierarchyinterfaces)
      The directly super types implemented by the type at run time
    - [supertypes](runtime-hierarchy_api_guide.md#how-to-know-all-the-direct-super-types-of-a-type-runtimetypehierarchysupertypes)
      Get all the directly supertypes. Combination of extended type and extended types.
    - [lineage](runtime-hierarchy_api_guide.md#runtime-type-lineage)
      Allows access to the different levels of the hierarchy (at runtime)
      - [allExtendedTypes](runtime-hierarchy_api_guide.md#how-to-get-all-the-types-extended-from-a-type-typelineageallextendedtypes)
        All the extended types in the hierarchy (at run time)
      - [allImplementedTypes](runtime-hierarchy_api_guide.md#how-to-get-all-the-implemented-types-from-a-type-typelineageallimplementedtypes)
        All implemented types in the hierarchy (at run time)
      - [allRelatedTypes](runtime-hierarchy_api_guide.md#how-to-get-all-types-involved-in-a-type-hierarchy-typelineageallrelatedtypes)
        All the types in the hierarchy (at run time)
      - [ancestorOf](runtime-hierarchy_api_guide.md#how-to-get-the-direct-ancestor-of-a-type-in-a-lineage-typelineageancestorof)
        Gets the direct ancestor of a type in the extension line of the hierarchy
      - [descendantOf](runtime-hierarchy_api_guide.md#how-to-get-the-direct-descendant-of-a-type-in-a-lineage-typelineagedescendantof)
        Gets the direct descendant of a type in the extension line of the hierarchy
      - [lowestDescendant](runtime-hierarchy_api_guide.md#how-to-get-the-lowest-descendant-in-a-hierarchy-lineage-typelineagelowestdescendant)
        Gets the lowest descendant in the extension line of the hierarchy
      - [highestAncestor](runtime-hierarchy_api_guide.md#how-to-get-the-highest-ancestor-in-a-hierarchy-lineage-typelineagehighestancestor)
        Gets the highest ancestor in the extension line of the hierarchy

## Type Members
If you want to manipulate objects or execute code using reflection you will, 
usually, access some members of a type (fields, methods or constructors)

### Common to any member
- [name](members_api_guide.md#how-to-access-the-name-of-a-member-namedname)
  Name of the field, method or constructor depending on the member
- [annotations](members_api_guide.md#how-to-access-the-annotations-declared-on-a-member-annotatedannotations)
  The attached annotations accompanying the member (if any)
- [modifiers](members_api_guide.md#how-to-access-all-the-modifiers-of-a-member-modifiablemodifiers)
  Any modifiers applied to the member (Diamond reifies modifiers as objects)
- [generics](members_api_guide.md#how-to-access-the-type-parameters-used-on-a-member-declaration-generifiedgeneric)
  Allows access to generic info like parameters
- [as an executable](members_api_guide.md#executable)
  Allows treating the member as an executable piece of code
  - [asLambda](members_api_guide.md#how-to-get-a-polymorphic-lambda-from-a-member-executableasLambda)
    Gets a polymorphical lambda that invoked (semantics depends on the member type)
  - [returnType](members_api_guide.md#how-to-know-the-return-type-of-any-member-used-as-function-executablereturntype)
    The type of returned result the member returns
  - [parameters](members_api_guide.md#how-to-know-the-expected-parameters-of-a-member-used-as-a-function-executableparameters)
    The parameters (with names) the member expects as parameters
  - [parameterTypes](members_api_guide.md#how-to-know-the-expected-parameter-types-of-a-member-used-as-function-parameterizedbehaviorparametertypes)
    Only the types of parameters a member expects
  - [declaredExceptions](members_api_guide.md#how-to-know-the-declared-exception-of-the-member-exceptionabledeclaredexceptions)
    Any exception the member declares
  
### Specific for TypeField
- [type](field-api-guide.md#how-to-know-the-type-of-a-field-typefieldtype)
  The type the field was declared with
- [getValueFrom](field-api-guide.md#how-to-get-the-value-of-a-field-from-an-object-typefieldgetvaluefrom)
  Gets the value of the field from an object or static field
- [setValueOn](field-api-guide.md#how-to-set-the-value-of-a-field-on-an-object-typefieldsetvalueon)
  Sets the value of the field into an object or static field
- [as a lambda](field-api-guide.md#field-used-as-lambda)
  Allows using the field as a polymorphic lambda for setting and getting a value
- [bindTo](field-api-guide.md#how-to-bind-an-instance-to-a-type-field-typefieldbindto)
  Binds the field to an instance so it can be omitted in future calls
- [nativeType](field-api-guide.md#how-to-get-the-native-reflection-instance-for-a-field-typefieldnativetype)
  Returns the native reflection instance that represents the field

### Specific for TypeMethod
- [defaultValue](method-api-guide.md#how-to-get-the-default-value-of-a-method-typemethoddefaultvalue)
  The default value this method has (meaningful for annotation methods)
- [invokeOn](method-api-guide.md#how-to-invoke-a-bound-method-on-an-instance-boundmethodinvoke)
  Invokes the method on a receiver
- [as a lambda](method-api-guide.md#method-used-as-lambda)
  Allows using the method as a polymorphic lambda whose semantics will depend on the method
- [bindTo](method-api-guide.md#how-to-bind-an-instance-to-a-type-method-typemethodbindto)
  Binds the method to an instance so it can be omitted as receiver in future calls
- [withArguments](method-api-guide.md#how-to-bind-arguments-to-the-parameters-of-a-method-typemethodwitharguments)
  Binds the method to a set of arguments so they can be omitted in future calls
- [nativeType](method-api-guide.md#how-to-get-the-native-reflection-instance-for-a-method-typemethodnativetype)
  Returns the native reflection instance that represents the method

### Specific for TypeConstructor
- [invoke](constructor-api-guide.md#how-to-invoke-a-constructor-typeconstructorinvoke)
  Invokes the constructor with no arguments
- [as lambda](constructor-api-guide.md#constructor-used-as-lambda)
  Allows treating the constructor as a polymorphic lambda to get new instances
- [withArguments](constructor-api-guide.md#how-to-bind-arguments-to-the-parameters-of-a-constructor-typeconstructorwitharguments)
  Binds a set of arguments into the constructor so they can be omitted in future calls
- [nativeType](constructor-api-guide.md#how-to-get-the-native-reflection-instance-for-a-constructor-typeconstructornativetype)
  Returns the native reflection instance that represents the constructor