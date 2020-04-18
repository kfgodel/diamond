# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
explore the compile time hierarchy of a type

## Compile time hierarchy
Since Java has 2 type systems, one at compilation and one after erasure
has been applied removing some generics info, Diamond represents both for a type.

To access the information about a type hierarchy at compile time
use `TypeInstance#hierarchy()`

#### How to know the type a type directly extends from: `CompileTimeHierarchy#extendedType()`
Loosely equivalent to `java.lang.Class#getAnnotatedSuperclass()` 
 
```java
CompileTimeHierarchy compileHierarchy = Diamond.of(ChildClass.class))
  .hierarchy();
 
compileHierarchy.extendedType().get()
```
Outputs the type instance that represents the extended type at compile time   
```java
ParentClass<C, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```
Notice that the extended type has its actual type arguments as defined
by the type we are introspecting (in contrast to runtime hierarchy)

#### How to know the different types a type directly implements: `CompileTimeHierarchy#implementedTypes()`
Loosely equivalent to `java.lang.Class#getAnnotatedInterfaces()` 
 
```java
CompileTimeHierarchy compileHierarchy = Diamond.of(ChildClass.class))
  .hierarchy();
 
compileHierarchy.implementedTypes().collectToList()
```
Outputs the type instance list containing t he directly implemented types   
```java
[ChildInterface1<Integer> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, ChildInterface2<String> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces]
```

#### How to know all the direct super types of a type: `CompileTimeHierarchy#supertypes()`
Combination of extended and implemented types
 ```java
CompileTimeHierarchy compileHierarchy = Diamond.of(ChildClass.class))
  .hierarchy();
 
compileHierarchy.supertypes().collectToList()
```
Outputs the list of extended type and implemented types   
```java
[ 
ParentClass<C, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ChildInterface1<Integer> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface2<String> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces 
]
```
Notice that the returned supertype follows the closest to type order  

### Type Lineage
When you want to explore the hierarchy of a type or move through 
levels of it while retaining all the compile type information available
you need to use the `CompileTimeHierarchy#lineage()`. 
It deduces the actual values for the generic type parameters on the hierarchy.

#### How to get all the types extended from a type: `TypeLineage#allExtendedTypes()`
This is equivalent of recursively calling `extendedType()` on each 
returned type.

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.allExtendedTypes().collectToList()
```
Outputs the list of all the extended types up to Object   
```java
[
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentClass<C, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
GrandParentClass<Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
Object @ java.lang
]
```
Notice that it also contains the starting type and is ordered closest to that
type first. It's only the extension line, it doesn't include implemented types.
And, in contrast to runtime hierarchy, it includes actual type arguments

#### How to get all the implemented types from a type: `TypeLineage#allImplementedTypes()`
This is equivalent of recursively calling `implementedTypes()` on each 
returned type and removing duplicates.

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.allImplementedTypes().collectToList()
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
Notice that it does not replaces type arguments for implemnted types.
This is a known bug PRJ-535 that will be fixed in the near future.

#### How to get all types involved in a type hierarchy: `TypeLineage#allRelatedTypes()`
This combines extended type with implemented types

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.allRelatedTypes().collectToList()
```
Outputs the list of all the types that are related to the hierarchy of the original type   
```java
[
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentClass<C, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ChildInterface1<Integer> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface2<String> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ChildInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
GrandParentClass<Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
GrandParentClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage, 
ParentInterface2<Integer, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ParentInterface2 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ParentInterface1<Integer, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
ParentInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces, 
Serializable @ java.io, 
Object @ java.lang, 
GrandParentInterface1<Integer> @ ar.com.kfgodel.diamond.unit.testobjects.interfaces,
GrandParentInterface1 @ ar.com.kfgodel.diamond.unit.testobjects.interfaces
]
```
Notice that it includes the starting type as well as type argument replacements
for its super types, and it also includes the non parameterized types as those
belong to the compile time hierarchy too

#### How to get the actual type arguments of a super type in the hierarchy: `TypeLineage#genericArgumentsOf()`
Added by Diamond this allows you to get the actual parameterized arguments
of a generic type in any position of the hierarchy

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.genericArgumentsOf(Diamond.of(GrandParentInterface1.class))
  .collectToList()
```
Outputs the list arguments used to parameterize the interface from the child type   
```java
[Integer @ java.lang]
```
Notice that this list is calculated by sequentially replacing type variables
with their values from the starting type.   
Becasue some starting types may not define all their type parameters
the supertypes may get a type variable as actual value

#### How to get the direct ancestor of a type in a lineage: `TypeLineage#ancestorOf()`
This only takes extended types into consideration

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.ancestorOf(Diamond.of(ChildClass.class))
  .get()
```
Outputs the directly extended type from `ChildClass`   
```java
ParentClass<C, Integer> @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```

### How to get the direct descendant of a type in a lineage: `TypeLineage#descendantOf()`
This only takes extended types into consideration

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
TypeInstance firstParentType = compileLineage.allExtendedTypes()
  .skip(1)
.findFirst().get();

compileLineage.descendantOf(firstParentType)
  .get()
```
Outputs the directly extended type from `ChildClass`   
```java
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```
Notice that the exact type needs to be passed to the the descendant
(including any parametrization)

### How to get the lowest descendant in a hierarchy lineage: `TypeLineage#lowestDescendant()`
This only takes extended types into consideration

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.lowestDescendant()
```
Outputs the starting type as the lowest descendant   
```java
ChildClass @ ar.com.kfgodel.diamond.unit.testobjects.lineage
```

### How to get the highest ancestor in a hierarchy lineage: `TypeLineage#highestAncestor()`
This only takes extended types into consideration

 ```java
TypeLineage compileLineage = Diamond.of(ChildClass.class))
  .hierarchy().lineage();
 
compileLineage.highestAncestor()
```
Outputs the highest super type (which is Object usually, except for certain types)   
```java
Object @ java.lang
```

