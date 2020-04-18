# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
test or check a type against other types

### Is... questions
All the questions that have the form is... and return a boolean as response
are grouped for type instance in its `TypeInstance#is()` method

#### How to test if an instance is from a type: `TypeTests#instance()`
This is equivalent to `java.lang.Class#isInstance()`
 ```java
Object anObject = new Object();
TypeInstance objectType = Diamond.of(Object.class);

objectType.is().instance(anObject);
```
Outputs the boolean answering the question
```java
true
```

#### How to test if a type represents an interface: `TypeTests#partOf()`
This is a concept added by Diamond and not part of native reflection
 ```java
TypeInstance objectType = Diamond.of(List.class);

objectType.is().partOf(Categories.INTERFACE);
```
Outputs the boolean answering the question
```java
true
```
Note that some of the categories are added by Diamond and not part of native
reflection (like `NUMERIC`).

#### How to test if a type is a runtime subtype: `TypeTests#assignableFrom()`
This is equivalent to `java.lang.class.Class#isAssignableFrom()`
 ```java
TypeInstance subtype = Diamond.of(ArrayList.class);
TypeInstance superType = Diamond.of(List.class);

superType.is().assignableFrom(subtype);
```
Outputs the boolean answering the question
```java
true
```

#### How to test if a type is a runtime supertype: `TypeTests#assignableTo()`
Inverse of `assignableFrom()`.

 ```java
TypeInstance subtype = Diamond.of(ArrayList.class);
TypeInstance superType = Diamond.of(List.class);

subtype.is().assignableTo(superType);
```
Outputs the boolean answering the question
```java
true
```
 
 #### How to test if a type is a compile time subtype: `TypeTests#subTypeOf()`
 The relationship depends on the classes used at runtime to represent
 the compile type. This method is useful for parameterized type, type variables, etc. 
 
 ```java
 TypeInstance parameterizedMap = Diamond.types()
  .from(new ReferenceOf<Map<String, Integer>>() {});
 
parameterizedMap.is().subTypeOf(Diamond.of(Map.class))
```
 Outputs the boolean answering the question
 ```java
 true
 ```

 #### How to test if a type is a compile time supertype: `TypeTests#superTypeOf()`
 The relationship depends on the classes used at runtime to represent
 the compile type. This method is useful for parameterized type, type variables, etc. 
 
 ```java
TypeInstance mapType = Diamond.of(Map.class);
TypeInstance parameterizedMap = Diamond.types()
  .from(new ReferenceOf<Map<String, Integer>>() {});
 
mapType.is().superTypeOf(parameterizedMap)
```
 Outputs the boolean answering the question
 ```java
 true
 ```