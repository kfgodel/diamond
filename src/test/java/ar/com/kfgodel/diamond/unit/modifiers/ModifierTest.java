package ar.com.kfgodel.diamond.unit.modifiers;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 09/01/16.
 */
@RunWith(JavaSpecRunner.class)
public class ModifierTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a modifier", () -> {
      context().modifier(()-> Modifiers.PUBLIC);

      it("can be accessed directly as a constant",()->{
          assertThat(context().modifier()).isNotNull();
      });
      it("has a declaration",()->{
        assertThat(context().modifier().declaration()).isEqualTo("public");
      });

      describe("presence", () -> {
        it("can be found on an int",()->{
            assertThat(context().modifier().isPresentIn(Modifier.PUBLIC)).isTrue();
        });   
      });

      describe("equality", () -> {
        it("is true if both declaration are equals",()->{
          assertThat(context().modifier()).isEqualTo(Diamond.modifiers().from(Modifier.PUBLIC).get(0));
        });

        it("is false if declarations differ",()->{
          assertThat(context().modifier()).isNotEqualTo(Modifiers.FINAL);
        });
      });


    });

  }
}