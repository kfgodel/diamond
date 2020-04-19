# Diamond sources API
This doc shows an overview of how to get Diamond abstractions for every concept
that is supported

## Entry point
All the abstractions Diamond offers are accessible through the class `Diamond`.
There's a static method for each type of abstraction and, due to frequency of
use, types are easily accessible through `Diamond.of()`.

- [of](sources/type-sources_api_guide.md#how-to-get-the-diamond-representation-of-a-type-diamondof)
  Gets the `TypeInstance` equivalent of the given `Type` instance
- [ofNative](sources/type-sources_api_guide.md#how-to-get-several-diamond-representation-from-types-at-once-diamondofnative)
  Gets an array of TypeInstances for the given `Type[]`
- [types](sources/type-sources_api_guide.md#types-accessor)
  Allows access to different sources to get Diamond abstractions over types
  - [from(Object)](sources/type-sources_api_guide.md#how-to-get-a-diamond-type-from-any-native-reflection-alternative-typesourcesfromobject)
    Gets a `TypeInstance` from any of the native variants to represent a Java type
  - [from(ReferenceOf)](sources/type-sources_api_guide.md#how-to-get-the-diamond-representation-of-a-generic-parameterized-type-typesourcesfromreferenceof)
    Gets a type from a reference to a type. This is useful for parameterized types
  - [from(Object[])](sources/type-sources_api_guide.md#how-to-get-multiple-type-representations-at-once-typesourcesfromobject)
    Gets and array of types from their native reflection counterparts
  - [named](sources/type-sources_api_guide.md#how-to-get-a-type-by-its-name-typesourcesnamed)
    Gets a type from its name, loading into the VM if needed
- [methods](sources/method-sources_api_guide.md#methods-accessor)
  Allows access to different sources to get Diamond abstractions over methods
  - [in](sources/method-sources_api_guide.md#how-to-get-the-diamond-methods-of-a-class-methodsourcesin)
    Allows access to the methods present in a native reflection `Class`
  - [from](sources/method-sources_api_guide.md#how-to-get-the-diamond-representation-of-a-native-method-methodsourcesfrom)
    Gets the Diamond version of a native reflection `Method`
- [fields](sources/field-sources_api_guide.md#fields-accessor)
  Allows access to different sources to get Diamond abstractions over fields
  - [in](sources/field-sources_api_guide.md#how-to-get-the-diamond-fields-of-a-class-fieldsourcesin)
    Allows access to the fields present in a native reflection `Class`
  - [from](sources/field-sources_api_guide.md#how-to-get-the-diamond-representation-of-a-native-field-fieldsourcesfrom)
    Gets the Diamond version of a native reflection `Field`
- [constructors](sources/constructor-sources_api_guide.md#constructors-accessor)
  Allows access to different sources to get Diamond abstractions over constructors
  - [in](sources/constructor-sources_api_guide.md#how-to-get-the-diamond-constructors-of-a-class-constructorsourcesin)
    Allows access to the constructors present in a native reflection `Class`
  - [from](sources/constructor-sources_api_guide.md#how-to-get-the-diamond-representation-of-a-native-constructor-constructorsourcesfrom)
    Gets the Diamond version of a native reflection `Constructor`
- [modifiers](sources/modifier-sources_api_guide.md#modifiers-accessor)
  Allows access to different sources to get Diamond abstractions over member modifiers
  - [all](sources/modifier-sources_api_guide.md#how-to-get-all-the-diamond-reified-modifiers-modifiersourcesall)
    Gets all the Diamond reified modifiers
  - [from](sources/modifier-sources_api_guide.md#how-to-get-the-modifiers-of-a-native-reflection-member-modifiersourcesfrom)
    Gets the modifiers present in a member
  - [fromMember](sources/modifier-sources_api_guide.md#how-to-get-the-modifiers-from-a-member-modifier-bitmap-modifiersourcesfrommember)
    Gets the modifiers analyzing a member bitmap (fields, methods, or constructors)
  - [fromParameter](sources/modifier-sources_api_guide.md#how-to-get-the-modifiers-from-a-parameter-modifier-bitmap-modifiersourcesfromparameter)
    Gets the modifiers analyzing a parameter bitmap
- [parameters](sources/parameter-sources_api_guide.md#parameters-accessor)
  Allows access to different sources to get Diamond abstractions over executable parameters
  - [from(Parameter)](sources/parameter-sources_api_guide.md#how-to-get-the-diamond-representation-of-a-native-parameter-parametersourcesfrom)
    Gets a diamond parameter from a native `Parameter`
  - [from(Parameter[])](sources/parameter-sources_api_guide.md#how-to-get-the-diamond-representation-of-several-parameters-parametersourcesfromparameter)
    Gets an array of parameters from their native counterparts
- packages
  Allows access to different sources to get Diamond abstractions over packages
  - from
    Gets the Diamond representation from a native reflection `Package`
  - named
    Gets a package from its name (it must exist in the classpath)
- metaObjects
  Allows access to different sources to get Diamond abstractions over instances
  - from
  Gets a MetaObject from an instance that allows operations on the object
- lambdas
  Allows you to get a single Diamond representation from different types of native lambdas 
  - fromRunnable
    Gets a polymorphic lambda version of a `Runnable` 
  - fromConsumer
    Gets a polymorphic lambda version of a `Consumer`
  - fromBiConsumer
    Gets a polymorphic lambda version of a `BiConsumer`
  - fromSupplier
    Gets a polymorphic lambda version of a `Supplier`
  - fromFunction
    Gets a polymorphic lambda version of a `Function`
  - fromBiFunction
    Gets a polymorphic lambda version of a `BiFunction`
  - fromPredicate
    Gets a polymorphic lambda version of a `Predicate`
  - fromInvokable
    Gets a polymorphic lambda version of a `Invokable`
