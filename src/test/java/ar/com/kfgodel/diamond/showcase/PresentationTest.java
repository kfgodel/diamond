package ar.com.kfgodel.diamond.showcase;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.showcase.objects.ChildClass;
import ar.com.kfgodel.diamond.showcase.objects.TestObject;
import ar.com.kfgodel.nary.api.Nary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * This class verifies behavior shown on presentation
 * Date: 25/4/20 - 16:36
 */
@RunWith(JavaSpecRunner.class)
public class PresentationTest extends JavaSpec<PresentationTestContext> {
  @Override
  public void define() {
    describe("the presentation", () -> {

      describe("regarding fields", () -> {
        it("shows how to access a field value with native reflection", () -> {
          Field idField = null;
          try {
            idField = TestObject.class.getDeclaredField("id");
          } catch (NoSuchFieldException e) {
            fail("handle exception while getting field", e);
          }

          final TestObject object = new TestObject(4);

          //Because it's a private field
          idField.setAccessible(true);

          Object fieldValue = null;
          try {
            fieldValue = idField.get(object);
          } catch (IllegalAccessException e) {
            fail("handle exception while getting value", e);
          }

          assertThat(fieldValue).isEqualTo(4);
        });

        it("shows how to get a field value with Diamond", () -> {
          final TypeField idField = Diamond.of(TestObject.class)
            .fields().named("id")
            .unique().get();

          final TestObject object = new TestObject(4);

          final Object fieldValue = idField.getValueFrom(object);
          assertThat(fieldValue).isEqualTo(4);
        });
      });

      describe("regarding methods", () -> {
        it("shows how to invoke a method with native reflection", () -> {
          final Method[] methods = TestObject.class.getMethods();
          Method setterMethod = null;
          for (Method candidateMethod : methods) {
            if (candidateMethod.getName().equals("setFieldA")) {
              setterMethod = candidateMethod;
              break;
            }
          }

          final TestObject object = new TestObject(4);

          try {
            setterMethod.invoke(object, "new value for field A");
          } catch (IllegalAccessException e) {
            fail("handle permission exception before invoking", e);
          } catch (InvocationTargetException e) {
            fail("handle exception during invocation", e);
          }

          assertThat(object.getFieldA()).isEqualTo("new value for field A");
        });

        it("shows how to invoke a method with Diamond", () -> {
          final TypeMethod setterMethod = Diamond.of(TestObject.class)
            .methods().named("setFieldA")
            .unique().get();

          final TestObject object = new TestObject(4);

          setterMethod.invokeOn(object, "new value for field A");

          assertThat(object.getFieldA()).isEqualTo("new value for field A");
        });

        it("shows how to bind a method to an instance with Diamond", () -> {
          final TypeMethod setter = Diamond.of(TestObject.class)
            .methods().named("setFieldA")
            .unique().get();

          // Bind to an implicit receiver
          final TestObject object = new TestObject(1);
          final BoundMethod boundSetter = setter.bindTo(object);

          boundSetter.invoke("first value");
          assertThat(object.getFieldA()).isEqualTo("first value");

          boundSetter.invoke("second value");
          assertThat(object.getFieldA()).isEqualTo("second value");
        });

        it("shows how to bind the arguments of a method with Diamond", () -> {
          final TypeMethod setter = Diamond.of(TestObject.class)
            .methods().named("setFieldA")
            .unique().get();

          // Bind to implicit arguments
          final BehaviorCall boundArguments = setter.withArguments("implicit argument");

          final TestObject firstObject = new TestObject(1);
          boundArguments.invokeOn(firstObject);

          final TestObject secondObject = new TestObject(2);
          boundArguments.invokeOn(secondObject);

          assertThat(firstObject.getFieldA()).isEqualTo("implicit argument");
          assertThat(secondObject.getFieldA()).isEqualTo("implicit argument");
        });
      });

      describe("regarding constructors", () -> {
        it("shows how to instantiate from non niladic constructor using reflection", () -> {
          Constructor<TestObject> constructor = null;
          try {
            constructor = TestObject.class.getConstructor(int.class);
          } catch (NoSuchMethodException e) {
            fail("handle missing constructor", e);
          }

          TestObject object = null;
          try {
            object = constructor.newInstance(5);
          } catch (InstantiationException e) {
            fail("handle exception for abstract classes", e);
          } catch (IllegalAccessException e) {
            fail("handle permission exception", e);
          } catch (InvocationTargetException e) {
            fail("handle exception inside constructor", e);
          }

          assertThat(object.getId()).isEqualTo(5);
        });

        it("shows how to instantiate with Diamond", () -> {
          final TypeConstructor constructor = Diamond.of(TestObject.class)
            .constructors().withNativeParameterTypes(int.class)
            .unique().get();

          final TestObject object = constructor.invoke(5);

          assertThat(object.getId()).isEqualTo(5);
        });

      });

      describe("regarding type relationships", () -> {
        it("shows how test subtype relationships with native reflection", () -> {
          final boolean objectFromString = Object.class.isAssignableFrom(String.class);
          assertThat(objectFromString).isTrue();

          final boolean serializableFromObject = Serializable.class.isAssignableFrom(String.class);
          assertThat(serializableFromObject).isTrue();

          final ParameterizedType listOfStrings = new ReferenceOf<List<String>>() {
          }.getReferencedParameterizedType();
          final Type rawListType = listOfStrings.getRawType();
          final boolean collectionFromListOfStrings = Collection.class.isAssignableFrom((Class) rawListType);
          assertThat(collectionFromListOfStrings).isTrue();
        });

        it("shows how test subtype relationships with Diamond", () -> {
          final boolean objectFromString = Diamond.of(Object.class)
            .is().assignableFrom(Diamond.of(String.class));
          assertThat(objectFromString).isTrue();

          final boolean serializableFromObject = Diamond.of(String.class)
            .is().assignableTo(Diamond.of(Serializable.class));
          assertThat(serializableFromObject).isTrue();

          final boolean collectionFromListOfStrings = Diamond.types().from(new ReferenceOf<List<String>>() {
          })
            .is().assignableTo(Diamond.of(Collection.class));
          assertThat(collectionFromListOfStrings).isTrue();
        });

      });

      describe("regarding generics", () -> {
        it("shows how get the actual argument of a parameterized type with native reflection", () -> {
          final Type mapType = new ReferenceOf<Map<String, Long>>() {
          }.getReferencedType();

          ParameterizedType parameterizedType = (ParameterizedType) mapType;
          final Type secondArgument = parameterizedType.getActualTypeArguments()[1];

          assertThat(secondArgument).isEqualTo(Long.class);
        });

        it("shows how get the actual argument of a parameterized type with Diamond", () -> {
          final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<String, Long>>() {
          });

          final TypeInstance secondArgument = mapType.generic().arguments()
            .skip(1)
            .findFirst().get();

          final AnnotatedType actual = secondArgument.reflectedAs(AnnotatedType.class).get();
          assertThat(actual.getType()).isEqualTo(Long.class);
        });

        it("shows how to get the bounds of a type variable with native reflection", () -> {
          // We use a method to get a type variable
          Method parameterizedMethod = null;
          try {
            parameterizedMethod = PresentationTest.class.getDeclaredMethod("parameterizedMethod", Number.class);
          } catch (NoSuchMethodException e) {
            fail("handle misnamed method exception", e);
          }

          final TypeVariable<Method> typeVariable = parameterizedMethod.getTypeParameters()[0];

          final Type upperBound = typeVariable.getBounds()[0];

          assertThat(upperBound.getTypeName()).isEqualTo("java.lang.Number");
        });
        it("shows hot to get the bounds of a type variable with Diamond", () -> {
          final TypeMethod parameterizedMethod = Diamond.methods().in(PresentationTest.class)
            .named("parameterizedMethod")
            .unique().get();

          final TypeInstance typeVariable = parameterizedMethod.generic().parameters()
            .findFirst().get();

          final TypeInstance upperBound = typeVariable.generic().bounds()
            .upper().findFirst().get();

          assertThat(upperBound.name()).isEqualTo("Number");
        });

        it("shows how to get the bounds of a wildcard with native reflection", () -> {
          // We use a map type to capture 2 wildcards (one upper bound, and the other lower bound)
          final ParameterizedType mapType = new ReferenceOf<Map<? extends Number, ? super String>>() {
          }.getReferencedParameterizedType();

          final Type firstWildcard = mapType.getActualTypeArguments()[0];
          final Type upperBound = ((WildcardType) firstWildcard).getUpperBounds()[0];

          final Type secondWildcard = mapType.getActualTypeArguments()[1];
          final Type lowerBound = ((WildcardType) secondWildcard).getLowerBounds()[0];

          assertThat(upperBound.getTypeName()).isEqualTo("java.lang.Number");
          assertThat(lowerBound.getTypeName()).isEqualTo("java.lang.String");
        });

        it("shows hot to get the bounds of a wildcard with Diamond", () -> {
          // We use a map to capture 2 wildcards
          final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<? extends Number, ? super String>>() {
          });

          final TypeInstance firstWildcard = mapType.generic().arguments().findFirst().get();
          final TypeInstance upperBound = firstWildcard.generic().bounds()
            .upper().findFirst().get();

          final TypeInstance secondWildcard = mapType.generic().arguments().skip(1).findFirst().get();
          final TypeInstance lowerBound = secondWildcard.generic().bounds()
            .lower().findFirst().get();

          assertThat(upperBound.name()).isEqualTo("Number");
          assertThat(lowerBound.name()).isEqualTo("String");
        });

      });

      describe("regarding hierarchy", () -> {
        it("shows how to get implemented interfaces with native reflection", () -> {
          final Class<ChildClass> childClass = ChildClass.class;

          // Directly implemented
          final List<Type> childInterfaces = Arrays.asList(childClass.getGenericInterfaces());
          // ... Iteration required until top of hierarchy
          final List<Type> parentInterfaces = Arrays.asList(childClass.getSuperclass().getGenericInterfaces());
          final List<Type> grandparentInterfaces = Arrays.asList(childClass.getSuperclass().getSuperclass().getGenericInterfaces());
          // ...

          final List<Type> interfaces = new ArrayList<>();
          interfaces.addAll(childInterfaces);
          interfaces.addAll(parentInterfaces);
          interfaces.addAll(grandparentInterfaces);

          final List<String> interfaceNames = interfaces.stream()
            .map(Type::getTypeName)
            .collect(Collectors.toList());
          assertThat(interfaceNames).containsExactly(
            "java.util.NavigableMap<java.lang.Integer, java.lang.String>",
            "java.util.SortedMap<K, java.lang.String>",
            "java.util.Map<K, V>"
          );
        });

        it("shows how to get implemented interfaces with native reflection", () -> {
          final TypeInstance childType = Diamond.of(ChildClass.class);
          final List<TypeInstance> interfaces = childType
            .hierarchy().lineage().allRelatedTypes()
            .filter(type -> type.is().a(Categories.INTERFACE))
            .collectToList();

          final List<String> interfaceDaclarations = interfaces.stream()
            .map(TypeInstance::declaration)
            .collect(Collectors.toList());
          assertThat(interfaceDaclarations).containsExactly(
            "java.util.NavigableMap<java.lang.Integer, java.lang.String>",
            "java.util.NavigableMap",
            "java.util.SortedMap<java.lang.Integer, java.lang.String>",
            "java.util.SortedMap",
            "java.util.Map<java.lang.Integer, java.lang.String>",
            "java.util.Map"
          );
        });

        it("shows there's no way to get actual type argument of supertype with native reflection", () -> {
          final Class<SortedMap> paremetrizableType = SortedMap.class;

          Type[] actualArguments = null;
          final Type[] parentInterfaces = ChildClass.class
            .getSuperclass().getGenericInterfaces();
          for (Type parentInterface : parentInterfaces) {
            if (!(parentInterface instanceof ParameterizedType)) {
              continue;
            }
            ParameterizedType parameterizedInterface = (ParameterizedType) parentInterface;
            if (parameterizedInterface.getRawType().equals(paremetrizableType)) {
              actualArguments = parameterizedInterface.getActualTypeArguments();
              break;
            }
          }

          final TypeVariable firstTypeArgument = (TypeVariable) actualArguments[0];
          // Outputs: K

          assertThat(firstTypeArgument.getName()).isEqualTo("K");
        });

        it("shows how to get actual type argument of supertype with Diamond", () -> {
          final TypeInstance parametrizableType = Diamond.of(SortedMap.class);
          final Nary<TypeInstance> actualArguments = Diamond.of(ChildClass.class)
            .hierarchy().lineage()
            .genericArgumentsOf(parametrizableType);
          final TypeInstance firstTypeArgument = actualArguments
            .findFirst().get();
          // Outputs: Integer

          assertThat(firstTypeArgument.name()).isEqualTo("Integer");
        });
      });

      describe("regarding lambdas", () -> {
        it("shows how to get reflection on lambdas", () -> {
          final Lambda lambda = Diamond.lambdas().fromSupplier(() -> "a string ");
          final long parameterCount = lambda.parameters().count();
          final TypeInstance returnType = lambda.returnType();

          assertThat(parameterCount).isEqualTo(0);
          assertThat(returnType.name()).isEqualTo("Object");
        });
      });

      it("shows how can reflection be used as lambdas", () -> {
        // Class as supplier
        Supplier<Object> supplier = Diamond.of(String.class);
        assertThat(supplier.get()).isEqualTo("");

        // Constructor with arg as function
        Function<Object, Object> function = Diamond.of(TestObject.class)
          .constructors().withNativeParameterTypes(int.class)
          .unique().get();
        final TestObject object = (TestObject) function.apply(10);
        assertThat(object.getId()).isEqualTo(10);

        // Setter as a biconsumer
        BiConsumer<Object, Object> biConsumer = Diamond.of(TestObject.class)
          .methods().named("setFieldA")
          .unique().get();

        final TestObject anObject = new TestObject(5);
        biConsumer.accept(anObject, "field value");
        assertThat(anObject.getFieldA()).isEqualTo("field value");

        // Field as a function
        Function<Object, Object> getter = Diamond.of(TestObject.class)
          .fields().named("id")
          .unique().get();

        final List<TestObject> objects = Lists.newArrayList(new TestObject(1), new TestObject(2), new TestObject(3));
        final List<Object> ids = objects
          .stream()
          .map(getter)
          .collect(Collectors.toList());
        assertThat(ids).containsExactly(1, 2, 3);

      });

    });

  }

  public <T1 extends Number> void parameterizedMethod(T1 argument) {
  }
}