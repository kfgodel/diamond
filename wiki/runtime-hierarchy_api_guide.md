# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
explore the runtime hierarchy of a type

## Runtime information
Since Java has 2 type systems, one at compilation and one after erasure
has been applied, Diamond represents both for a type.

To access the run time information available for a type use
`TypeInstance#runtime()` 

#### How to get the actual runtime type of a type: `TypeRuntime#type()`
Added by Diamond, this method allows you to get the actual type used
at runtime for annotated types, type variables, wildcards, parameterized types, etc.
```java
TypeRuntime typeRuntime = Diamond.types()
  .from(new ReferenceOf<ParentClass<String, Integer>>() {})
  .runtime();
 
typeRuntime.type()
```
Outputs the raw un-parameterized type for the given type   
```java
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```

#### How to get the actual classes that can be used at runtime to hold a variable of a type: `TypeRuntime#classes()`
This is specially useful for type variables that may have more than one bound,
which at runtime means that can be assigned to more than one class.
```java
// Type variable declared before: A extends ChildClass & Collection

TypeRuntime typeRuntime = Diamond.types()
  .from(new ReferenceOf<A>() {})
  .runtime();
 
typeRuntime.classes()
    .collectToList()
```
Outputs the classes that act as upper bounds and are both assignable from the type   
```java
[class ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass, interface java.util.Collection]
```

### Runtime Hierarchy
The runtime hierarchy of a type doesn't contain generified type arguments
replaced as the compile time hierarchy does. Instead it follows the plain native 
reflection hierarchy adding a couple of methods to improve its usability.

To access it use `TypeRuntime#hierarchy()`  

#### How to know the superclass a type directly extends from: `RuntimeTypeHierarchy#superclass()`
Equivalent to `java.lang.Class#getSuperclass()` 
 
```java
RuntimeTypeHierarchy runtimeHierarchy = Diamond.of(ChildClass.class))
  .runtime().hierarchy();
 
runtimeHierarchy.superclass().get()
```
Outputs the type instance that represents the direct superclass   
```java
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```
Notice that the superclass does not have any type parameter replaced
(in contrast to compile time hierarchy)

#### How to know the interfaces a type directly implements: `RuntimeTypeHierarchy#interfaces()`
Equivalent to `java.lang.Class#getInterfaces()` 
 
```java
RuntimeTypeHierarchy runtimeHierarchy = Diamond.of(ChildClass.class))
  .runtime().hierarchy();
 
runtimeHierarchy.interfaces().collectToList()
```
Outputs the type instance list containing the directly implemented interfaces   
```java
[ChildInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, ChildInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces]
```

#### How to know all the direct super types of a type: `RuntimeTypeHierarchy#supertypes()`
Combination of supreclass and interfaces
 ```java
RuntimeTypeHierarchy runtimeHierarchy = Diamond.of(ChildClass.class))
  .runtime().hierarchy();
 
runtimeHierarchy.supertypes().collectToList()
```
Outputs the list of extended type and implemented types   
```java
[
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ChildInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces
]
```
Notice that the returned supertype follows the closest to type order,
and they have no generics parametrization 

### Runtime Type Lineage
When you want to explore the hierarchy of a type or move through 
levels of it you need to use the `RuntimeTypeHierarchy#lineage()`.

#### How to get all the types extended from a type: `TypeLineage#allExtendedTypes()`
This is equivalent of recursively calling `superclass()` on each 
returned type.

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.allExtendedTypes().collectToList()
```
Outputs the list of all the extended types up to Object   
```java
[
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
GrandParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
Object @ java.lang
]
```
Notice that it also contains the starting type and is ordered closest to that
type first. It's only the extension line, it doesn't include interfaces.
And, in contrast to compile time hierarchy, it doens't include actual 
type arguments

#### How to get all the implemented types from a type: `TypeLineage#allImplementedTypes()`
This is equivalent of recursively calling `interfaces()` on each 
returned type and removing duplicates.

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.allImplementedTypes().collectToList()
```
Outputs the list of all the implemented types following the hierarchy   
```java
[
ChildInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ParentInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
Serializable @ java.io, 
ParentInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
GrandParentInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces
]
```

#### How to get all types involved in a type hierarchy: `TypeLineage#allRelatedTypes()`
This combines extended type with implemented types

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.allRelatedTypes().collectToList()
```
Outputs the list of all the types that are related to the hierarchy of the original type   
```java
[
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ChildInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces,
GrandParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ParentInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
Serializable @ java.io, 
Object @ java.lang, 
GrandParentInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces
]
```
Notice that it includes the starting type and doesn't have parameterized types.
The order of appereance is due to its proxomity to the first type in the hierarchy

#### How to get the direct ancestor of a type in a lineage: `TypeLineage#ancestorOf()`
This only takes extended types into consideration

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.ancestorOf(Diamond.of(ChildClass.class))
  .get()
```
Outputs the directly extended type from `ChildClass`   
```java
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```

### How to get the direct descendant of a type in a lineage: `TypeLineage#descendantOf()`
This only takes extended types into consideration

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.descendantOf(Diamond.of(ParentClass.class))
  .get()
```
Outputs the directly extended type from `ChildClass`   
```java
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```

### How to get the lowest descendant in a hierarchy lineage: `TypeLineage#lowestDescendant()`
This only takes extended types into consideration

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.lowestDescendant()
```
Outputs the starting type as the lowest descendant   
```java
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```

### How to get the highest ancestor in a hierarchy lineage: `TypeLineage#highestAncestor()`
This only takes extended types into consideration

 ```java
TypeLineage runtimeLineage = Diamond.of(ChildClass.class))
  .runtime().hierarchy().lineage();
 
runtimeLineage.highestAncestor()
```
Outputs the highest super type (which is Object usually, except for certain types)   
```java
Object @ java.lang
```

