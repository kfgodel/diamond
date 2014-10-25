package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.testobjects.methods.RedefiningMethodTestObject;
import org.junit.runner.RunWith;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the possible access to methods by using a name
 * Created by kfgodel on 25/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodByNameTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("method accessed by name", () -> {

            context().object(RedefiningMethodTestObject::new);
            context().typeInstance(()-> Diamond.of(context().object().getClass()));

            it("can have no match",()->{
                Stream<TypeMethod> matchingMethods = context().typeInstance().methods().named("nonExistingMethod");
                assertThat(matchingMethods.count()).isEqualTo(0);
            });

            it("can have more than one match",()->{
                Stream<TypeMethod> matchingMethods = context().typeInstance().methods().named("redefinedAndOverloadedMethod");
                assertThat(matchingMethods.count()).isEqualTo(3);
            });

            it("can assume only one optional occurrence",()->{
                Optional<TypeMethod> matchingMethod = context().typeInstance().methods().uniqueNamed("uniqueMethod");
                assertThat(matchingMethod.isPresent()).isTrue();
            });

            it("throws exception if more than one match for optional unique",()->{
                try {
                    context().typeInstance().methods().uniqueNamed("redefinedAndOverloadedMethod");
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("There's more than one method named \"redefinedAndOverloadedMethod\"");
                }
            });


            it("can assume a non optional occurrence",()->{
                TypeMethod matchingMethod = context().typeInstance().methods().existingNamed("uniqueMethod");
                assertThat(matchingMethod).isNotNull();
            });

            it("throws exception if no match for non optional unique",()->{
                try {
                    context().typeInstance().methods().existingNamed("nonExistingMethod");
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (DiamondException e) {
                    assertThat(e).hasMessage("There's no method named \"nonExistingMethod\"");
                }
            });
        });

    }
}