package ar.com.kfgodel.diamond.unit.invokables;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.invokables.InvokableTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior for different invokable types
 * Created by kfgodel on 29/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class InvokableTypesTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("when used as an invokable type", () -> {

      context().typeInstance(() -> Diamond.of(InvokableTestObject.class));

      describe("a method", () -> {

        context().invokable(() ->
          context().typeInstance()
            .methods().named(context().name())
            .unique().get()
        );

        describe("if static", () -> {

          it("takes all the arguments as method arguments", () -> {
            context().name(() -> "staticSumMethod");

            Object result = context().invokable().invoke(3, 4);

            assertThat(result).isEqualTo(7);
          });
        });

        describe("if non static (instance)", () -> {

          it("uses the first argument as the instance, and the rest as method arguments", () -> {
            context().name(() -> "instanceMultiply");

            Object result = context().invokable().invoke(new InvokableTestObject(), 3, 4);

            assertThat(result).isEqualTo(12);
          });

        });


        it("throws an error if arguments don't match", () -> {
          context().name(() -> "staticSumMethod");

          try {
            context().invokable().invoke(3);
            failBecauseExceptionWasNotThrown(DiamondException.class);
          } catch (DiamondException e) {
            assertThat(e.getMessage())
              .startsWith("Invocation rejected for method[")
              .endsWith("InvokableTestObject.staticSumMethod(int,int)]. Expected 2 arguments but invoked with: [3]");
          }
        });

        it("returns null for void methods", () -> {
          context().name(() -> "staticVoidMethod");

          Object result = context().invokable().invoke();

          assertThat(result).isNull();
        });


      });

      describe("a constructor", () -> {

        context().invokable(() -> context().typeInstance()
          .constructors().withParameterTypes(Diamond.of(int.class), Diamond.of(int.class))
          .unique().get());

        it("takes all the arguments as constructor arguments", () -> {
          Object result = context().invokable().invoke(1, 2);

          assertThat(result).isInstanceOf(InvokableTestObject.class);
        });

      });

      describe("a field", () -> {

        context().invokable(() ->
          context().typeInstance()
            .fields().named(context().name())
            .unique().get()
        );

        describe("if static", () -> {

          context().name(() -> "staticField");

          it("takes the one argument as setter value", () -> {
            context().invokable().invoke(45);

            assertThat(InvokableTestObject.staticField).isEqualTo(45);
          });

          it("takes none as getter", () -> {
            InvokableTestObject.staticField = 23;

            Object value = context().invokable().invoke();

            assertThat(value).isEqualTo(23);
          });
        });

        describe("if non static", () -> {

          context().name(() -> "instanceField");

          it("uses the first argument as instance and the second as value for setter", () -> {
            InvokableTestObject object = new InvokableTestObject();

            Invokable invokable = context().invokable();
            invokable.invoke(object, 22);

            assertThat(object.instanceField).isEqualTo(22);
          });

          it("uses the only argument as instance for getter", () -> {
            InvokableTestObject object = new InvokableTestObject();
            object.instanceField = 79;

            Object value = context().invokable().invoke(object);

            assertThat(value).isEqualTo(79);
          });

        });

      });

    });

  }
}