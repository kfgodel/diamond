package ar.com.kfgodel.diamond.unit.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.MethodReturnTypeTestObject;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior for a method return type
 * Created by kfgodel on 09/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodReturnTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a method's return type", () -> {

            context().typeInstance(() -> Diamond.of(MethodReturnTypeTestObject.class));


            it("is the type declared by the method as its return type",()->{
                TypeMethod method = context().typeInstance().methods().named("stringReturnedMethod").get();
                assertThat(method.returnType().name())
                        .isEqualTo("String");
            });
            
            it("is void for void methods",()->{
                TypeMethod method = context().typeInstance().methods().named("voidReturnedMethod").get();
                assertThat(method.returnType())
                        .isEqualTo(Diamond.of(void.class));
            });   
        });

    }
}
