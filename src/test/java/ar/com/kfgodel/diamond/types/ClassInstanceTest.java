package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type defines specs for test
 * Created by kfgodel on 19/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ClassInstanceTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a class instance", ()->{

            it("has a simple short name", ()->{
                assertThat(Diamond.of(Object.class).name())
                        .isEqualTo("Object");
            });

            xit("equality is based on the semantic name", ()->{
                assertThat(Diamond.of(Object.class))
                        .isEqualTo(Diamond.of(Object.class));
            });
            it("contains type parameter values");
            it("contains type argument values");
        });
    }


}
