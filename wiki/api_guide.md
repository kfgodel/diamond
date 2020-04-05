# Structure of Diamond API
This doc shows an overview of the Diamond API structure

### TypeInstance
- [names](names_api_guide.md)
  - [shortName](names_api_guide.md#how-to-get-the-simple-name-of-a-class-typenamesshortname)
  - [commonName](names_api_guide.md#how-to-get-the-name-of-a-class-typenamescommonname)
  - [canonicalName](names_api_guide.md#hot-to-get-the-canonical-name-of-a-class-typenamescanonicalname)
  - [typeName](names_api_guide.md#how-to-get-the-typename-of-a-type-typenamestypename)
  - [completeName](names_api_guide.md#how-to-get-the-annotated-name-of-a-type-typenamescompletename)
  - [bareName](names_api_guide.md#how-to-get-the-minimum-name-of-a-type-typenamesbarename)

- categories
- declaredPackage
- componentType
- reflectedAs

- members
- constructors
  - all
  - niladic
  - withParameters
- fields
  - all
  - named
- methods
  - all
  - named
  - withSignature
  - withNativeSignature
  - withParameters

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

### TypeField
- [name](names_api_guide.md#how-to-get-the-name-of-a-field-typefieldname)

### TypeMethod
- [name](names_api_guide.md#how-to-get-the-name-of-a-method-typemethodname)

### TypeConstructor
- [name](names_api_guide.md#how-to-get-the-name-of-a-constructor-typeconstructorname)
