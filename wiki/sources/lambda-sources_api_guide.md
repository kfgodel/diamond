# Diamond API for lambda sources
This doc shows examples of API usage to get lambda instances 
from different sources

### Lambdas accessor
All the lambda related sources are grouped into a single api point
`Diamond#lambdas()` which allows variations to access lambda instances.

A `Lambda` instance, created from any form of native lambda, allows you
know the parameters the lambda expects, the return type, execute it as
a different form of lambda, etc. 

#### How to get the Diamond representation of a native lambda: `LambdaSources#fromXXXX()`
There's one method variant for each native lambda type
```java
Diamond.lambdas().fromFunction((arg1) -> 1)
```
Outputs the Diamond lambda that represents the given instance
```java
lambda(Object)->Object
```

#### How to know the parameters a lambda expects: `Executable#parameters()`
```java
Lambda lambda = Diamond.lambdas().fromConsumer((arg1) -> LOG.debug("Running as consumer: " + arg1))

lambda.parameters()
  .collectToList()
```
Outputs the list of parameters the give lambda expects
```java
[Object arg0]
```

#### How to get the return type of a lamdbda: `Executable#returnType()`
```java
Lambda lambda = Diamond.lambdas().fromSupplier(() -> 1);

lambda.returnType()
```
Outputs the Type that represents the lambda return type
```java
Object @ java.lang
```
Notice that because lambdas are compile time syntactic sugar the
actual return type is not retained in runtime (and thus, not available for reflection)