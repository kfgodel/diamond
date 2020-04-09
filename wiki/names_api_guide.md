# Diamond API for names
This doc shows common use cases as examples of the API to access names


## Type names
Java has different types and each type has different names.
Diamond tries to unify those names and standardize the api to access them.
To access the different options for names of a TypeInstance do:
```java
Diamond.of(aType).names()
``` 

### How to get the simple name of a class: `TypeNames#shortName()`
Short name gives you the shortest representation a type has to be used.
```java
typeInstance.names().shortName()
```
For a list of strings outputs:
```java
"List"
```
Which is the shortest name of the class.  
Take into consideration that wilcard types may have the same shortname `?` while
representing different types

### How to get the name of a class: `TypeNames#commonName()`
Common name is what we are used to find as names for classes on runtime. 
For other types this is the typename, because they don't have a `getName()`.
```java
typeInstance.names().commonName()
``` 
For a list of strings outputs:
```java
"java.util.List"
```
Which is the usual binary name that identifies the type at runtime.  
Take into consideration that this name is not very friendly for humans.
For example an array of strings is `[Ljava.lang.String;`

### Hot to get the canonical name of a class: `TypeNames#canonicalName()`
This name makes sense for classes and not too much for other types. It's
the name that identifies the type and it's readable for humans.
```java
typeInstance.names().canonicalName()
```
For a list of strings outputs:
```java
"java.util.List"
```
Which is the usual full name of a class and matches the common name.  
In contrast to the common name this is not the binary format.
For example an array of strings is `java.lang.String[]`

### How to get the typeName of a type: `TypeNames#typeName()`
Every java `Type` has a `getTypeName()` method that gives the name of the type.
However, due to design differences each type behaves differently. 
For example, Wildcards include bounds and type variables don't.

```java
typeInstance.names().typeName()
```
For a list of strings outputs:
```java
"java.util.List<java.lang.String>"
```
Which is the complete parameterized type name.  

### How to get the annotated name of a type: `TypeNames#completeName()`
Added by diamond this name standardize the differences between types and 
adds annotation information to the name of a type to completely identify and 
discriminate it from other types.
```java
typeInstance.names().completeName()
```
For an annotated list of strings outputs:
```java
"@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() java.util.List<@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String>"
```
Which includes the annotation of each type as well as generics, bounds, or components types.
This is the longest name you can get with diamond and the closest to the source declaration.

### How to get the minimum name of a type: `TypeNames#bareName()`
This name is added by diamond to have a consistent name between types without 
including relationships to other types. It tries to give a `typeName()` equivalent
but being consistent.

```java
typeInstance.names().bareName()
```
For a list of strings outputs:
```java
"java.util.List"
```
Which is the cannonical name.
Take into consideration that for relation types (array, wildcards, typevariables)
this name doesn't include relation component or bounds. Examples: `[]`,`?`,`T`



  
## Member names
All the members of a type (fields, methods, constructors) have a name that can
be accessed:
```java
typeMember.name()
```

### How to get the name of a method: `TypeMethod#name()` 
This is the equivalent of calling `java.lang.reflect.Method#getName()`
```java
TypeMethod typeMethod = Diamond.of(Object.class)
  .methods().all()
  .filter(method -> method.name().startsWith("to"))
  .findFirst().get();

typeMethod.name()
``
Outputs the name of `toString()` method
```java
"toString"
```

### How to get the name of a field: `TypeField#name()`
Equivalent of calling `java.lang.reflect.Field#getName()`
```java
TypeField typeField = Diamond.of(String.class)
  .fields().all()
  .filter(field -> field.name().startsWith("val"))
  .findFirst().get();

typeField.name()
```
Outputs the `value` array field holding the string chars
```java
"value"
```

### How to get the name of a constructor: `TypeConstructor#name()`
Equivalent of calling `java.lang.reflect.Constructor#getName()`
```java
TypeConstructor typeConstructor = Diamond.of(String.class)
  .constructors().all()
  .findFirst().get();

typeConstructor.name()
```
Outputs the name of its declaring class
```java
"java.lang.String"
```


