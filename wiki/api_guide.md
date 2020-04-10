# Structure of Diamond API
This doc shows an overview of the Diamond API structure

## TypeInstance
- [names](names_api_guide.md)
  - [shortName](names_api_guide.md#how-to-get-the-simple-name-of-a-class-typenamesshortname)
  - [commonName](names_api_guide.md#how-to-get-the-name-of-a-class-typenamescommonname)
  - [canonicalName](names_api_guide.md#hot-to-get-the-canonical-name-of-a-class-typenamescanonicalname)
  - [typeName](names_api_guide.md#how-to-get-the-typename-of-a-type-typenamestypename)
  - [completeName](names_api_guide.md#how-to-get-the-annotated-name-of-a-type-typenamescompletename)
  - [bareName](names_api_guide.md#how-to-get-the-minimum-name-of-a-type-typenamesbarename)

- [members](accessing-members_api_guide.md)
  - [all](accessing-members_api_guide.md#how-to-get-all-the-members-of-a-type-typeinstancemembers)
- [fields](accessing-members_api_guide.md#accessing-fields)
  - [all](accessing-members_api_guide.md#how-to-get-all-the-fields-of-a-type-typeinstancefields)
  - [named](accessing-members_api_guide.md#how-to-get-a-field-by-name-typeinstancenamed)
- [methods](accessing-members_api_guide.md#accessing-methods)
  - [all](accessing-members_api_guide.md#how-to-get-all-the-methods-of-a-type-typeinstancemethods)
  - [named](accessing-members_api_guide.md#how-to-get-a-method-by-name-typemethodsnamed)
  - [withSignature](accessing-members_api_guide.md#how-to-get-a-method-by-its-signature-typemethodswithsignature)
  - [withParameterTypes](accessing-members_api_guide.md#how-to-get-a-method-by-its-parameter-types-typemethodswithparametertypes)
- [constructors](accessing-members_api_guide.md#accessing-constructors)
  - [all](accessing-members_api_guide.md#how-to-get-all-the-constructors-of-a-type-typeinstanceconstructors)
  - [niladic](accessing-members_api_guide.md#how-to-get-the-no-arg-constructor-of-a-type-typeconstructorsniladic)
  - [withParameters](accessing-members_api_guide.md#how-to-get-a-constructor-by-its-parameter-types-typeconstructorswithparametertypes)

- categories
- declaredPackage
- componentType
- reflectedAs

- runtime
 - type
 - classes
 - hierarchy
   - superclass
   - interfaces
   - lineage
     - lowestDescendant
     - highestAncestor
     - ancestorOf
     - descendantOf
     - allExtendedTypes
     - allImplementedTypes
     - allRelatedTypes
     - genericArgumentsOf
   - supertypes

- generics
  - bounds
    - upper
    - lower
  - arguments
  - parameters
- hierarchy
  - extendedType
  - implementedTypes
  - lineage
    - lowestDescendant
    - highestAncestor
    - ancestorOf
    - descendantOf
    - allExtendedTypes
    - allImplementedTypes
    - allRelatedTypes
    - genericArgumentsOf
  - supertypes

- newInstance
- get

- declaration
- is
  - partOf
  - subTypeOf
  - superTypeOf
  - assignableFrom
  - assignableTo
  - typeFor

## Type Members
### Common to any member
- [name](members_api_guide.md#how-to-access-the-name-of-a-member-namedname)
- [annotations](members_api_guide.md#how-to-access-the-annotations-declared-on-a-member-annotatedannotations)
- [modifiers](members_api_guide.md#how-to-access-all-the-modifiers-of-a-member-modifiablemodifiers)
- [generics](members_api_guide.md#how-to-access-the-type-parameters-used-on-a-member-declaration-generifiedgenerics)
- [as an executable](members_api_guide.md#executable)
  - [asFunction](members_api_guide.md#how-to-get-a-polymorphic-lambda-from-a-member-executableasfunction)
  - [returnType](members_api_guide.md#how-to-know-the-return-type-of-any-member-used-as-function-executablereturntype)
  - [parameters](members_api_guide.md#how-to-know-the-expected-parameters-of-a-member-used-as-a-function-executableparameters)
  - [parameterTypes](members_api_guide.md#how-to-know-the-expected-parameter-types-of-a-member-used-as-function-parameterizedbehaviorparametertypes)
  - [declaredExceptions](members_api_guide.md#how-to-know-the-declared-exception-of-the-member-exceptionabledeclaredexceptions)
  
### Specific for TypeField
- [name](field-api-guide.md#how-to-get-the-name-of-a-field-typefieldname)

### Specific for TypeMethod
- [name](method-api-guide.md#how-to-get-the-name-of-a-method-typemethodname)

### Specific for TypeConstructor
- [name](constructor-api-guide.md#how-to-get-the-name-of-a-constructor-typeconstructorname)
