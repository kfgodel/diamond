# Diamond API for type fields
This doc shows common use cases as examples of the API for type fields.  
Common member behavior is covered [here](members_api_guide.md) so this only
include API specific for fields

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
