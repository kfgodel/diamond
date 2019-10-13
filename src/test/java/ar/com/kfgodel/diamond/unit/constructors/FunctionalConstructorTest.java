package ar.com.kfgodel.diamond.unit.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.constructors.FunctionalConstructorTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior of constructor as a functional element
 * Created by kfgodel on 26/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FunctionalConstructorTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("constructor as a function", () -> {

            context().testClass(()-> FunctionalConstructorTestObject.class);
            context().typeInstance(()-> Diamond.of(context().testClass()));

            it("is a supplier if niladic", () -> {
                Supplier<Object> supplier = context().typeInstance().constructors().niladic().get();

                Object createdInstance = supplier.get();

                assertThat(createdInstance).isInstanceOf(context().testClass());
            }); 
            
            it("is a function if one argument",()->{
                Function<Object, Object> function = context().typeInstance().constructors().withParameters(Diamond.of(Integer.class)).get();

                Object createdInstance = function.apply(1);

                assertThat(createdInstance).isInstanceOf(context().testClass());
            });

            it("throws an exception if functionally called with wrong argument count",()->{
                Function<Object, Object> function = context().typeInstance().constructors().niladic().get();

                try {
                    function.apply(1);
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (Exception e) {
                    assertThat(e.getMessage())
                            .startsWith("Invocation rejected for constructor[")
                            .endsWith("FunctionalConstructorTestObject()] due to wrong arguments[1]");
                }
            });

            it("throws an exception if functionally called with wrong argument types",()->{
                Function<Object, Object> function = context().typeInstance().constructors().withParameters(Diamond.of(Integer.class)).get();

                try {
                    function.apply("a");
                    failBecauseExceptionWasNotThrown(DiamondException.class);
                } catch (Exception e) {
                    assertThat(e.getMessage())
                            .startsWith("Invocation rejected for constructor[")
                            .endsWith("FunctionalConstructorTestObject(java.lang.Integer)] due to wrong arguments[a]");
                }
            });
        });

    }
}