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

- generics
  - bounds
    - upper
    - lower
  - arguments
  - parameters
  - runtimeType
- inheritance
  - extendedType
  - superclass
  - implementedTypes
  - interfaces
  - typeLineage
    - lowestDescendant
    - highestAncestor
    - allMembers
    - ancestorOf
    - descendantOf
    - inheritedInterfaces
    - allRelatedTypes
    - genericArgumentsOf
  - supertypes
  - isSubTypeOf
  - isSubTypeOfNative
  - isSuperTypeOf
  - isSuperTypeOfNative

- newInstance
- get

- declaration
- is
- isAssignableFrom
- isAssignableTo
- isTypeFor
- kinds

- reflectionType
- runtimeClasses 
