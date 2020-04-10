# Diamond API for type fields
This doc shows common use cases as examples of the API for type methods.  
Common member behavior is covered [here](members_api_guide.md) so this only
include API specific for methods

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
