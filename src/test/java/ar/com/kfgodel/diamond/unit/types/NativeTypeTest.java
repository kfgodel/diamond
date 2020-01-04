package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.nary.api.Unary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies that a native type is retrievable from certain types
 * Created by kfgodel on 20/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class NativeTypeTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a native type", () -> {

      it("can be obtained from classes", () -> {
        TypeInstance typeInstance = Diamond.of(PublicMembersTestObject.class);

        Class<?> rawClass = typeInstance.runtime().classes().asUni().get();

        assertThat(rawClass).isEqualTo(PublicMembersTestObject.class);
      });

      it("can be obtained from methods", () -> {
        TypeMethod method = Diamond.of(PublicMembersTestObject.class).methods().named("method").asUni().get();

        Unary<Method> nativeMethod = method.nativeType();

        assertThat(nativeMethod.isPresent()).isTrue();
        assertThat(nativeMethod.asUni().get().getName()).isEqualTo("method");
      });

      it("can be obtained from fields", () -> {
        TypeField field = Diamond.of(PublicMembersTestObject.class).fields().named("field").asUni().get();

        Unary<Field> nativeField = field.nativeType();

        assertThat(nativeField.isPresent()).isTrue();
        assertThat(nativeField.asUni().get().getName()).isEqualTo("field");
      });

      it("can be obtained from constructors", () -> {
        TypeConstructor constructor = Diamond.of(PublicMembersTestObject.class).constructors().niladic().get();

        Unary<Constructor> nativeConstructor = constructor.nativeType();

        assertThat(nativeConstructor.isPresent()).isTrue();
      });
    });

  }
}