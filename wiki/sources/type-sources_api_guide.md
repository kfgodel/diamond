# Diamond API for type sources
This doc shows examples of API usage to get type instances from different sources

#### How to get the Diamond representation of a Type: `Diamond#of()`
```java
Diamond.of(Object.class)
```
Outputs the TypeInstance representation for the type defined by the Object class
```java
Object @ java.lang
```

#### How to get several Diamond representation from Types at once: `Diamond#ofNative()`
```java
Diamond.ofNative(Object.class, String.class, List.class)
```
Outputs the array of type instances for each input type
```java
[Object @ java.lang, String @ java.lang, List @ java.util]
```

### Types accessor
All the type related sources are grouped into a single api point
`Diamond#types()` which allows variations to access type instances

#### How to get a Diamond type from any native reflection alternative: `TypeSources#from(Object)`
Because native reflection uses many different types of objects (some completely 
unrelated) to represent types, this method allows you to have a single point
to get the `TypeInstance` equivalent. Even for AnnotatedTypes, raw Classes or
ParameterizedTypes.
```java
Diamond.types().from(Object.class)
```
Outputs the TypeInstance representation for the type defined by the Object class
```java
Object @ java.lang
```
Notice that this is equivalent to `Diamond#of()` but accepts a broader set
of native type representations

#### How to get the Diamond representation of a generic parameterized Type: `TypeSources#from(ReferenceOf)`
Based on the [TypeReference concept from Neal Gafter](http://gafter.blogspot.com/2006/12/super-type-tokens.html)
a `ReferenceOf` can be used to capture full generics arguments on a parameterized
type and use it to reference that type.  
```java
Diamond.types().from(new ReferenceOf<List<String>>() {});
```
Outputs the TypeInstance representation for the sub-type of List that only contains Strings
```java
List<String> @ java.util
```

#### How to get multiple type representations at once: `TypeSources#from(Object[])`
```java
Diamond.types().from(new Object[]{Object.class, String.class, List.class})
    .collectToList()
```
Outputs the type instances for the give original types 
```java
[Object @ java.lang, String @ java.lang, List @ java.util]
```

#### How to get a type by its name: `TypeSources#named()`
This is only for classes that can be loaded by name into de VM
```java
Diamond.types().named("java.lang.Object")
```
Outputs the TypeInstance representation for the type defined by the Object class
```java
Object @ java.lang
```