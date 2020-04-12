# Diamond API for type fields
This doc shows common use cases as examples of the API for type constructors.  
Common member behavior is covered [here](members_api_guide.md) so this only
include API specific for constructors

#### How to invoke a constructor: `TypeConstructor#invoke()`
Equivalent to `java.lang.Constructor#newInstance()`

```java
TypeConstructor typeConstructor = Diamond.of(PublicMembersTestObject.class)
  .constructors().niladic().get();

typeConstructor.invoke()
``
Outputs the created instance
```java
"PublicMembersTestObject{args=[]}"
```

#### How to get the native reflection instance for a constructor: `TypeConstructor#nativeType()`
This is only available for native java types, and allows you to inter-operate
with other non-Diamond code.

```java
TypeConstructor typeConstructor = Diamond.of(PublicMembersTestObject.class)
  .constructors().niladic().get();

typeConstructor.nativeType().get()
```
Outputs the native java.lang.Constructor instance that represents the same constructor
```java
"public ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject()"
```


### Constructor used as lambda
Every constructor can be treated as different types of lambdas to be invoked with
combinations of parameters, or chose the lambda that best suits its signature. 

#### How to use a constructor as a Supplier: `TypeConstructor#get()`
```java
TypeConstructor typeConstructor = Diamond.of(PublicMembersTestObject.class)
  .constructors().niladic().get();

typeConstructor.get()
``
Outputs the newly created instance
```java
"PublicMembersTestObject{args=[]}"
```

#### How to use a constructor that accepts an argument as a Function: `TypeConstructor#apply()`
```java
TypeConstructor typeConstructor = Diamond.of(FunctionalConstructorTestObject.class)
  .constructors().withParameterTypes(Diamond.of(Integer.class))
  .unique().get();

typeConstructor.apply(1)
``
Outputs the newly created object using 1 as constructor argument
```java
"FunctionalConstructorTestObject{args=[1]}"
```

#### How to bind arguments to the parameters of a constructor: `TypeConstructor#withArguments()`
```java
TypeConstructor typeConstructor = Diamond.of(MethodCallTestObject.class)
  .constructors().withNativeParameterTypes(Object.class, Object.class, Object.class)
  .unique().get();

typeConstructor.withArguments(1, 2, 3)
``
Outputs the prepared constructor call created by binding the arguments
```java
(1, 2, 3) bound to MethodCallTestObject(Object, Object, Object) @ ar.com.kfgodel.diamond.unit.testobjects.methods
```

#### How to invoke a constructor with bound arguments: `BehaviorCall#invokeOn()`

```java
BehaviorCall constructorCall = Diamond.of(MethodCallTestObject.class)
  .constructors().withNativeParameterTypes(Object.class, Object.class, Object.class)
  .unique().get()
  .withArguments(1, 2, 3);

constructorCall.invoke()
``
Outputs the newly created instance using the arguments 
```java
"MethodCallTestObject{staticInvoked=constructor(1, 2, 3), instanceInvoked=none, firstParam=1}"
```