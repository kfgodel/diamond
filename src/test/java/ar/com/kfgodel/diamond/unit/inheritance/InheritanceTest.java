package ar.com.kfgodel.diamond.unit.inheritance;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of type hierarchy questions
 * Created by kfgodel on 19/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class InheritanceTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a type's inheritance", () -> {
      it("can answer if other type is a subtype", () -> {
        TypeInstance objectType = Diamond.of(Object.class);
        TypeInstance stringType = Diamond.of(String.class);

        assertThat(objectType.is().subTypeOf(stringType)).isFalse();
        assertThat(stringType.is().subTypeOf(objectType)).isTrue();
      });

      it("can answer if other type is a supertype", () -> {
        TypeInstance objectType = Diamond.of(Object.class);
        TypeInstance stringType = Diamond.of(String.class);

        assertThat(objectType.is().superTypeOf(stringType)).isTrue();
        assertThat(stringType.is().superTypeOf(objectType)).isFalse();
      });

    });

  }
}
