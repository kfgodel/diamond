# Diamond API for type fields
This doc shows common use cases as examples of the API for type constructors.  
Common member behavior is covered [here](members_api_guide.md) so this only
include API specific for constructors

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
