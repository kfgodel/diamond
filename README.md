Diamond
==============

Where your reflection shines better  

A Java project that redefines the way to do reflection on java classes to reduce
the amount of verbosity needed and attempt to offer a simple dsl for common operations

Status: Experimental

### Code Example
Every diamond abstraction over reflection is created with `Diamond.of()` which
receives a native reflection object and adds a DSL over it.

Here are some examples on accessing fields, methods, and invoking them for a class 

```java
  it("adds a dsl over reflection API", () -> {
    final TypeInstance objectType = Diamond.of(Object.class);
    assertThat(objectType.names().canonicalName()).isEqualTo("java.lang.Object");
  });

  it("allows creating new instances easily", () -> {
    final Object createdObject = Diamond.of(Object.class)
      .newInstance();
    assertThat(createdObject).isNotNull();
  });

  it("offers a simplified no-checked-exception way to access class fields", () -> {
    final List<String> namesOfObjectFields = Diamond.of(Object.class)
      .fields().all()
      .map(TypeField::name)
      .collect(Collectors.toList());
    assertThat(namesOfObjectFields).isEmpty();
  });

  it("offers a simplified no-checked-exception way to access class methods", () -> {
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
    final String createdString = (String) Diamond.of(String.class)
      .constructors().withNativeParameters(byte[].class)
      .get()
      .invoke(new byte[0]);
    assertThat(createdString).isEmpty();
  });
```

### Design principles
Working on this project I have tried to follow some rules in order to offer an
intuitive API and be consistent:

- Don't use checked exceptions, convert them to an specific runtime one:  
  Most of the time, when working with reflection, you don't want to be bothered 
  with security access, unlinked classes, missing methods, etc. And if it fails 
  it's probably going to be a run time failure, which is not possible to handle at 
  compile time design
  
- [Nary](https://github.com/kfgodel/nary) is used to provide flexible api:  
  Because we don't know the classes you will be working on, the number of methods
  may be predictable to you at compile time but not for me.  So in order to provide 
  you with an API that allows you to have a single method in a class and multiple
  in others, I use Nary all over, instead of Optional or Streams.  
  
- Generics: Compile vs Runtime  
  Java has 2 Type systems living in it. One that you see at compile time and probably
  think of it when coding, and the one that is available at runtime.  
  For instance, List<String> is not a type that exists at runtime, and regardless of
  what the compiler complains when you assign it to List<Integer>, at run time that's
  perfectly a legal move.  
  To cope with this contradiction I prioritized the compile time type system but I 
  tried to offer some possibilities (like `runtimeType()`) to allow accessing the 
  underlying runtime type system.  