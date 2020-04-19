# Diamond API for metaobjects sources
This doc shows examples of API usage to get meta objects 
from different sources

### Metaobjects accessor
All the metaobjects related sources are grouped into a single api point
`Diamond#metaObjects()` which allows variations to access meta objects

#### How to get a meta object from any instance: `MetaObjectSources#from()`
```java
Diamond.metaObjects().from("Hello World")
```
Outputs the Diamond meta object that allows reflection level manipulation of the 
given instance
```java
meta-Hello World
```

## MetaObject API
Once you get a `MetaObject` instance you can introspect the reflection
information of that instance. For example, getting all the methods
bound to that instance

#### How to get the type of an instance: `MetaObject#type()`
```java
MetaObject metaObject = Diamond.metaObjects().from(new PublicMembersTestObject());

metaObject.type();
```
Outputs the type instance representing the class of the object
```java
PublicMembersTestObject @ ar.com.kfgodel.diamond.unit.testobjects.modifiers
```

#### How to get the methods bound to an instance: `MetaObject#methods()`
```java
MetaObject metaObject = Diamond.metaObjects().from(new PublicMembersTestObject(45));

metaObject.methods().all()
  .collectToList()
```
Outputs the list of invokable methods already bound to the instance
```java
[
method() @@ PublicMembersTestObject{args=[45]}, 
methodWithDiffParamType(String) @@ PublicMembersTestObject{args=[45]}, 
methodWithDiffParamName(Integer) @@ PublicMembersTestObject{args=[45]}, 
methodWithEqualParam(Integer) @@ PublicMembersTestObject{args=[45]}, 
toString() @@ PublicMembersTestObject{args=[45]}, 
finalize() @@ PublicMembersTestObject{args=[45]}, 
wait(long, int) @@ PublicMembersTestObject{args=[45]}, 
wait(long) @@ PublicMembersTestObject{args=[45]}, 
wait() @@ PublicMembersTestObject{args=[45]}, 
equals(Object) @@ PublicMembersTestObject{args=[45]}, 
toString() @@ PublicMembersTestObject{args=[45]}, 
hashCode() @@ PublicMembersTestObject{args=[45]}, 
getClass() @@ PublicMembersTestObject{args=[45]}, 
clone() @@ PublicMembersTestObject{args=[45]}, 
registerNatives() @@ PublicMembersTestObject{args=[45]}, 
notify() @@ PublicMembersTestObject{args=[45]}, 
notifyAll() @@ PublicMembersTestObject{args=[45]}
]
```

#### How to get the fields bound to an instance: `MetaObject#fields()`
```java
MetaObject metaObject = Diamond.metaObjects().from(new PublicMembersTestObject(22));

metaObject.fields().all()
  .collectToList()
```
Outputs the list of fields already bound to the instance
```java
[
args @@ PublicMembersTestObject{args=[22]}, 
field @@ PublicMembersTestObject{args=[22]}
]
```