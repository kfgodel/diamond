package ar.com.kfgodel.diamond.parameters;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of an executable parameter
 * Created by kfgodel on 07/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class ExecutableParameterTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("an executable parameter", () -> {

            it("has a declared type", () -> {
                context().parameter(()-> Diamond.of(Object.class).methods().named("equals").get().parameters().get());

                TypeInstance parameterType = context().parameter().declaredType();

                assertThat(parameterType.name()).isEqualTo("Object");
            });

        });

    }
}