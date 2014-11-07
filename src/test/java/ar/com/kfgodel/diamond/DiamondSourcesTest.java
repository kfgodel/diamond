package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.packages.TypePackage;
import ar.com.kfgodel.diamond.testobjects.ClassWithIdField;
import ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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

            describe("classes", () -> {
                it("can be obtained from Class instances", () -> {
                    TypeInstance diamondClass = Diamond.types().from(Object.class);
                    assertThat(diamondClass.name()).isEqualTo("Object");
                });
                it("can be obtained from complete class names", () -> {
                    TypeInstance diamondClass = Diamond.types().named("java.lang.Object");
                    assertThat(diamondClass.name()).isEqualTo("Object");
                });
                it("have a special shortcut", ()->{
                    TypeInstance diamondClass = Diamond.of(Object.class);
                    assertThat(diamondClass.name()).isEqualTo("Object");
                });
            });
            describe("methods", ()->{
                it("can be obtained from Method instances", ()->{
                    Method methodInstance = null;
                    try {
                        methodInstance = Object.class.getMethod("equals", Object.class);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("This is why reflection api turns difficult to use",e);
                    }
                    TypeMethod diamondMethod = Diamond.methods().from(methodInstance);
                    assertThat(diamondMethod.name()).isEqualTo("equals");
                });

                it("can be obtained from a Class instance and a method name", () -> {
                    TypeMethod diamondMethod = Diamond.methods().in(Object.class).named("equals").get();
                    assertThat(diamondMethod.name()).isEqualTo("equals");
                });

                it("can be obtained from a Class instance, a method name and its parameters",()->{
                    TypeMethod diamondMethod = Diamond.methods().in(Object.class).withSignature("equals", Diamond.ofNative(Object.class)).get();
                    assertThat(diamondMethod.name()).isEqualTo("equals");
                });
                
                it("can be obtained from a Class and its parameter types",()->{
                    TypeMethod diamondMethod = Diamond.methods().in(Object.class).withParameters(Diamond.ofNative(Object.class)).get();
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
                    TypeField diamondField = Diamond.fields().in(ClassWithIdField.class).named("id").get();
                    assertThat(diamondField.name()).isEqualTo("id");
                });
            });

            describe("constructors", ()->{
                it("can be obtained from Constructor instances", () -> {
                    Constructor<PublicMembersTestObject> constructor = null;
                    try {
                        constructor = PublicMembersTestObject.class.getDeclaredConstructor();
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("This is why reflection api turns difficult to use", e);
                    }
                    TypeConstructor diamondConstructor = Diamond.constructors().from(constructor);
                    assertThat(diamondConstructor.name()).isEqualTo("ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject");
                });
                it("can be obtained from a Class instance and a constructor parameter types", () -> {
                    TypeConstructor diamondConstructor = Diamond.constructors()
                            .in(PublicMembersTestObject.class).withParameters(Diamond.ofNative(Integer.class)).get();
                    assertThat(diamondConstructor.name()).isEqualTo("ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject");
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
            });

            describe("parameters", () -> {
                
                it("can be obtained from a native parameter instance",()->{
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
                
            });


        });

    }
}
