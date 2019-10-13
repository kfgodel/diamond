package ar.com.kfgodel.diamond.unit.parameters;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of an executable parameter
 * Created by kfgodel on 07/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class ExecutableParameterTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("an executable parameter", () -> {

            context().parameter(()-> context().typeInstance().constructors().withParameters(Diamond.of(Integer.class)).get().parameters().get());
            context().typeInstance(()-> Diamond.of(PublicMembersTestObject.class));

            it("has a declared type", () -> {

                TypeInstance parameterType = context().parameter().declaredType();

                assertThat(parameterType.name()).isEqualTo("Integer");
            });

            it("has a runtime name",()->{

                String parameterName = context().parameter().name();

                // Depending on the compilation options this test may fails if one option asserted
                assertThat(parameterName.equals("arg0") || parameterName.equals("parameter")).isTrue();
            });

            /**
             * This test fails sometimes. It seems the compiler omits the modifier in the generated .class?
             */
            xit("has modifiers",()->{
                List<String> parameterModifiers = context().parameter().modifiers().map(Modifier::declaration).collect(Collectors.toList());

                assertThat(parameterModifiers).isEqualTo(Arrays.asList("final"));
            });

            it("has annotations",()->{
                List<String> parameterModifiers = context().parameter().annotations().map(Annotation::annotationType)
                        .map(Class::getSimpleName).collect(Collectors.toList());

                assertThat(parameterModifiers).isEqualTo(Arrays.asList("TestAnnotation1"));
            });

            describe("equality", () -> {
                it("is true if declared type and name are equals",()->{
                    ExecutableParameter otherParameter = context().typeInstance().methods().named("methodWithEqualParam")
                      .get().parameters().findFirst().get();
                    assertThat(context().parameter()).isEqualTo(otherParameter);
                });

              /**
               * This test fails sometimes. The compiler doesn't generate proper parameter names on some machines
               */
                xit("is false if name is different",()->{
                    ExecutableParameter otherParameter = context().typeInstance().methods().named("methodWithDiffParamName")
                      .get().parameters().findFirst().get();
                    assertThat(context().parameter()).isNotEqualTo(otherParameter);
                });

                it("is false if declared type is different",()->{
                    ExecutableParameter otherParameter = context().typeInstance().methods().named("methodWithDiffParamType")
                      .get().parameters().findFirst().get();
                    assertThat(context().parameter()).isNotEqualTo(otherParameter);
                });
            });


        });

    }
}