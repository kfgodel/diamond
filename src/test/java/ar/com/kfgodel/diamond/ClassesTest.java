package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type defines specs for test
 * Created by kfgodel on 19/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ClassesTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("class instances", ()->{
            it("can be compared for equality", ()->{
                assertThat(Diamond.of(Object.class))
                        .isEqualTo(Diamond.of(Object.class));
            });
        });
    }


}
