package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.DClass;
import ar.com.kfgodel.diamond.api.Diamond;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests general use cases for diamond
 * Created by kfgodel on 17/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class DiamondShowcaseIT extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {

        describe("diamond has its own reflection types", () -> {

            describe("classes", () -> {
                it("can be obtained from Class instances", () -> {
                    DClass diamondClass = Diamond.classes().from(Object.class);
                    assertThat(diamondClass.getName()).isEqualTo("Object");
                });
            });
            describe("methods", ()->{
               it("can be obtained from Class instances", ()->{

               });
            });


        });

    }
}
