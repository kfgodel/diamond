package ar.com.kfgodel.diamond.unit.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.accessors.FieldAccessorTestObject;
import org.junit.runner.RunWith;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of fields as value accessors
 * Created by kfgodel on 23/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldAccessorTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("TypeField as static accessor", ()->{

            context().object(FieldAccessorTestObject::new);
            context().typeInstance(()-> Diamond.of(context().object().getClass()));
            context().field(() -> context().typeInstance().fields().named(context().name()).get());
            context().name(() -> "privateField");

            it("can set the value to an instance",()->{
                FieldAccessorTestObject object = context().object();

                context().field().setValueOn(object, 23);

                assertThat(object.getPrivateField()).isEqualTo(23);

            });
            it("can get the value from an instance", () -> {
                FieldAccessorTestObject object = context().object();
                object.setPrivateField(23);

                Object gettedValue = context().field().getValueFrom(object);

                assertThat(gettedValue).isEqualTo(23);
            });


            describe("accessibility", () -> {
                it("works on public fields",()->{
                    FieldAccessorTestObject object = context().object();
                    context().name(()-> "publicField");

                    context().field().setValueOn(object, 1);

                    assertThat(context().field().<Object>getValueFrom(object)).isEqualTo(1);

                });
                it("works on protected fields",()->{
                    FieldAccessorTestObject object = context().object();
                    context().name(()-> "protectedField");

                    context().field().setValueOn(object, 2);

                    assertThat(context().field().<Object>getValueFrom(object)).isEqualTo(2);
                });
                it("works on default fields",()->{
                    FieldAccessorTestObject object = context().object();
                    context().name(()-> "defaultField");

                    context().field().setValueOn(object, 3);

                    assertThat(context().field().<Object>getValueFrom(object)).isEqualTo(3);

                });
                it("works on private fields",()->{
                    FieldAccessorTestObject object = context().object();
                    context().name(()-> "privateField");

                    context().field().setValueOn(object, 4);

                    assertThat(context().field().<Object>getValueFrom(object)).isEqualTo(4);

                });
            });

            describe("as functions", () -> {
                it("can be used as a bi-consumer for setting the value",()->{
                    FieldAccessorTestObject object = context().object();
                    BiConsumer<Object, Object> field = context().field();

                    field.accept(object, 23);

                    assertThat(object.getPrivateField()).isEqualTo(23);

                }); 
                
                it("can be used as a function for getting the value",()->{
                    FieldAccessorTestObject object = context().object();
                    object.setPrivateField(23);
                    Function<Object,Object> field = context().field();


                    Object gettedValue = field.apply(object);

                    assertThat(gettedValue).isEqualTo(23);

                });   
            });


        });
    }
}