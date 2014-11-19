package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies that a native type is retrievable from certain types
 * Created by kfgodel on 20/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class NativeTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a raw class", () -> {

            it("can be obtained from classes",()->{
                TypeInstance stringType = Diamond.of(String.class);

                Class<?> rawClass = stringType.rawClasses().get();

                assertThat(rawClass).isEqualTo(String.class);
            });

        });

    }
}