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
            it("can answer if other type is a subtype",()->{
                TypeInstance objectType = Diamond.of(Object.class);
                TypeInstance stringType = Diamond.of(String.class);

                assertThat(objectType.inheritance().isSubTypeOf(stringType)).isFalse();
                assertThat(stringType.inheritance().isSubTypeOf(objectType)).isTrue();
            });

            it("can answer if a native type is a subtype",()->{
                TypeInstance objectType = Diamond.of(Object.class);
                TypeInstance stringType = Diamond.of(String.class);

                assertThat(objectType.inheritance().isSubTypeOfNative(String.class)).isFalse();
                assertThat(stringType.inheritance().isSubTypeOfNative(Object.class)).isTrue();
            });

            it("can answer if other type is a supertype",()->{
                TypeInstance objectType = Diamond.of(Object.class);
                TypeInstance stringType = Diamond.of(String.class);

                assertThat(objectType.inheritance().isSuperTypeOf(stringType)).isTrue();
                assertThat(stringType.inheritance().isSuperTypeOf(objectType)).isFalse();
            });

            it("can answer if a native type is a supertype",()->{
                TypeInstance objectType = Diamond.of(Object.class);
                TypeInstance stringType = Diamond.of(String.class);

                assertThat(objectType.inheritance().isSuperTypeOfNative(String.class)).isTrue();
                assertThat(stringType.inheritance().isSuperTypeOfNative(Object.class)).isFalse();
            });
        });

    }
}
