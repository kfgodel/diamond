# Diamond API for field sources
This doc shows examples of API usage to get parameter instances 
from different sources

### Parameters accessor
All the parameter related sources are grouped into a single api point
`Diamond#parameters()` which allows variations to access parameter instances

#### How to get the Diamond representation of a native parameter: `ParameterSources#from()`
```java
Method method = null;
try {
  method = Object.class.getDeclaredMethod("equals", Object.class);
} catch (NoSuchMethodException e) {
  throw new RuntimeException("This is why reflection api turns out difficult to use", e);
}
Parameter nativeParameter = method.getParameters()[0];

Diamond.parameters().from(nativeParameter)
```
Outputs the Diamond pararameter from the native instance
```java
Object arg0
```

#### How to get the Diamond representation of several parameters: `ParameterSources#from(Parameter[])`
```java
Method method = null;
try {
  method = Object.class.getDeclaredMethod("equals", Object.class);
} catch (NoSuchMethodException e) {
  throw new RuntimeException("This is why reflection api turns out difficult to use", e);
}
Parameter[] nativeParameters = method.getParameters();

Diamond.parameters().from(nativeParameters)
    .collectToList()
```
Outputs the list of Diamond parameters 
```java
[Object arg0]
```

