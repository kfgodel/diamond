package ar.com.kfgodel.diamond.unit.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.BoundMethod;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.methods.BoundMethodTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of an instanceMethod
 * Created by kfgodel on 16/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class BoundMethodTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a bound method", () -> {

      context().typeInstance(() -> Diamond.of(BoundMethodTestObject.class));
      context().method(() ->
        context().typeInstance()
          .methods().named(context().name())
          .unique().get()
      );
      context().name(() -> "sum");
      context().object(BoundMethodTestObject::new);

      it("is a type method bound to a specific instance as 'this' ", () -> {
        TypeMethod typeMethod = context().method();

        BoundMethod bound = typeMethod.bindTo(context().object());

        assertThat(bound).isNotNull();
      });

      it("can be invoked with the instance implicitly defined", () -> {
        TypeMethod typeMethod = context().method();

        BoundMethod bound = typeMethod.bindTo(context().object());

        final int result = bound.invoke(1, 2);
        assertThat(result).isEqualTo(3);

      });

      it("has a type method", () -> {
        TypeMethod typeMethod = context().method();

        BoundMethod bound = typeMethod.bindTo(context().object());

        assertThat(bound.typeMethod()).isSameAs(typeMethod);
      });

      it("has a bound instance", () -> {
        TypeMethod typeMethod = context().method();
        Object instance = context().object();

        BoundMethod bound = typeMethod.bindTo(instance);

        assertThat(bound.instance()).isSameAs(instance);
      });

      it("has a easily accessible name", () -> {
        TypeMethod typeMethod = context().method();

        BoundMethod bound = typeMethod.bindTo(context().object());

        assertThat(bound.name()).isEqualTo("sum");
      });


      describe("requiring one less argument", () -> {

        context().boundMethod(() -> context().method().bindTo(context().object()));

        it("a no-arg void method becomes a runnable", () -> {
          context().name(() -> "runnable");
          Runnable asRunnable = context().boundMethod();

          asRunnable.run();

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("no-arg void");
        });

        it("a 1 arg void method becomes a consumer", () -> {
          context().name(() -> "consumer");
          Consumer<Object> asConsumer = context().boundMethod();

          asConsumer.accept(1);

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("(1) void");
        });

        it("a 2 arg void method becomes a consumer", () -> {
          context().name(() -> "biconsumer");
          BiConsumer<Object, Object> asBiconsumer = context().boundMethod();

          asBiconsumer.accept(1, 3);

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("(1, 3) void");
        });

        it("a no-arg non-void method becomes a supplier", () -> {
          context().name(() -> "supplier");
          Supplier<Object> asSupplier = context().boundMethod();

          asSupplier.get();

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("no-arg non-void");
        });

        it("a 1 arg non-void method becomes a function", () -> {
          context().name(() -> "function");
          Function<Object, Object> asFunction = context().boundMethod();

          asFunction.apply("text");

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("(text) non-void");
        });

        it("a 1 arg boolean method becomes a predicate", () -> {
          context().name(() -> "predicate");
          Predicate<Object> asPredicate = context().boundMethod();

          asPredicate.test(-15);

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("(-15) boolean");
        });

        it("a 1 arg method with same return type becomes a unary operator", () -> {
          context().name(() -> "unaryOperator");
          UnaryOperator<Object> asUnaryOperator = context().boundMethod();

          asUnaryOperator.apply(0);

          assertThat(context().<BoundMethodTestObject>object().invokedName).isEqualTo("unary (0) non-void");
        });

      });

      describe("equality", () -> {
        it("is true for bound methods that represent the same type method and are bound to the same instance", () -> {
          BoundMethod firstBoundMethod = context().method().bindTo(context().object());
          BoundMethod secondBoundMethod = context().method().bindTo(context().object());

          assertThat(firstBoundMethod).isNotSameAs(secondBoundMethod);
          assertThat(firstBoundMethod).isEqualTo(secondBoundMethod);
        });

        it("is false if the represented method is different", () -> {
          BoundMethod boundToTestMethod = context().method().bindTo(context().object());
          BoundMethod boundToOtherMethod = context().typeInstance()
            .methods().named("runnable")
            .unique().get()
            .bindTo(context().object());

          assertThat(boundToTestMethod).isNotEqualTo(boundToOtherMethod);
        });

        it("is false if they are bound to different instance", () -> {
          BoundMethod boundToTestObject = context().method().bindTo(context().object());
          BoundMethod boundToOtherObject = context().method().bindTo(new BoundMethodTestObject());

          assertThat(boundToTestObject).isNotEqualTo(boundToOtherObject);
        });
      });

    });
  }
}