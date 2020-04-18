# Diamond API for type members
This doc shows examples of API usage showcasing some common use cases to 
get information about a type characteristics

#### How to know the package where a type is declared: `TypeInstance#declaredPackage()`
Equivalent to `java.lang.Class#getPackage()` 
 
 ```java
TypeInstance typeInstance = Diamond.of(ChildClass.class);
 
typeInstance.declaredPackage().get()
```
 Outputs the package instance for that class  
 ```java
 ar.com.kfgodel.diamond.unit.testobjects.lineage
 ```

#### How to get a string representation of the type: `TypeInstance#declaration()`
Added by Diamond, this allows you to get a String with all the metadata 
 
 ```java
ReferenceOf<?> annotatedType = new ReferenceOf<@TestAnnotation2 List<Integer> @TestAnnotation1 []>() {};
TypeInstance typeInstance = Diamond.types().from(annotatedType);
 
typeInstance.declaration()
```
 Outputs the string representing the type as closest as we can  
 ```java
 "@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.util.List<java.lang.Integer> @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() []"
 ```

#### How to know the categories a type belongs to: `TypeInstance#categories()`
Added by Diamond a type can belong to multiple categories, some represent native concepts
as interfaces, some are added like NUMERIC category for types that represent numbers 
 
 ```java
TypeInstance typeInstance = Diamond.of(List.class);
 
typeInstance.categories().collectToList()
```
 Outputs the categories the class belongs to  
 ```java
 [REFERENCE, INTERFACE, CONTAINER]
 ```
Notice that a type that belongs to `INTERFACE` is equivalent of having `java.lang.Class#isInterface()` 
equal true. However `REFERENCE` and `CONTAINER` are categories added by Diamond
meaning that the type is a referential type (not a value), and it represents
a container (some form of collection).

#### How to know the component type of an array type: `TypeInstance#componentType()`
Equivalent to `java.lang.Class#getComponentType()` 
 
 ```java
TypeInstance typeInstance = Diamond.of(String[].class);
 
typeInstance.componentType().get()
```
 Outputs the type instance representing the String class  
 ```java
 "String @ java.lang"
 ```

#### How to get the native reflection instance from a type: `TypeInstance#reflectedAs()`
Because native reflection uses different unrelated hierarchies to represents
all possible types you need to know what subtype you are dealing with 
 
 ```java
TypeInstance typeInstance = Diamond.of(ChildClass.class);
 
typeInstance.reflectedAs(Class.class).get()
```
 Outputs native Class instance that represents the type  
 ```java
 "class ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass"
 ```