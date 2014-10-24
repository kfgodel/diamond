package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.testobjects.accessors.FieldAccessorTestObject;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of fields as value accessors
 * Created by kfgodel on 23/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldAccessorTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("field as static accessor", ()->{

            context().object(FieldAccessorTestObject::new);
            context().typeInstance(()-> Diamond.of(context().object().getClass()));

            it("can get the value from an instance", () -> {
                FieldAccessorTestObject object = context().object();
                object.setPrivateField(23);

                TypeField field = context().typeInstance().fields().existingNamed("privateField");

                assertThat(field.<Object>getValueFrom(object)).isEqualTo(23);
            });

            it("can set the value to an instance",()->{
                FieldAccessorTestObject object = context().object();

                TypeField field = context().typeInstance().fields().existingNamed("privateField");
                field.setValueOn(object, 23);

                assertThat(object.getPrivateField()).isEqualTo(23);
            });
        });
    }
}