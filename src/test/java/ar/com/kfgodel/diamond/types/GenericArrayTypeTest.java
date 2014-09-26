package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.ReferenceOf;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation2;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test verifies the representation of a generic array type
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class GenericArrayTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a generic array representaion", ()->{
            beforeEach(()->{
                context().typeInstance(()-> createGenericArrayType());
            });

            it("has a name", ()->{
                assertThat(context().typeInstance().name())
                        .isEqualTo("List[]");
            });

            it("has a declaration name", ()->{
                assertThat(context().typeInstance().names().declarationName())
                        .isEqualTo("java.util.List<java.lang.Integer>[]");
            });

            it("has component type", ()->{
                assertThat(context().typeInstance().componentType().get().name())
                        .isEqualTo("List");

            });

            it("component declaration name is preserved", ()->{
                assertThat(context().typeInstance().componentType().get().names().declarationName())
                        .isEqualTo("java.util.List<java.lang.Integer>");
            });

            it("can have attached annotations", ()->{
                List<Class<? extends Annotation>> annotationTypes = context().typeInstance().annotations()
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(annotationTypes)
                        .isEqualTo(Arrays.asList(TestAnnotation1.class));
            });
            it("component type can have attached annotations too", ()->{
                List<Class<? extends Annotation>> annotationTypes = context().typeInstance().componentType().get().annotations()
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(annotationTypes)
                        .isEqualTo(Arrays.asList(TestAnnotation2.class));
            });
        });
    }

    private static TypeInstance createGenericArrayType() {
        AnnotatedType annotatedType = new ReferenceOf<@TestAnnotation2 List<Integer> @TestAnnotation1[]>() {}.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().fromUnspecific(annotatedType);
        return typeInstance;
    }
}
