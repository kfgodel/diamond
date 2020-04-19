# Diamond API for method sources
This doc shows examples of API usage to get method instances from different sources

### Methods accessor
All the method related sources are grouped into a single api point
`Diamond#methods()` which allows variations to access method instances

#### How to get the Diamond methods of a class: `MethodSources#in()`
```java
Diamond.methods().in(Object.class)
  .all().collectToList()
```
Outputs all the type methods that Object offers
```java
[
finalize() @ Object, 
wait(long, int) @ Object, 
wait(long) @ Object,
 wait() @ Object, 
equals(Object) @ Object, 
toString() @ Object, 
hashCode() @ Object, 
getClass() @ Object, 
clone() @ Object, 
registerNatives() @ Object, 
notify() @ Object, 
notifyAll() @ Object
]
```
Notice that this is equivalent to `Diamond.of(Object.class).methods().all()`

#### How to get the Diamond representation of a native Method: `MethodSources#from()`
```java
Method methodInstance = null;
try {
  methodInstance = Object.class.getMethod("equals", Object.class);
} catch (NoSuchMethodException e) {
  throw new RuntimeException("This is why reflection api turns out difficult to use", e);
}

Diamond.methods().from(methodInstance)
```
Outputs the TypeMethod instance for the given method
```java
equals(Object) @ Object
```
