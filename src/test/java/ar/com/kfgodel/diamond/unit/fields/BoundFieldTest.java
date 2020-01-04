package ar.com.kfgodel.diamond.unit.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.fields.BoundFieldTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior of a bound field
 * Created by kfgodel on 16/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class BoundFieldTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a bound field", () -> {

      context().typeInstance(() -> Diamond.of(BoundFieldTestObject.class));
      context().field(() -> context().typeInstance().fields().named(context().name()).asUni().get());
      context().name(() -> "field");
      context().object(BoundFieldTestObject::new);

      it("is a type field bound to a specific instance as 'this' ", () -> {
        TypeField typeField = context().field();

        BoundField bound = typeField.bindTo(context().object());

        assertThat(bound).isNotNull();
      });

      it("can be accessed with the instance implicitly defined", () -> {
        TypeField typeField = context().field();
        BoundField bound = typeField.bindTo(context().object());

        bound.set(42);

        assertThat(bound.get()).isEqualTo(42);

      });

      it("has a type field", () -> {
        TypeField typeField = context().field();

        BoundField bound = typeField.bindTo(context().object());

        assertThat(bound.typeField()).isSameAs(typeField);
      });

      it("has a bound instance", () -> {
        TypeField typeField = context().field();
        Object instance = context().object();

        BoundField bound = typeField.bindTo(instance);

        assertThat(bound.instance()).isSameAs(instance);
      });

      it("has an easily accessible name", () -> {
        TypeField typeField = context().field();

        BoundField bound = typeField.bindTo(context().object());

        assertThat(bound.name()).isEqualTo("field");
      });


      describe("when used as functional types", () -> {

        context().boundField(() -> context().field().bindTo(context().object()));

        it("gets the field value as supplier", () -> {
          context().<BoundFieldTestObject>object().field = 23;

          Supplier<Object> asSupplier = context().boundField();

          assertThat(asSupplier.get()).isEqualTo(23);
        });

        it("sets the field value as consumer", () -> {
          Consumer<Object> asConsumer = context().boundField();

          asConsumer.accept(17);

          assertThat(context().<BoundFieldTestObject>object().field).isEqualTo(17);
        });

        it("set the value and return null as a function", () -> {
          Function<Object, Object> asFunction = context().boundField();

          Object returned = asFunction.apply(18);

          assertThat(context().<BoundFieldTestObject>object().field).isEqualTo(18);
          assertThat(returned).isNull();
        });

        it("fails with an exception on every other combination", () -> {
          BiConsumer<Object, Object> asBiConsumer = context().boundField();

          try {
            asBiConsumer.accept(1, 2);
            failBecauseExceptionWasNotThrown(DiamondException.class);
          } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("This field[public int ar.com.kfgodel.diamond.unit.testobjects.fields.BoundFieldTestObject.field] only accepts 1 or 2 arguments: [BoundFieldTestObject instance, 1, 2]");
          }
        });
      });

      describe("equality", () -> {
        it("is true for bound fields that represent the same field and are bound to the same instance", () -> {
          BoundField firstBoundField = context().field().bindTo(context().object());
          BoundField secondBoundField = context().field().bindTo(context().object());

          assertThat(firstBoundField).isNotSameAs(secondBoundField);
          assertThat(firstBoundField).isEqualTo(secondBoundField);
        });

        it("is false if the represented field is different", () -> {
          BoundField boundToTestField = context().field().bindTo(context().object());
          BoundField boundToOtherField = context().typeInstance().fields().named("otherField").asUni().get().bindTo(context().object());

          assertThat(boundToTestField).isNotEqualTo(boundToOtherField);
        });

        it("is false if they are bound to different instance", () -> {
          BoundField boundToTestObject = context().field().bindTo(context().object());
          BoundField boundToOtherObject = context().field().bindTo(new BoundFieldTestObject());

          assertThat(boundToTestObject).isNotEqualTo(boundToOtherObject);
        });
      });

    });
  }
}