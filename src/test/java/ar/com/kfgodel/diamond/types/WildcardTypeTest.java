package ar.com.kfgodel.diamond.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation2;
import ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation3;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type test the representation of a wildcard type
 * Created by kfgodel on 20/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class WildcardTypeTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a type wildcard representation", ()->{
            beforeEach(()->{
                context().typeInstance(()-> createKeyWildcardTye());
            });

            it("has a name", ()->{
                assertThat(context().typeInstance().name())
                        .isEqualTo("?");
            });

            it("has a declaration name", ()->{
                assertThat(context().typeInstance().declaration())
                        .isEqualTo("@ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation1() ? extends @ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation3() java.lang.Number");
            });

            it("has upper bounds", ()->{
                List<String> upperTypeNames = context().typeInstance().generics().bounds().upper().map((upperBound)-> upperBound.name()).collect(Collectors.toList());
                assertThat(upperTypeNames)
                        .isEqualTo(Arrays.asList("Number"));
            });

            it("has lower bounds", ()->{
                context().typeInstance(()-> createValueWildcardType());
                List<String> upperTypeNames = context().typeInstance().generics().bounds().lower().map((lowerBound)-> lowerBound.name()).collect(Collectors.toList());
                assertThat(upperTypeNames)
                        .isEqualTo(Arrays.asList("Serializable"));
            });

            it("can have attached annotations", ()->{
                assertThat(context().typeInstance().annotations().findFirst().get().annotationType())
                        .isEqualTo(TestAnnotation1.class);
            });

            it("upper bounds can have annotations too", ()->{
                List<Class<? extends Annotation>> upperBoundAnnotations = context().typeInstance().generics().bounds().upper()
                        .flatMap((upperBound)-> upperBound.annotations())
                        .map((annotation)-> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(upperBoundAnnotations)
                        .isEqualTo(Arrays.asList(TestAnnotation3.class));
            });

            it("lower bounds can have annotations too", ()->{
                context().typeInstance(()-> createValueWildcardType());
                List<Class<? extends Annotation>> upperBoundAnnotations = context().typeInstance().generics().bounds().lower()
                        .flatMap((upperBound) -> upperBound.annotations())
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(upperBoundAnnotations)
                        .isEqualTo(Arrays.asList(TestAnnotation3.class));
            });
        });
    }

    private TypeInstance createKeyWildcardTye() {
        Function<AnnotatedType[], AnnotatedType> selector = (array)-> array[0];
        return createMapBasedWildcardType(selector);
    }

    /**
     * Static keyword is necessary for erasure preservation in runtime of wildcard annotations
     */
    private static TypeInstance createMapBasedWildcardType(Function<AnnotatedType[], AnnotatedType> selector) {
        AnnotatedParameterizedType annotatedMapType = (AnnotatedParameterizedType) new ReferenceOf<Map<
                @TestAnnotation1 ? extends @TestAnnotation3 Number,
                @TestAnnotation2 ? super @TestAnnotation3 Serializable
                >>() {}.getReferencedAnnotatedType();
        AnnotatedType annotatedKeyType = selector.apply(annotatedMapType.getAnnotatedActualTypeArguments());
        return Diamond.types().from(annotatedKeyType);
    }

    private TypeInstance createValueWildcardType() {
        Function<AnnotatedType[], AnnotatedType> selector = (array)-> array[1];
        return createMapBasedWildcardType(selector);
    }

}
