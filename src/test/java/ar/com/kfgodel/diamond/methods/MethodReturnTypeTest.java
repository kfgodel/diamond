package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.MethodReturnTypeTestObject;
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
                ClassMethod method = context().typeInstance().methods().all().filter((aMethod) -> aMethod.name().equals("stringReturnedMethod")).findFirst().get();
                assertThat(method.returnType().name())
                        .isEqualTo("String");
            });
            
            it("is void for void methods",()->{
                ClassMethod method = context().typeInstance().methods().all().filter((aMethod) -> aMethod.name().equals("voidReturnedMethod")).findFirst().get();
                assertThat(method.returnType().name())
                        .isEqualTo("void");
            });   
        });

    }
}
