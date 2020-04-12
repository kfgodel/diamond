# Diamond API for type fields
This doc shows common use cases as examples of the API for type methods.  
Common member behavior is covered [here](members_api_guide.md) so this only
include API specific for methods

#### How to get the default value of a method: `TypeMethod#defaultValue()` 
This is the equivalent of calling `java.lang.reflect.Method#getDefaultValue()`
```java
TypeMethod typeMethod = Diamond.of(DefaultValueAnnotation.class)
  .methods().named("memberWithDefault")
  .unique().get();

typeMethod.defaultValue().get()
``
Outputs the value defined as default for that method
```java
23
```

#### How to invoke a method on an instance: `TypeMethod#invokeOn()`
This can be used for static methods passing null as the instance.
Equivalent to `java.lang.Method#invoke()`

```java
MethodInvocationTestObject object = new MethodInvocationTestObject();

TypeMethod typeMethod = Diamond.of(MethodInvocationTestObject.class)
  .methods().named("publicMethod")
  .unique().get();

typeMethod.invokeOn(object)
``
Outputs the return value of the invoked method
```java
1
```

#### How to get the native reflection instance for a method: `TypeMethod#nativeType()`
This is only available for native java types, and allows you to inter-operate
with other non-Diamond code.

```java
TypeMethod method = Diamond.of(PublicMembersTestObject.class)
  .methods().named("method")
  .unique().get();

method.nativeType().get()
```
Outputs the native java.lang.Method instance that represents the same method
```java
"public void ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject.method()"
```

### Method used as lambda
Every method can be treated as different types of lambdas to be invoked with
combinations of parameters, or chose the lambda that best suits its signature. 

#### How to use a static method as a Supplier: `TypeMethod#get()`
```java
TypeMethod typeMethod = Diamond.of(FunctionalStaticMethodTestObject.class)
  .methods().named("supplier")
  .unique().get();

typeMethod.get()
``
Outputs the return value of the invoked staic method
```java
1
```

#### How to use a static method that accepts an argument as a Function: `TypeMethod#apply()`
```java
TypeMethod typeMethod = Diamond.of(FunctionalStaticMethodTestObject.class)
  .methods().named("function")
  .unique().get();

typeMethod.apply(85)
``
Outputs the return value of the invoked staic method
```java
85
```

#### How to use an instance no-arg method as a Consumer: `TypeMethod#accept()`
```java
FunctionalInstanceMethodTestObject object = ...
TypeMethod typeMethod = Diamond.of(FunctionalInstanceMethodTestObject.class)
  .methods().named("consumer")
  .unique().get();

typeMethod.accept(object)
```
Which doesn't have a return value, but invokes the instance method on the object

#### How to use a setter method as a BiConsumer: `TypeMethod#accept()`
```java
FunctionalInstanceMethodTestObject object = ...
TypeMethod typeMethod = Diamond.of(FunctionalInstanceMethodTestObject.class)
  .methods().named("biConsumer")
  .unique().get();

typeMethod.accept(object, 13)
```
Which doesn't have a return value, but sets the value to 13

### Binding a method to an instance: `BoundMethod`
A type method can be bound to an instance so it's not necessary to indicate it
everytime.

#### How to bind an instance to a type method: `TypeMethod#bindTo()`
```java
BoundMethodTestObject object = ...;

TypeMethod typeMethod = Diamond.of(BoundMethodTestObject.class)
  .methods().named("sum")
  .unique().get();

typeMethod.bindTo(object)
```
Outputs the bound method which has the implicit object
```java
sum(int, int) @@ BoundMethodTestObject instance
```

#### How to invoke a bound method on an instance: `BoundMethod#invoke()`
Once bound, a method doesn't need the instance to be passed to be invoked.
```java
BoundMethodTestObject object = ...;

BoundMethod boundMethod = Diamond.of(BoundMethodTestObject.class)
  .methods().named("sum")
  .unique().get()
  .bindTo(object);

boundMethod.invoke(1,2)
```
Outputs the result of the method invocation which is the sum of its arguments
```java
3
```

#### How to bind arguments to the parameters of a method: `TypeMethod#withArguments()`
```java
TypeMethod typeMethod = Diamond.of(MethodCallTestObject.class)
  .methods().named("createdWith")
  .unique().get();

typeMethod.withArguments(1)
``
Outputs the prepared method call created with binding the arguments
```java
(1) bound to createdWith(Object) @ MethodCallTestObject
```

#### How to invoke a method with bound arguments: `BehaviorCall#invokeOn()`

```java
MethodCallTestObject object = new MethodCallTestObject("a", "b", "c");

BehaviorCall methodCall = Diamond.of(MethodCallTestObject.class)
  .methods().named("createdWith")
  .unique().get()
  .withArguments("a");

methodCall.invokeOn(object)
``
Outputs the result of the method matching the argument with the intance 
```java
true
```

