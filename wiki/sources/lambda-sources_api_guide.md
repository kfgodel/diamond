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
