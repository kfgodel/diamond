# Structure of Diamond API
This doc shows an overview of the Diamond API structure


### TypeInstance
- names
  - shortName
  - commonName
  - canonicalName
  - typeName
  - completeName
  - bareName

- declaredPackage
- componentType
- reflectedAs

- kinds
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
  - kindOf
  - subTypeOf
  - superTypeOf
  - assignableFrom
  - assignableTo
  - typeFor
