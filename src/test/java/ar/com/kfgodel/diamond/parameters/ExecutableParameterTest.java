package ar.com.kfgodel.diamond.parameters;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifier;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject;
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

            context().parameter(()-> Diamond.of(PublicMembersTestObject.class).constructors().withParameters(Diamond.of(Integer.class)).get().parameters().get());

            it("has a declared type", () -> {

                TypeInstance parameterType = context().parameter().declaredType();

                assertThat(parameterType.name()).isEqualTo("Integer");
            });

            it("has a runtime name",()->{

                String parameterName = context().parameter().name();

                // Depending on the compilation options this test may fails if one option asserted
                assertThat(parameterName.equals("arg0") || parameterName.equals("parameter")).isTrue();
            });
            
            it("has modifiers",()->{
                List<String> parameterModifiers = context().parameter().modifiers().map(Modifier::declaration).collect(Collectors.toList());

                assertThat(parameterModifiers).isEqualTo(Arrays.asList("final"));
            });

            it("has annotations",()->{
                List<String> parameterModifiers = context().parameter().annotations().map(Annotation::annotationType)
                        .map(Class::getSimpleName).collect(Collectors.toList());

                assertThat(parameterModifiers).isEqualTo(Arrays.asList("TestAnnotation1"));
            });

        });

    }
}