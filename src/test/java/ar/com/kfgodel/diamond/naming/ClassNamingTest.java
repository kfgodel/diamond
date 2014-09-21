package ar.com.kfgodel.diamond.naming;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the representation of class names
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ClassNamingTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a class instance", ()->{
            beforeEach(()->{
                context().classInstance(()-> Diamond.of(Object.class));
            });

            it("has an easily accessible shortname", ()->{
                assertThat(context().classInstance().name())
                        .isEqualTo("Object");
            });

            describe("names", ()->{
                beforeEach(()->{
                    context().names(()-> context().classInstance().names());
                });

                it("contains the shortname", ()->{
                    assertThat(context().names().shortName())
                            .isEqualTo("Object");
                });

                it("shortname is the same as the type name", ()->{
                    assertThat(context().names().shortName())
                            .isEqualTo(context().classInstance().name());
                });

                it("contains the classloader name", ()->{
                    assertThat(context().names().classloaderName())
                            .isEqualTo("java.lang.Object");
                });

                it("contains the canonical name", ()->{
                    assertThat(context().names().canonicalName())
                            .isEqualTo("java.lang.Object");
                });

                it("contains the declaration name", ()->{
                    assertThat(context().names().declarationName())
                            .isEqualTo("java.lang.Object");
                });
            });

        });
    }
}
