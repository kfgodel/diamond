# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
get information about generics from a type

### Type generics
Types that are generified have additional information that can be accessed
using `TypeInstance#generics()`

#### How to get the type arguments of a parameterized type: `TypeGenerics#arguments()`
Equivalent to `java.lang.reflect.ParameterizedType#getActualTypeArguments()`
```java
final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<
  ? extends Number,
  ? super Serializable
>>() {});
 
mapType.generics().arguments()
  .collectToList()
```
Outputs the type argument list used to parameterize Map  
```java
[? extends Number, ? super Serializable]
```

#### How to get the type parameters of a parameterizable type: `TypeGenerics#parameters()`
Equivalent to `java.lang.Class#getTypeParameters()()`
```java
final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<
  ? extends Number,
  ? super Serializable
>>() {});
 
mapType.generics().parameters()
  .collectToList()
```
Outputs the type parameters list defined with the parameterizable type  
```java
[K extends Object, V extends Object]
```
Notice that neither `K` or `V` declared on Map explicit the upper bound
however in Diamond if no upper bound is explicit, then Object is assumed


### The bounds of a type can be accessed with: `TypeGenerics#bounds()`
This combines `java.lang.reflect.TypeVariable#getBounds()` and
`java.lang.reflect.TypeVariable#getAnnotatedBounds()` with 
`java.lang.reflect.WildcardType#getUpperBounds()` and 
`java.lang.reflect.WildcardType#getLowerBounds()` 
 
#### How to get the upper bounds of a type: `TypeBounds#upper()`
```java
 // We need a parameterized type in order to declare each bound wildcard
 final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<
   ? extends Number,
   ? super Serializable
   >>() {});
 
 mapType.generics().arguments()
   .findFirst()
   .get();
```
Outputs the first type argument which is upper bound to Number  
```java
? extends Number
```

#### How to get the lower bounds of a type: `TypeBounds#upper()`
 ```java
// We need a parameterized type in order to declare each bound wildcard
final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<
  ? extends Number,
  ? super Serializable
  >>() {});

mapType.generics().arguments()
  .skip(1)
  .findFirst()
  .get();
```
 Outputs the second type argument which is lower bound to Serializable  
 ```java
 ? super Serializable
 ```
