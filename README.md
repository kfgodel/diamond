Diamond
==============
Where your reflection shines better  

A Java project with an alternative API to do reflection on java types 
in order to reduce verbosity, and offer a simple dsl for common cases.   
It also adds some operations not possible with native reflection

Status: `Experimental`

## API documentation
Every important method can be explored in the **[Full API guide here](wiki/api_guide.md)**

### Code Example
Every diamond abstraction over reflection is created with `Diamond.of()` which
receives a native reflection object and adds a DSL over it.

Here are some examples on accessing fields, methods, and invoking them for a class 

```java
  it("adds a dsl over reflection API", () -> {
    final TypeInstance objectType = Diamond.of(Object.class);
    assertThat(objectType.names().canonicalName()).isEqualTo("java.lang.Object");
  });

  it("allows creating new instances without hassle", () -> {
    final Object createdObject = Diamond.of(Object.class)
      .newInstance();
    assertThat(createdObject).isNotNull();
  });

  it("offers a simplified no try-catch way to access class fields", () -> {
    final List<String> namesOfStringFields = Diamond.of(String.class)
      .fields().all()
      .map(TypeField::name)
      .collect(Collectors.toList());
    assertThat(namesOfStringFields).containsExactlyInAnyOrder(
      "value",
      "hash",
      "serialVersionUID",
      "serialPersistentFields",
      "CASE_INSENSITIVE_ORDER"
    );
  });

  it("offers a simplified no try-catch way to access class methods", () -> {
    final List<String> namesOfObjectMethods = Diamond.of(Object.class)
      .methods().all()
      .map(TypeMethod::name)
      .collect(Collectors.toList());
    assertThat(namesOfObjectMethods).containsExactlyInAnyOrder(
      "finalize",
      "wait",
      "wait",
      "wait",
      "equals",
      "toString",
      "hashCode",
      "getClass",
      "clone",
      "registerNatives",
      "notify",
      "notifyAll"
    );
  });

  it("offers a simplified way of calling constructors and methods",()->{
    final TypeConstructor constructor = Diamond.of(String.class)
      .constructors().withNativeParameterTypes(byte[].class)
      .unique().get();
    final String createdString = (String) constructor
      .invoke(new byte[0]);
    assertThat(createdString).isEmpty();
  });
```

## Design principles
Working on this project I have tried to follow these design decisions in order 
to offer an intuitive and consistent API:

- Don't use checked exceptions. Convert them to an specific runtime one:  
  Most of the time, when working with reflection, you don't want to be bothered 
  with security access, unlinked classes, missing methods, etc. And, if it fails, 
  it's probably going to be a runtime problem. That allows you to avoid try-catching
  every time reflection is needed 
  
- Use [Nary](https://github.com/kfgodel/nary) for returned elements:  
  To reduce the number of available methods, but at the same time provide a
  flexible API I have used Nary when a method returns elements.
  This allows me to offer a single return type but you can treat it as 
  an Optional or Stream at compile time.
  Because I don't know the classes you will be working on, the number of methods
  or fields may be predictable to you at compile time but not for me.
  
- Reify the 2 hierarchies of a Java type: Compile & Runtime  
  Java has 2 Type systems living in it. One that you see at compile time and probably
  think of it when coding, and the one that is available at runtime.  
  For example, there are no instances of List<String> at runtime, just List.
  Regardless of what the compiler complains about when you assign it to a 
  List<Integer> variable, at run time that's perfectly a legal operation.  
  To deal with this 2 type systems I reified the compile time type hierarchy 
  and the runtime hierarchy offering you access to both.  
  However I prioritized the compile time type system over the runtime whenever
  I had to.  
  
  