package ar.com.kfgodel.diamond.unit.constructors;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.unit.testobjects.constructors.ConstructorAccessTestObject;
import ar.com.kfgodel.optionals.Optional;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the access to constructors by the parameter types
 * Created by kfgodel on 25/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class ConstructorByParamTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("constructor accessed by param types", () -> {

            context().typeInstance(()-> Diamond.of(ConstructorAccessTestObject.class));

            it("can access the niladic constructor",()->{
                Optional<TypeConstructor> constructor = context().typeInstance().constructors().niladic();
                assertThat(constructor.isPresent()).isTrue();
            });   

            it("can have no match",()->{
                Optional<TypeConstructor> constructor = context().typeInstance().constructors().withParameters(Diamond.of(Serializable.class));
                assertThat(constructor.isPresent()).isFalse();
            });

            it("can assume only one optional occurrence",()->{
                Optional<TypeConstructor> constructor = context().typeInstance().constructors().withParameters(Diamond.of(Integer.class));
                assertThat(constructor.isPresent()).isTrue();
            });

            it("can assume a non optional occurrence",()->{
                TypeConstructor constructor = context().typeInstance().constructors().withParameters(Diamond.of(Integer.class)).get();
                assertThat(constructor).isNotNull();
            });

            it("throws exception if no match for non optional unique",()->{
                try {
                    context().typeInstance().constructors().withParameters(Diamond.of(Serializable.class)).get();
                    failBecauseExceptionWasNotThrown(NoSuchElementException.class);
                } catch (NoSuchElementException e) {
                    assertThat(e).hasMessage("No value present");
                }
            });

        });

    }
}