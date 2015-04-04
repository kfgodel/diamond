package ar.com.kfgodel.diamond.unit.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.methods.RedefiningMethodTestObject;
import ar.com.kfgodel.nary.exceptions.MoreThanOneElementException;
import ar.com.kfgodel.optionals.Optional;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior of methods accessed by name and parameter types
 * Created by kfgodel on 06/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodBySignatureTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("method accessed by signature", () -> {

            context().object(RedefiningMethodTestObject::new);
            context().typeInstance(()-> Diamond.of(context().object().getClass()));

            it("can have no match",()->{
                Stream<TypeMethod> matchingMethods = context().typeInstance().methods().withSignature("nonExistingMethod");
                assertThat(matchingMethods.count()).isEqualTo(0);
            });

            it("can have more than one match",()->{
                Stream<TypeMethod> matchingMethods = context().typeInstance().methods().withSignature("redefinedAndOverloadedMethod");
                assertThat(matchingMethods.count()).isEqualTo(2);
            });

            it("can assume only one optional occurrence",()->{
                Optional<TypeMethod> matchingMethod = context().typeInstance().methods().withSignature("uniqueMethod");
                assertThat(matchingMethod.isPresent()).isTrue();
            });

            it("throws exception if more than one match when unique assumed",()->{
                try {
                    context().typeInstance().methods().withSignature("redefinedAndOverloadedMethod").isPresent();
                    failBecauseExceptionWasNotThrown(MoreThanOneElementException.class);
                } catch (MoreThanOneElementException e) {
                    assertThat(e).hasMessage("There's more than one element in the stream to create an optional: [public void redefinedAndOverloadedMethod() /* RedefiningMethodTestObject */, public void redefinedAndOverloadedMethod() /* RedefinedMethodTestObject */]");
                }
            });


            it("can assume a non optional occurrence",()->{
                TypeMethod matchingMethod = context().typeInstance().methods().withSignature("uniqueMethod").get();
                assertThat(matchingMethod).isNotNull();
            });

            it("throws exception if no match for non optional unique",()->{
                try {
                    context().typeInstance().methods().withSignature("nonExistingMethod").get();
                    failBecauseExceptionWasNotThrown(NoSuchElementException.class);
                } catch (NoSuchElementException e) {
                    assertThat(e).hasMessage("No value present");
                }
            });
        });


    }
}