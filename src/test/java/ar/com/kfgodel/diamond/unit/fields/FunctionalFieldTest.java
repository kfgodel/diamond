package ar.com.kfgodel.diamond.unit.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.fields.FunctionalFieldTestObject;
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
 * This type verifies the behavior of fields as functional types
 * Created by kfgodel on 26/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FunctionalFieldTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("field as a function", () -> {

            context().testClass(()-> FunctionalFieldTestObject.class);
            context().typeInstance(()-> Diamond.of(context().testClass()));
            context().object(()-> context().typeInstance().newInstance());

            it("is a supplier to get a static field value", () -> {
                FunctionalFieldTestObject.staticField = 3;
                Supplier<Object> supplier = context().typeInstance().fields().named("staticField").get();

                Object value = supplier.get();

                assertThat(value).isEqualTo(3);
            });

            it("is a consumer to set a static field value",()->{
                Consumer<Object> consumer = context().typeInstance().fields().named("staticField").get();

                consumer.accept(4);

                assertThat(FunctionalFieldTestObject.staticField).isEqualTo(4);
            });

            it("is a function to get an instance value",()->{
                FunctionalFieldTestObject object = context().object();
                object.instanceField = 20;

                Function<Object, Object> function = context().typeInstance().fields().named("instanceField").get();
                Object value = function.apply(object);

                assertThat(value).isEqualTo(20);
            });

            it("is a biConsumer to set an instance value",()->{
                FunctionalFieldTestObject object = context().object();

                BiConsumer<Object, Object> consumer = context().typeInstance().fields().named("instanceField").get();
                consumer.accept(object, 72);

                assertThat(object.instanceField).isEqualTo(72);
            });

            it("throws an exception if used as static field with an instance field",()->{
                Supplier<Object> supplier = context().typeInstance().fields().named("instanceField").get();

                try {
                    supplier.get();
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (Exception e) {
                    assertThat(e.getMessage())
                            .startsWith("Get for instance field[")
                            .endsWith("FunctionalFieldTestObject.instanceField] cannot be done on null instance");
                }
            });

            it("silently ignores instance argument if used as instance field with a static field",()->{
                FunctionalFieldTestObject.staticField = 8;
                Function<Object, Object> function = context().typeInstance().fields().named("staticField").get();

                Object value = function.apply(context().object());

                assertThat(value).isEqualTo(8);
            });

        });
    }
}