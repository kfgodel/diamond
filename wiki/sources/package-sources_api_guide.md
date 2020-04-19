# Diamond API for package sources
This doc shows examples of API usage to get package instances 
from different sources

### Packages accessor
All the package related sources are grouped into a single api point
`Diamond#packages()` which allows variations to access package instances

#### How to get the Diamond representation of a native Package: `PackageSources#from()`
```java
Package nativePackage = Package.getPackage("java.lang");

Diamond.packages().from(nativePackage);
```
Outputs the Diamond package from the native instance
```java
java.lang
```

#### How to get a package by name: `PackageSources#named()`
```java
Diamond.packages().named("java.lang")
```
Outputs the Diamond package found by name 
```java
java.lang
```

