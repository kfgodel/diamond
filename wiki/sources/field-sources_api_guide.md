# Diamond API for type sources
This doc shows examples of API usage to get field instances from different sources

### Fields accessor
All the field related sources are grouped into a single api point
`Diamond#fields()` which allows variations to access field instances

#### How to get the Diamond fields of a class: `FieldSources#in()`
```java
Diamond.fields().in(ClassWithIdField.class)
  .all().collectToList()
```
Outputs all the type fields that ClassWithIdField offers
```java
[id @ ClassWithIdField]
```
Notice that this is equivalent to `Diamond.of(Object.class).fields().all()`

#### How to get the Diamond representation of a native Field: `FieldSources#from()`
```java
Field fieldInstance = null;
try {
  fieldInstance = ClassWithIdField.class.getDeclaredField("id");
} catch (NoSuchFieldException e) {
  throw new RuntimeException("This is why reflection api turns out difficult to use", e);
}

Diamond.fields().from(fieldInstance)
```
Outputs the TypeField instance for the given field
```java
id @ ClassWithIdField
```
