package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject;
import org.junit.runner.RunWith;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of a type as a functional element
 * Created by kfgodel on 26/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FunctionalTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("type as function", () -> {
            it("is a supplier of new instances",()->{
                Supplier<Object> supplier = Diamond.of(PublicMembersTestObject.class);

                Object createdInstance = supplier.get();

                assertThat(createdInstance).isInstanceOf(PublicMembersTestObject.class);
            });
        });
    }
}