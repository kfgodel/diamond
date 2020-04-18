# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
create new instances from a instantiable type

#### How to quickly create an instance of a type using the niladic constructor: `TypeInstance#newInstance()`
This only works for types that have a no-arg constructor. 
```java
TypeInstance type = Diamond.of(PublicMembersTestObject.class);

type.newInstance()
```
Outputs the newly created object
```java
"PublicMembersTestObject{args=[]}"
```
#### How to use a type as a supplier: `TypeInstance#get()`
This is an alias to `newInstance()` 
```java
TypeInstance type = Diamond.of(PublicMembersTestObject.class);

type.get()
```
Outputs the newly created object
```java
"PublicMembersTestObject{args=[]}"
```

