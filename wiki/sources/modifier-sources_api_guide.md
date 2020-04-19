# Diamond API for modifier sources
This doc shows examples of API usage to get modifier instances 
from different sources

### Modifiers accessor
All the modifier related sources are grouped into a single api point
`Diamond#modifiers()` which allows variations to access modifiers instances

#### How to get all the Diamond reified modifiers: `ModifierSources#all()`
```java
Diamond.modifiers().all()
  .collectToList()
```
Outputs all the instances that represent modifiers
```java
[public, private, protected, , final, static, abstract, synchronized, native, strictfp, transient, volatile]
```

#### How to get the modifiers of a native reflection member: `ModifierSources#from()`
```java
Method method = null;
try {
  method = Object.class.getDeclaredMethod("equals", Object.class);
} catch (NoSuchMethodException e) {
  throw new RuntimeException("This is why reflection api turns out difficult to use", e);
}

Diamond.modifiers().from(method)
    .collectToList()
```
Outputs all the modifiers declared on the given native member
```java
[public]
```

#### How to get the modifiers from a member modifier bitmap: `ModifierSources#fromMember()`
Due to reflection representation of modifiers, a bitmap is natively used.
This allows a conversion to objects

```java
int bitmap = java.lang.reflect.Modifier.PUBLIC | java.lang.reflect.Modifier.ABSTRACT;

Diamond.modifiers().fromMember(bitmap)
    .collectToList()
```
Outputs all the modifiers interpreted from the bitmap
```java
[public, abstract]
```

#### How to get the modifiers from a parameter modifier bitmap: `ModifierSources#fromParameter()`
Parameter modifier bitmap interpretation is different. As the lack of
visibility modifier doesn't imply "package" level scope (in contrast to member bitmaps)

```java
int bitmap = java.lang.reflect.Modifier.FINAL;

Diamond.modifiers().fromParameter(bitmap)
    .collectToList()
```
Outputs all the modifiers interpreted from the bitmap
```java
[final]
```