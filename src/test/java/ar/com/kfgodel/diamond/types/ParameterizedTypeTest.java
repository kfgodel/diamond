package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation2;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation3;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the representation of a parameterized type
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ParameterizedTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a parameterized type representation", ()->{

            beforeEach(()->{
                context().typeInstance(()-> createParameterizedType());
            });

            it("has a name", ()->{
                assertThat(context().typeInstance().name())
                        .isEqualTo("Map");
            });

            it("has a declaration name",()->{
                assertThat(context().typeInstance().names().declarationName())
                        .isEqualTo("java.util.Map<java.lang.String, java.lang.Integer>");
            });

            it("has type arguments", ()->{
                List<String> argumentNames = context().typeInstance().typeArguments().map((typeArgument) -> typeArgument.name()).collect(Collectors.toList());
                assertThat(argumentNames).isEqualTo(Arrays.asList("String", "Integer"));
            });

            it("type argument declaration names are preserved", ()->{
                assertThat(context().typeInstance().typeArguments()
                            .map((typeArgument)-> typeArgument.names().declarationName())
                            .collect(Collectors.toList()))
                        .isEqualTo(Arrays.asList("java.lang.String","java.lang.Integer"));

            });

            it("can have attached annotations", ()->{
                List<Class<? extends Annotation>> annotationTypes = context().typeInstance().annotations()
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(annotationTypes)
                        .isEqualTo(Arrays.asList(TestAnnotation1.class));
            });

            it("type arguments can have attached annotations too", ()->{
                List<Class<? extends Annotation>> annotationTypes = context().typeInstance().typeArguments()
                        .flatMap((typeArgument) -> typeArgument.annotations())
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(annotationTypes)
                        .isEqualTo(Arrays.asList(TestAnnotation2.class, TestAnnotation3.class));
            });

        });
    }

    private static TypeInstance createParameterizedType() {
        AnnotatedType annotatedType = new ReferenceOf<@TestAnnotation1 Map<@TestAnnotation2 String, @TestAnnotation3 Integer>>() {}.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().fromUnspecific(annotatedType);
        return typeInstance;
    }
}
