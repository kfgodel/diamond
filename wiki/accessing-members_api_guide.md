# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
access type members (methods, fields and constructors)

## Accessing members

#### How to get all the members of a type: `TypeInstance#members()`
The returned members includes methods, fields and constructors of the type
```java
TypeInstance typeInstance = Diamond.of(PublicMembersTestObject.class);

typeInstance.members().collectToList();
```
Outputs the list of members of the type (including inherited)
```java
[field @ PublicMembersTestObject, method() @ PublicMembersTestObject, methodWithDiffParamType(String) @ PublicMembersTestObject, methodWithDiffParamName(Integer) @ PublicMembersTestObject, methodWithEqualParam(Integer) @ PublicMembersTestObject, finalize() @ Object, wait(long, int) @ Object, wait(long) @ Object, wait() @ Object, equals(Object) @ Object, toString() @ Object, hashCode() @ Object, getClass() @ Object, clone() @ Object, registerNatives() @ Object, notify() @ Object, notifyAll() @ Object, PublicMembersTestObject(Integer) @ ar.com.kfgodel.diamond.unit.testobjects.modifiers, PublicMembersTestObject() @ ar.com.kfgodel.diamond.unit.testobjects.modifiers]
```
Notice that `@` is used in the `toString()` of every member to indicate 
the scope where the member was declared

### Accessing Fields

#### How to get all the fields of a type: `TypeFields#all()`
This includes also the inherited fields.
```java
TypeInstance typeInstance = Diamond.of(String.class);

typeInstance.fields().all()
  .collectToList()
```
Outputs the list of fields, including static and inherited
```java
[value @ String, hash @ String, serialVersionUID @ String, serialPersistentFields @ String, CASE_INSENSITIVE_ORDER @ String]
```

#### How to get a field by name: `TypeFields#named()`
```java
TypeInstance typeInstance = Diamond.of(RedefiningFieldTestObject.class);

typeInstance.fields().named("uniqueField")
  .unique().get()
```
Outputs the only type field found with that name
```java
uniqueField @ RedefiningFieldTestObject
```

### Accessing Methods

#### How to get all the methods of a type: `TypeMethods#all()`
This includes also the overrided method.
```java
TypeInstance typeInstance = Diamond.of(RedefiningMethodTestObject.class);

typeInstance.methods().all()
  .collectToList()
```
Outputs the list of methods available and overrides the given type
```java
[uniqueMethod() @ RedefiningMethodTestObject, redefinedAndOverloadedMethod() @ RedefiningMethodTestObject, redefinedAndOverloadedMethod(int) @ RedefiningMethodTestObject, redefinedAndOverloadedMethod() @ RedefinedMethodTestObject, finalize() @ Object, wait(long, int) @ Object, wait(long) @ Object, wait() @ Object, equals(Object) @ Object, toString() @ Object, hashCode() @ Object, getClass() @ Object, clone() @ Object, registerNatives() @ Object, notify() @ Object, notifyAll() @ Object]
```

#### How to get a method by name: `TypeMethods#named()`
```java
TypeInstance typeInstance = Diamond.of(Object.class);

typeInstance.methods().named("equals")
  .unique().get()
```
Outputs the only method found with that name on the type
```java
equals(Object) @ Object
```

#### How to get the overriding method by name and avoid overrided methods: `findFirst()`
Methods are ordered so the first occurrence is the closest to the concrete class.
Later methods appear as the iteration moves up in the hierarchy.
```java
TypeInstance typeInstance = Diamond.of(RedefiningMethodTestObject.class);

typeInstance.methods().named("redefinedAndOverloadedMethod")
  .findFirst().get()
```
Outputs the only method found with that name on the type
```java
redefinedAndOverloadedMethod() @ RedefiningMethodTestObject
```

#### How to get a method by its signature: `TypeMethods#withSignature()`
```java
TypeInstance typeInstance = Diamond.of(Object.class);

typeInstance.methods().withSignature("equals", Diamond.of(Object.class))
  .unique().get()
```
Outputs the only method found with that name on the type
```java
equals(Object) @ Object
```

#### How to get a method by its parameter types: `TypeMethods#withParameterTypes()`
```java
TypeInstance typeInstance = Diamond.of(Object.class);

typeInstance.methods().withParameterTypes(Diamond.of(Object.class))
  .unique().get()
```
Outputs the only method found with that name on the type
```java
equals(Object) @ Object
```

### Accessing Constructors

#### How to get all the constructors of a type: `TypeConstructors#all()`
```java
TypeInstance typeInstance = Diamond.of(Long.class);

typeInstance.constructors().all()
  .collectToList()
```
Outputs the list of constructors available for the type
```java
[Long(long) @ java.lang, Long(String) @ java.lang]
```

#### How to get the no-arg constructor of a type: `TypeConstructors#niladic()`
```java
TypeInstance typeInstance = Diamond.of(ConstructorAccessTestObject.class);

typeInstance.constructors().niladic()
  .get()
```
Outputs the only constructor with no arguments for that type
```java
ConstructorAccessTestObject() @ ar.com.kfgodel.diamond.unit.testobjects.constructors
```

#### How to get a constructor by its parameter types: `TypeConstructors#withParameterTypes()`
```java
TypeInstance typeInstance = Diamond.of(ConstructorAccessTestObject.class);

typeInstance.constructors().withParameterTypes(Diamond.of(Integer.class))
  .unique().get()
```
Outputs the only method found with that name on the type
```java
ConstructorAccessTestObject(Integer) @ ar.com.kfgodel.diamond.unit.testobjects.constructors
```
