package ar.com.kfgodel.diamond.unit.sources;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.ClassWithIdField;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.nary.api.Nary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests ways to access diamond instances
 * Created by kfgodel on 17/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class DiamondSourcesTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {

    describe("diamond has its own reflection types", () -> {

      describe("types", () -> {
        it("can be obtained from Class instances", () -> {
          TypeInstance diamondClass = Diamond.types().from(Object.class);
          assertThat(diamondClass.name()).isEqualTo("Object");
        });
        it("can be obtained from an array of type instances", () -> {
          final Nary<TypeInstance> typeInstances = Diamond.types().from(new Object[]{Object.class, String.class, List.class});
          final List<String> typeNames = typeInstances
            .map(Named::name)
            .collectToList();
          assertThat(typeNames).containsExactly("Object","String","List");
        });
        it("can be obtained from type references", () -> {
          TypeInstance diamondClass = Diamond.types().from(new ReferenceOf<List<String>>() {});
          assertThat(diamondClass.name()).isEqualTo("List");
        });
        it("can be obtained from complete class names", () -> {
          TypeInstance diamondClass = Diamond.types().named("java.lang.Object");
          assertThat(diamondClass.name()).isEqualTo("Object");
        });
        it("has a special shortcut", () -> {
          TypeInstance diamondClass = Diamond.of(Object.class);
          assertThat(diamondClass.name()).isEqualTo("Object");
        });
        it("has a shortcut for multiple instances",()->{
          final TypeInstance[] typeInstances = Diamond.ofNative(Object.class, String.class, List.class);
          final List<String> typeNames = Arrays.stream(typeInstances)
            .map(Named::name)
            .collect(Collectors.toList());
          assertThat(typeNames).containsExactly("Object", "String", "List");
        });
        it("can be obtained from a generic method", () -> {
          TypeInstance diamondRepresentation = Diamond.from(Object.class);
          assertThat(diamondRepresentation.name()).isEqualTo("Object");
        });
        itThrows(IllegalArgumentException.class, "if null is used as a native type", ()->{
          Diamond.types().from((Object)null);
        }, e->{
          assertThat(e).hasMessage("There's no Diamond type instance for null");
        });
        itThrows(IllegalArgumentException.class, "if null is used as an array of native types", ()->{
          Diamond.types().from((Object[])null);
        }, e->{
          assertThat(e).hasMessage("The native type array can't be null");
        });
      });
      describe("methods", () -> {
        it("can be obtained from Method instances", () -> {
          Method methodInstance = null;
          try {
            methodInstance = Object.class.getMethod("equals", Object.class);
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          TypeMethod diamondMethod = Diamond.methods().from(methodInstance);
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });

        it("can be obtained from a Class instance and a method name", () -> {
          TypeMethod diamondMethod = Diamond.methods()
            .in(Object.class)
            .named("equals")
            .unique().get();
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });

        it("can be obtained from a Class instance, a method name and its diamond parameters", () -> {
          TypeMethod diamondMethod = Diamond.methods()
            .in(Object.class)
            .withSignature("equals", Diamond.ofNative(Object.class))
            .unique().get();
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });

        it("can be obtained from a Class instance, a method name and its native parameters", () -> {
          TypeMethod diamondMethod = Diamond.methods()
            .in(Object.class)
            .withNativeSignature("equals", Object.class)
            .unique().get();
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });

        it("can be obtained from a Class and its diamond parameter types", () -> {
          TypeMethod diamondMethod = Diamond.methods()
            .in(Object.class)
            .withParameterTypes(Diamond.of(Object.class))
            .unique().get();
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });

        it("can be obtained from a Class and its native parameter types", () -> {
          TypeMethod diamondMethod = Diamond.methods()
            .in(Object.class)
            .withNativeParameterTypes(Object.class)
            .unique().get();
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });
        it("can be obtained from a generic method", () -> {
          Method methodInstance = null;
          try {
            methodInstance = Object.class.getMethod("equals", Object.class);
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          TypeMethod diamondMethod = Diamond.from(methodInstance);
          assertThat(diamondMethod.name()).isEqualTo("equals");
        });
      });
      describe("fields", () -> {
        it("can be obtained from Field instances", () -> {
          Field fieldInstance = null;
          try {
            fieldInstance = ClassWithIdField.class.getDeclaredField("id");
          } catch (NoSuchFieldException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          TypeField diamondField = Diamond.fields().from(fieldInstance);
          assertThat(diamondField.name()).isEqualTo("id");
        });
        it("can be obtained from a Class instance and a field name", () -> {
          TypeField diamondField = Diamond.fields()
            .in(ClassWithIdField.class)
            .named("id")
            .unique().get();
          assertThat(diamondField.name()).isEqualTo("id");
        });
        it("can be obtained from a generic method", () -> {
          Field fieldInstance = null;
          try {
            fieldInstance = ClassWithIdField.class.getDeclaredField("id");
          } catch (NoSuchFieldException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          TypeField diamondField = Diamond.from(fieldInstance);
          assertThat(diamondField.name()).isEqualTo("id");
        });
      });

      describe("constructors", () -> {
        it("can be obtained from Constructor instances", () -> {
          Constructor<PublicMembersTestObject> constructor = null;
          try {
            constructor = PublicMembersTestObject.class.getDeclaredConstructor();
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          TypeConstructor diamondConstructor = Diamond.constructors().from(constructor);
          assertThat(diamondConstructor.name()).endsWith("PublicMembersTestObject");
        });
        it("can be obtained from a Class instance and diamond parameter types", () -> {
          TypeConstructor diamondConstructor = Diamond.constructors()
            .in(PublicMembersTestObject.class)
            .withParameterTypes(Diamond.ofNative(Integer.class))
            .unique().get();
          assertThat(diamondConstructor.name()).endsWith("PublicMembersTestObject");
        });

        it("can be obtained from a Class instance and native parameter types", () -> {
          TypeConstructor diamondConstructor = Diamond.constructors()
            .in(PublicMembersTestObject.class)
            .withNativeParameterTypes(Integer.class)
            .unique().get();
          assertThat(diamondConstructor.name()).endsWith("PublicMembersTestObject");
        });
        it("can be obtained from a generic method", () -> {
          Constructor<PublicMembersTestObject> constructor = null;
          try {
            constructor = PublicMembersTestObject.class.getDeclaredConstructor();
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          TypeConstructor diamondConstructor = Diamond.from(constructor);
          assertThat(diamondConstructor.name()).endsWith("PublicMembersTestObject");
        });
      });

      describe("packages", () -> {

        it("can be ontained from a package instances", () -> {
          Package nativePackage = Package.getPackage("java.lang");

          TypePackage aPackage = Diamond.packages().from(nativePackage);

          assertThat(aPackage).isNotNull();
        });

        it("can be obtained from a name", () -> {
          TypePackage aPackage = Diamond.packages().named("java.lang");

          assertThat(aPackage).isNotNull();
        });
        it("can be obtained from a generic method", () -> {
          Package nativePackage = Package.getPackage("java.lang");

          TypePackage aPackage = Diamond.from(nativePackage);

          assertThat(aPackage).isNotNull();
        });
      });

      describe("parameters", () -> {

        it("can be obtained from a native parameter instance", () -> {
          Method method = null;
          try {
            method = Object.class.getDeclaredMethod("equals", Object.class);
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          Parameter nativeParameter = method.getParameters()[0];

          ExecutableParameter parameter = Diamond.parameters().from(nativeParameter);

          assertThat(parameter.declaredType().name()).isEqualTo("Object");
        });

        it("can be obtained from a generic method", () -> {
          Method method = null;
          try {
            method = Object.class.getDeclaredMethod("equals", Object.class);
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }
          Parameter nativeParameter = method.getParameters()[0];

          ExecutableParameter parameter = Diamond.from(nativeParameter);

          assertThat(parameter.declaredType().name()).isEqualTo("Object");
        });
      });

      describe("modifiers", () -> {
        it("can be obtained from a class member", () -> {
          Method method = null;
          try {
            method = Object.class.getDeclaredMethod("equals", Object.class);
          } catch (NoSuchMethodException e) {
            throw new RuntimeException("This is why reflection api turns difficult to use", e);
          }

          Nary<Modifier> methodModifiers = Diamond.modifiers().from(method);
          assertThat((Stream)methodModifiers).isEqualTo(Lists.newArrayList(Modifiers.PUBLIC));
        });
        it("can be obtained from an int bitmap", () -> {
          Nary<Modifier> methodModifiers = Diamond.modifiers().fromMember(java.lang.reflect.Modifier.PUBLIC | java.lang.reflect.Modifier.ABSTRACT);
          assertThat((Stream)methodModifiers).isEqualTo(Lists.newArrayList(Modifiers.PUBLIC, Modifiers.ABSTRACT));
        });
      });


      describe("lambdas", () -> {
        it("can be obtained from inline expressions", () -> {

          Lambda lambda = Diamond.lambdas().fromSupplier(() -> 3);

          assertThat(lambda.parameters().count()).isEqualTo(0);
        });
        it("can be obtained from a method reference", () -> {
          Lambda lambda = Diamond.lambdas().fromSupplier(DiamondSourcesTest::method);

          assertThat(lambda.parameters().count()).isEqualTo(0);
        });
      });

    });
  }

  public static int method() {
    return 3;
  }
}
