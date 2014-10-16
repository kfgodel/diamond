package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.testobjects.FieldTypeTestObject;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test verifies teh behavior of a field type property
 * Created by kfgodel on 12/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a field's type", () -> {

            context().typeInstance(() -> Diamond.of(FieldTypeTestObject.class));

            it("is the type declared for a field",()->{
                TypeField field = context().typeInstance().fields().all().filter((aField) -> aField.name().equals("intField")).findFirst().get();
                assertThat(field.type().name())
                        .isEqualTo("int");
            });

        });

    }
}
