package ar.com.kfgodel.diamond.constructors;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.testobjects.constructors.ConstructorAccessTestObject;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.Optional;

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
                Optional<TypeConstructor> constructor = context().typeInstance().constructors().declaredFor(Diamond.of(Serializable.class));
                assertThat(constructor.isPresent()).isFalse();
            });

            it("can assume only one optional occurrence",()->{
                Optional<TypeConstructor> constructor = context().typeInstance().constructors().declaredFor(Diamond.of(Integer.class));
                assertThat(constructor.isPresent()).isTrue();
            });

            it("can assume a non optional occurrence",()->{
                TypeConstructor constructor = context().typeInstance().constructors().existingDeclaredFor(Diamond.of(Integer.class));
                assertThat(constructor).isNotNull();
            });

            it("throws exception if no match for non optional unique",()->{
                try {
                    context().typeInstance().constructors().existingDeclaredFor(Diamond.of(Serializable.class));
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("There's no constructor with params [java.io.Serializable]");
                }
            });

        });

    }
}