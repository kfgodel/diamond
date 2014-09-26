package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;
import ar.com.kfgodel.diamond.api.sources.TypeDefinedTypeNamesSource;
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

            beforeEach(()->{
                context().classInstance(()-> Diamond.of(Object.class));
            });

            it("has a simple short name", ()->{
                assertThat(context().classInstance().name())
                        .isEqualTo("Object");
            });

            it("has other names", ()->{
                TypeDefinedTypeNamesSource classNames = context().classInstance().names();
                assertThat(classNames.classloaderName())
                        .isEqualTo("java.lang.Object");
            });

            it("has type parameters", ()->{
                assertThat(context().classInstance().typeParameters().count()).isEqualTo(0);
            });

            it("has a lineage with its ancestors", ()->{
                ClassLineage classLineage = context().classInstance().lineage();
                assertThat(classLineage.highestAncestor().name())
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
