package ar.com.kfgodel.diamond.unit.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.fields.BoundField;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.fields.BoundFieldTestObject;
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
        describe("a bound field", ()->{

            context().typeInstance(() -> Diamond.of(BoundFieldTestObject.class));
            context().field(() -> context().typeInstance().fields().named(context().name()).get());
            context().name(() -> "field");
            context().object(BoundFieldTestObject::new);

            it("is a type field bound to a specific instance as 'this' ", () -> {
                TypeField typeField = context().field();

                BoundField bound = typeField.bindTo(context().object());

                assertThat(bound).isNotNull();
            });

            it("can be accessed with the instance implicitly defined",()->{
                TypeField typeField = context().field();
                BoundField bound = typeField.bindTo(context().object());

                bound.set(42);

                assertThat(bound.get()).isEqualTo(42);

            });

            it("has a type field",()->{
                TypeField typeField = context().field();

                BoundField bound = typeField.bindTo(context().object());

                assertThat(bound.typeField()).isSameAs(typeField);
            });

            it("has a bound instance",()->{
                TypeField typeField = context().field();
                Object instance = context().object();

                BoundField bound = typeField.bindTo(instance);

                assertThat(bound.instance()).isSameAs(instance);
            });


            describe("requiring one less argument", () -> {

                context().boundField(() -> context().field().bindTo(context().object()));

                it("used as supplier gets the value", () -> {
                    context().<BoundFieldTestObject>object().field = 23;

                    Supplier<Object> asSupplier = context().boundField();

                    assertThat(asSupplier.get()).isEqualTo(23);
                });

                it("used as consumer sets the value",()->{
                    Consumer<Object> asConsumer = context().boundField();

                    asConsumer.accept(17);

                    assertThat(context().<BoundFieldTestObject>object().field).isEqualTo(17);
                });
                
                it("used as function it sets the value",()->{
                    Function<Object,Object> asFunction = context().boundField();

                    Object returned = asFunction.apply(18);

                    assertThat(context().<BoundFieldTestObject>object().field).isEqualTo(18);
                    assertThat(returned).isNull();
                });

                it("used as runnable gets the value but loses it",()->{
                    Runnable asRunnable = context().boundField();

                    asRunnable.run();
                });

                it("fails with an exception on every other combination", () -> {
                    BiConsumer<Object,Object> asBiConsumer = context().boundField();

                    try {
                        asBiConsumer.accept(1, 2);
                        failBecauseExceptionWasNotThrown(DiamondException.class);
                    } catch (Exception e) {
                        assertThat(e.getMessage()).startsWith("This field[public int ar.com.kfgodel.diamond.unit.testobjects.fields.BoundFieldTestObject.field] only accepts 1 or 2 arguments: [ar.com.kfgodel.diamond.unit.testobjects.fields.BoundFieldTestObject@")
                        .endsWith(", 1, 2]");
                    }
                });
            });
        });
    }
}