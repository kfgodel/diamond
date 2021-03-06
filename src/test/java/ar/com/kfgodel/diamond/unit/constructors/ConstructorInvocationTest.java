package ar.com.kfgodel.diamond.unit.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.HaltedConstructorInvocationException;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.constructors.ExceptionConstructorTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.DefaultMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PrivateMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.ProtectedMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior of constructor invocation
 * Created by kfgodel on 25/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class ConstructorInvocationTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("TypeConstructor as instance creator", () -> {

      context().typeInstance(() -> Diamond.of(context().testClass()));
      context().constructor(() -> context().typeInstance().constructors().niladic().get());

      describe("accessibility", () -> {
        it("can invoke public constructor", () -> {
          context().testClass(() -> PublicMembersTestObject.class);

          Object instance = context().constructor().invoke();

          assertThat(instance).isInstanceOf(context().testClass());
        });
        it("can invoke protected constructor", () -> {
          context().testClass(() -> ProtectedMembersTestObject.class);

          Object instance = context().constructor().invoke();

          assertThat(instance).isInstanceOf(context().testClass());

        });
        it("can invoke default constructor", () -> {
          context().testClass(() -> DefaultMembersTestObject.class);

          Object instance = context().constructor().invoke();

          assertThat(instance).isInstanceOf(context().testClass());
        });
        it("can invoke private constructor", () -> {
          context().testClass(() -> PrivateMembersTestObject.class);

          Object instance = context().constructor().invoke();

          assertThat(instance).isInstanceOf(context().testClass());
        });
      });

      it("throws a special exception for constructor's own exceptions", () -> {
        context().testClass(() -> ExceptionConstructorTestObject.class);

        try {
          context().constructor().invoke();
          failBecauseExceptionWasNotThrown(HaltedConstructorInvocationException.class);
        } catch (HaltedConstructorInvocationException e) {
          assertThat(e.getMessage())
            .startsWith("Invocation halted for constructor[")
            .endsWith("ExceptionConstructorTestObject()] with arguments[]: I don't finish successfully");
          assertThat(e.getHaltingCause()).hasMessage("I don't finish successfully");
          assertThat(e.getInvokedArguments()).isEmpty();
          assertThat(e.getInvokedConstructor().getParameterTypes()).isEmpty();
        }
      });

      describe("for arrays", () -> {
        context().testClass(() -> int[].class);
        context().constructor(() -> {
          return context().typeInstance()
            .constructors().withParameterTypes(Diamond.of(int.class))
            .unique().get();
        });

        it("can instantiate with given size", () -> {

          Object instance = context().constructor().invoke(4);

          assertThat(instance).isInstanceOf(context().testClass());
          assertThat((int[]) instance).hasSize(4);
        });

        it("throws an error if wrong parameter count", () -> {
          try {
            context().constructor().invoke();
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
          } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("Array constructor can have only one int parameter but received: []");
          }
        });

        it("throws an error if wrong parameter type", () -> {
          try {
            context().constructor().invoke("hello");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
          } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("Array constructor parameter must be an integer value but received: hello");
          }
        });
      });


    });

  }
}