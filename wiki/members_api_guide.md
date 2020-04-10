# Diamond API for type members
This doc shows common use cases as examples of the API available to all type members (fields, methods and constructors) 

## Common behavior
Every member of a type (field, method or constructor) is an instance of `TypeMember`
which ensures certain common behavior that is inherited to all members:

### Named
Every member has a name (even constructors)

#### How to access the name of a member: `Named#name()`
Equivalent of calling `java.lang.reflect.[Field|Method|Constructor]#getName()`
```java
TypeMember typeMember = Diamond.of(String.class)
  .fields().all()
  .filter(field -> field.name().startsWith("val"))
  .findFirst().get();

typeMember.name()
```
Outputs the `value` array field holding the string chars
```java
"value"
```


### Annotated
All the members of a type can be annotated, and thus its annotations be accessed
introspectively
 
#### How to access the annotations declared on a member: `Annotated#annotations()`
Equivalent of calling `java.lang.reflect.[Field|Method|Constructor]#geAnnotations()`
```java
TypeMember typeMember = Diamond.of(MemberAnnotationTestObject.class)
  .fields().named("annotatedField")
  .unique().get();

typeMember.annotations()
  .collectToList()
```
Outputs the list of annotations applied to the member
```java
[@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1(), @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2()]
```


### Modifiable
All the type members, including constructors, can have modifiers (i.g: private, final, static, etc)
In contrast to native reflection, Diamond reifies each modifier so it's not 
represented by an int, or a bit in a bitmap. Each modifier is an enum instance
that you can access directly and interop with native reflection representation.

#### How to access all the modifiers of a member: `Modifiable#modifiers()`
Calculated from accessing the modifiers bitmap int from: `java.lang.reflect.[Field|Method|Constructor]#getModifiers()`

```java
TypeMember typeMember = Diamond.of(PrivateMembersTestObject.class)
  .methods().named("method")
  .unique().get();

typeMember.modifiers()
  .collectToList()
```
Outputs the list containing the modifier enums for the member
```java
[private]
```

### Hot to check if a member is private: `Modifiable#is()`
Shorthand of `Modifiable#modifiers().anymatch(modifier)`

```java
TypeMember typeMember = Diamond.of(PrivateMembersTestObject.class)
  .methods().named("method")
  .unique().get();

typeMember.is(Modifiers.PRIVATE)
```
Outputs the boolean indicating the match
```java
true
```


### Generified
Every member can have type parameters used to generify its declaration.

#### How to access the type parameters used on a member declaration: `Generified#generics()`
```java
TypeMember typeMember = Diamond.of(GenericMembersTestObject.class))
  .methods().named("method")
  .unique().get());

typeMember.generics().arguments()
  .collectToList()
```
Outputs the list of type instances that represent the actual type arguments used on the method
```java
[R extends Object]
```


### Declarable
Every member has a description including all the information that can be used
to discriminate it from othe similar methods. 

#### How to get a description of the member closest to its declaration: `Declarable#declaration()`
 ```java
TypeMember typeMember = Diamond.of(MemberNamingTestObject.class)
   .methods().named("methodWithArgs")
   .unique().get();

typeMember.declaration()
```
Outputs the string that is closest to include all the runtime available information about the member 
 ```java
"@ar.com.kfgodel.diamond.unit.testobjects.annotations.MethodTestAnnotation() @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3() public <S extends java.lang.Object> @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3() int methodWithArgs(java.lang.String, S extends java.lang.Object) /* MemberNamingTestObject */"
 ```
Even the declaring type is included as a comment at the end to discriminate overriding methods


### Executable
Every type member, in Diamond, is treated as an executable member. Even fields
can be used as lambdas to be executed and produce side effects.
Depending on the specific type of member, the lambda invocation will have 
different semantics. 

#### How to get a polymorphic lambda from a member: `Executable#asFunction()`
The returned 'lambda' like object implements almost all the lambda interfaces,
allowing you to use it on several contexts.

```java
TypeMember typeMember = Diamond.of(FunctionalFieldTestObject.class)
  .fields().named("staticField")
  .unique().get();

typeMember.asFunction().accept(3)
```
It sets the a value of `3` on the static field named `staticField` in `FunctionalFieldTestObject`.
Which is equivalent of calling `FunctionalFieldTestObject.staticField = 3` as setter   

##### How to use any member as a getter lambda
 ```java
// Set a value on the instance field for the test
object.instanceField = 20;

TypeMember typeMember = Diamond.of(FunctionalFieldTestObject.class)
  .fields().named("instanceField")
  .unique().get();

typeMember.asFunction().apply(object)
```
Outputs the value we set earlier in the field, taken from the object 
```java
20
  ```
##### How to use any member as a setter lambda
 ```java
TypeMember typeMember = Diamond.of(FunctionalFieldTestObject.class)
  .fields().named("instanceField")
  .unique().get();

typeMember.asFunction().accept(object, 30)
```
It sets the a value of `30` on the instance field named `instanceField` for the passed `object`.
Which is equivalent of calling `object.instanceField = 30` as setter   

#### How to know the return type of any member used as function: `Executable#returnType()` 
 ```java
TypeMember typeMember = Diamond.of(MethodReturnTypeTestObject.class)
  .methods().named("voidReturnedMethod")
 .unique().get();

typeMember.returnType()
```
Outputs the type instance representing void (the return type of that method) 
 ```java
void
 ```

#### How to know the expected parameters of a member used as a function: `Executable#parameters()`
Although not every member declares an explicit set of parameters like methods do,
you can ask the member what parameters it expects to receive (useful for methods and constructors).
Each parameter includes the name and its type 

 ```java
TypeMember typeMember = Diamond.of(Integer.class)
  .constructors().all()
  .findFirst().get();

typeMember.parameters()
```
Outputs the list of expected arguments for the first constructor. Notice
how the argument name depends on the information the compiler adds when generating
the byte code (or the lack of it). 
 ```java
[int arg0]
 ```

#### How to know the expected parameter types of a member used as function: `ParameterizedBehavior#parameterTypes()`
This gives you only the parameter types. Equivalent to `java.lang.reflect.[Method|Constructor]#getParameterTypes()`
 ```java
TypeMember typeMember = Diamond.of(MethodParameterTypesTestObject.class)
  .methods().named("oneStringOneNumberParameters")
  .unique().get();

typeMember.parameterTypes()
  .collectToList()
```
Outputs the list of type instances representing the parameter types in order 
 ```java
[String @ java.lang, Number @ java.lang]
 ```

#### How to know the declared exception of the member: `Exceptionable#declaredExceptions`
Because not all members declare exceptions this may return empty depending on the member type.

 ```java
TypeMember typeMember = Diamond.of(MemberExceptionsTestObject.class)
  .methods().named("method")
  .unique().get();

typeMember.declaredExceptions()
  .collectToList()
```
Outputs the list of type instances representing the declared exception types 
 ```java
[RuntimeException @ java.lang, Exception @ java.lang, Throwable @ java.lang]
 ```