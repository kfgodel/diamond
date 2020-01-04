package ar.com.kfgodel.diamond.unit.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.FieldTypeTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
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

      context().field(() -> Diamond.of(FieldTypeTestObject.class).fields().named("intField").unique().get());

      it("is the type declared for a field", () -> {
        assertThat(context().field().type().name())
          .isEqualTo("int");
      });

      it("is the same as the return type", () -> {
        assertThat(context().field().type())
          .isEqualTo(context().field().returnType());
      });

    });

  }
}
