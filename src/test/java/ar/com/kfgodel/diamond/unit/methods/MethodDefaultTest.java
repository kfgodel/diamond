package ar.com.kfgodel.diamond.unit.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.DefaultValueAnnotation;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.nary.api.Nary;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies that defaults for methods behaves as expected
 * Created by kfgodel on 08/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodDefaultTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("method default value", () -> {

            it("is defined for annotation members with default value",()->{
                TypeMethod methodWithDefault = Diamond.of(DefaultValueAnnotation.class).methods().named("memberWithDefault").get();

                Nary<Object> defaultValue = methodWithDefault.defaultValue();

                assertThat(defaultValue.get()).isEqualTo(23);
            });
            
            it("is not present for annotations members without a default",()->{
                TypeMethod methodWithDefault = Diamond.of(DefaultValueAnnotation.class).methods().named("memberWithoutDefault").get();

                Nary<Object> defaultValue = methodWithDefault.defaultValue();

                assertThat(defaultValue.isPresent()).isFalse();
            });

            it("is not present for non annotation methods",()->{
                TypeMethod normalMethod = Diamond.of(PublicMembersTestObject.class).methods().named("method").get();

                Nary<Object> defaultValue = normalMethod.defaultValue();

                assertThat(defaultValue.isPresent()).isFalse();
            });
        });

    }
}