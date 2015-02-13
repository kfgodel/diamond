package ar.com.kfgodel.diamond.unit.types;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass;
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

            it("has a name", () -> {
                assertThat(context().typeInstance().name())
                        .isEqualTo("ParentClass");
            });

            it("has a declaration", () -> {
                assertThat(context().typeInstance().declaration())
                        .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass<@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String, @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3() java.lang.Integer>");
            });

            it("has type parameters", ()->{
                List<String> typeParameterNames = context().typeInstance().generics().parameters().map((typeParameter) -> typeParameter.name()).collect(Collectors.toList());
                assertThat(typeParameterNames)
                        .isEqualTo(Arrays.asList("P1", "P2"));
            });


            it("has type arguments", ()->{
                List<String> argumentNames = context().typeInstance().generics().arguments().map((typeArgument) -> typeArgument.name()).collect(Collectors.toList());
                assertThat(argumentNames).isEqualTo(Arrays.asList("String", "Integer"));
            });

            it("type argument declaration names are preserved", ()->{
                List<String> argumentDeclarations = context().typeInstance().generics().arguments()
                        .map((typeArgument) -> typeArgument.declaration())
                        .collect(Collectors.toList());
                assertThat(argumentDeclarations)
                        .isEqualTo(Arrays.asList("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String", "@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3() java.lang.Integer"));

            });

            it("can have attached annotations", ()->{
                List<Class<? extends Annotation>> annotationTypes = context().typeInstance().annotations()
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(annotationTypes)
                        .isEqualTo(Arrays.asList(TestAnnotation1.class));
            });

            it("type arguments can have attached annotations too", ()->{
                List<Class<? extends Annotation>> annotationTypes = context().typeInstance().generics().arguments()
                        .flatMap((typeArgument) -> typeArgument.annotations())
                        .map((annotation) -> annotation.annotationType())
                        .collect(Collectors.toList());
                assertThat(annotationTypes)
                        .isEqualTo(Arrays.asList(TestAnnotation2.class, TestAnnotation3.class));
            });

            /**
             * This is due to the lack of an annotated type parameters method on the Class instance
             */
            it("type parameters can't have attached annotations", ()->{
                long parameterAnnotationsCount = context().typeInstance().generics().parameters()
                        .flatMap((typeArgument) -> typeArgument.annotations())
                        .count();
                // Even though the 1st type parameter has annotations we cannot access it on runtime
                // with reflection. Limitation from current reflection API
                assertThat(parameterAnnotationsCount).isEqualTo(0);
            });
            
            it("is a subtype of its raw type",()->{
                TypeInstance parameterizedMap = Diamond.types().from(new ReferenceOf<Map<String, Integer>>() {}.getReferencedAnnotatedType());
                
                assertThat(parameterizedMap.inheritance().isSubTypeOf(Diamond.of(Map.class))).isTrue();
            });   

        });
    }

    private static TypeInstance createParameterizedType() {
        AnnotatedType annotatedType = new ReferenceOf<@TestAnnotation1 ParentClass<@TestAnnotation2 String, @TestAnnotation3 Integer>>() {}.getReferencedAnnotatedType();
        TypeInstance typeInstance = Diamond.types().from(annotatedType);
        return typeInstance;
    }
}
