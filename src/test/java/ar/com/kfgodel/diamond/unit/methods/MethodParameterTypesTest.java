package ar.com.kfgodel.diamond.unit.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.MethodParameterTypesTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of the method parameter
 * Created by kfgodel on 09/10/14.
 */

@RunWith(JavaSpecRunner.class)
public class MethodParameterTypesTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a method's parameters", () -> {

            context().typeInstance(() -> Diamond.of(MethodParameterTypesTestObject.class));

            it("is empty if the method takes no parameters",()->{
                TypeMethod method = context().typeInstance().methods().all().filter((aMethod) -> aMethod.name().equals("noParameters")).findFirst().get();
                assertThat(method.parameterTypes().count())
                        .isEqualTo(0);

            }); 
            
            it("has the types for the accepted method parameter types",()->{
                TypeMethod method = context().typeInstance().methods().all().filter((aMethod) -> aMethod.name().equals("oneStringOneNumberParameters")).findFirst().get();
                assertThat(method.parameterTypes().map(Named::name).collect(Collectors.toList()))
                        .isEqualTo(Arrays.asList("String", "Number"));
            });   
        });

    }
}
