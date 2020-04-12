# Diamond API for type fields
This doc shows common use cases as examples of the API for type fields.  
Common member behavior is covered [here](members_api_guide.md) so this only
include API specific for fields

#### How to know the type of a field: `TypeField#type()`
Equivalent of calling `java.lang.reflect.Field#getType()`
```java
TypeField typeField = Diamond.of(FieldTypeTestObject.class)
  .fields().named("intField")
  .unique().get());

typeField.type()
```
Outputs the type instance that represents the type of the field 
```java
int
```

#### How to get the value of a field from an object: `TypeField#getValueFrom()`
Equivalent of calling `java.lang.reflect.Field#get()`
```java
FieldAccessorTestObject object = ...;

TypeField typeField = Diamond.of(FieldAccessorTestObject.class)
  .fields().named("publicField")
  .unique().get());

typeField.getValueFrom(object)
```
Outputs the value of the field for the given object
```java
1
```

#### How to set the value of a field on an object: `TypeField#setValueOn()`
Equivalent of calling `java.lang.reflect.Field#set()`
```java
FieldAccessorTestObject object = ...;

TypeField typeField = Diamond.of(FieldAccessorTestObject.class)
  .fields().named("publicField")
  .unique().get());

typeField.setValueOn(object, 3)
```
Which is equivalent of doing `object.publicField = 3`

#### How to get the native reflection instance for a field: `TypeField#nativeType()`
This is only available for native java types, and allows you to inter-operate
with other non-Diamond code.

```java
TypeField field = Diamond.of(PublicMembersTestObject.class)
  .fields().named("field")
  .unique().get();

field.nativeType().get()
```
Outputs the native java.lang.Field instance that represents the same field
```java
"public int ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject.field"
```
  

### Field used as lambda
Every field can be treated as different types of lambdas to get or set the
value that it holds on an instance or a class. 

#### How to use a static field as a getter Supplier: `TypeField#get()`
This can only be used for static fields because instance fields require
an additional argument. 

```java
TypeField typeField = Diamond.of(FunctionalFieldTestObject.class)
  .fields().named("staticField")
  .unique().get();

typeField.get()
```
Outputs the value of the static field for the given class
```java
3
```

#### How to use an instance field as a getter Function: `TypeField#apply()`
This can be used for static or instance fields. Static fields accept null as the 
instance.

```java
FieldAccessorTestObject object = ...;

TypeField typeField = Diamond.of(FieldAccessorTestObject.class)
  .fields().named("privateField")
  .unique().get();

typeField.apply(object)
```
Outputs the value of the field for the given object
```java
23
```

#### How to use a static field as a setter consumer: `TypeField#accept()`
This can only be used for static fields because instance fields require
an additional argument.
 
```java
TypeField typeField = Diamond.of(FunctionalFieldTestObject.class)
  .fields().named("staticField")
  .unique().get());

typeField.accept(23)
```
Which is equivalent of doing `FunctionalFieldTestObject.staticField = 23` 
 
#### How to use a field as a setter bi-consumer: `TypeField#accept()`
This can be used with static fields too. Pass null as the object.

```java
FieldAccessorTestObject object = ...;

TypeField typeField = Diamond.of(FieldAccessorTestObject.class)
  .fields().named("privateField")
  .unique().get());

typeField.setValueOn(object, 23)
```
Which is equivalent of doing `object.publicField = 23`


### Binding a field to an instance: `BoundField`
A type field can be bound to an instance so it's not necessary to indicate it
everytime.

#### How to bind an instance to a type field: `TypeField#bindTo()`
```java
BoundFieldTestObject object = ...;

TypeField typeField = Diamond.of(BoundFieldTestObject.class)
  .fields().named("field")
  .unique().get();

typeField.bindTo(object)
```
Outputs the bound field which has the implicit object
```java
field @@ BoundFieldTestObject instance
```

#### Hot to get the value of bound field: `BoundField#get()`
This also allows the bound field to be used as a getter Supplier.

```java
BoundFieldTestObject object = ...;

BoundField boundField = Diamond.of(BoundFieldTestObject.class)
  .fields().named("field")
  .unique().get()
  .bindTo(object);

boundField.get()
```
Outputs the bound field which has the implicit object
```java
42
```  

#### How to set the value on a bound field: `BoundField#set()`
The bound field has an alias for this method that makes it
useful as a setter consumer with `accept()`.
   
```java
BoundFieldTestObject object = ...;

BoundField boundField = Diamond.of(BoundFieldTestObject.class)
  .fields().named("field")
  .unique().get()
  .bindTo(object);

boundField.set(42)
```   
Which sets the value of the field to `42` in that instance


