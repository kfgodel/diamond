# Diamond API for constructor sources
This doc shows examples of API usage to get constructor instances 
from different sources

### Constructors accessor
All the constructor related sources are grouped into a single api point
`Diamond#constructors()` which allows variations to access constructor instances

#### How to get the Diamond constructors of a class: `ConstructorSources#in()`
```java
Diamond.constructors().in(PublicMembersTestObject.class)
  .all().collectToList()
```
Outputs all the type constructors that PublicMembersTestObject offers
```java
[
PublicMembersTestObject() @ ar.com.kfgodel.diamond.unit.testobjects.modifiers, 
PublicMembersTestObject(Integer) @ ar.com.kfgodel.diamond.unit.testobjects.modifiers
]
```
Notice that this is equivalent to `Diamond.of(Object.class).constructors().all()`

#### How to get the Diamond representation of a native Constructor: `ConstructorSources#from()`
```java
Constructor<PublicMembersTestObject> constructor = null;
try {
  constructor = PublicMembersTestObject.class.getDeclaredConstructor();
} catch (NoSuchMethodException e) {
  throw new RuntimeException("This is why reflection api turns out difficult to use", e);
}

Diamond.constructors().from(constructor)
```
Outputs the TypeConstructor instance for the given constructor
```java
PublicMembersTestObject() @ ar.com.kfgodel.diamond.unit.testobjects.modifiers
```
