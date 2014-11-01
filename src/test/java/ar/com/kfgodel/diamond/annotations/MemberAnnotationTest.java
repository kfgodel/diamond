package ar.com.kfgodel.diamond.annotations;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.annotations.MemberAnnotationTestObject;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of members with their annotations
 * Created by kfgodel on 01/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MemberAnnotationTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("annotations", () -> {

            context().typeInstance(() -> Diamond.of(MemberAnnotationTestObject.class));

            describe("on a field", () -> {

                context().field(()-> context().typeInstance().fields().existingNamed(context().name()));

                it("can be empty", () -> {
                    context().name(() -> "unannotatedField");

                    Stream<Annotation> annotations = context().field().annotations();

                    assertThat(annotations.count()).isEqualTo(0);
                }); 
                
                it("contains all the annotations",()->{
                    context().name(()-> "annotatedField");

                    Stream<Annotation> annotations = context().field().annotations();

                    assertThat(annotations.map(Annotation::annotationType).map(Type::getTypeName).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("ar.com.kfgodel.diamond.testobjects.TestAnnotation1", "ar.com.kfgodel.diamond.testobjects.TestAnnotation2"));
                });   
            });

            describe("on a method", () -> {

                context().method(() -> context().typeInstance().methods().existingNamed(context().name()));

                it("can be empty",()->{
                    context().name(() -> "unannotatedMethod");

                    Stream<Annotation> annotations = context().method().annotations();

                    assertThat(annotations.count()).isEqualTo(0);
                });

                it("contains all the annotations",()->{
                    context().name(()-> "annotatedMethod");

                    Stream<Annotation> annotations = context().method().annotations();

                    assertThat(annotations.map(Annotation::annotationType).map(Type::getTypeName).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("ar.com.kfgodel.diamond.testobjects.TestAnnotation1", "ar.com.kfgodel.diamond.testobjects.TestAnnotation2"));

                });
            });


            describe("on a constructor", () -> {
                it("can be empty",()->{
                    context().constructor(()-> context().typeInstance().constructors().existingDeclaredFor());

                    Stream<Annotation> annotations = context().constructor().annotations();

                    assertThat(annotations.count()).isEqualTo(0);
                });

                it("contains all the annotations",()->{
                    context().constructor(()-> context().typeInstance().constructors().existingDeclaredFor(Diamond.of(Integer.class)));

                    Stream<Annotation> annotations = context().constructor().annotations();

                    assertThat(annotations.map(Annotation::annotationType).map(Type::getTypeName).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("ar.com.kfgodel.diamond.testobjects.TestAnnotation1", "ar.com.kfgodel.diamond.testobjects.TestAnnotation2"));

                });
            });

        });


    }
}