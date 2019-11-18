package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the representation of type variables
 * Created by kfgodel on 20/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeVariableTest extends JavaSpec<DiamondTestContext> {

  @Override
  public void define() {
    describe("a type variable representation", () -> {
      beforeEach(() -> {
        context().typeInstance(() -> createTypeVariable());
      });

      it("has a name", () -> {
        assertThat(context().typeInstance().name())
          .isEqualTo("T");
      });
      it("has a declaration", () -> {
        assertThat(context().typeInstance().declaration())
          .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() T extends @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.Number & java.lang.Comparable");
      });
      it("can have upper bounds", () -> {
        List<String> upperTypeNames = context().typeInstance().generics().bounds().upper().map((upperBound) -> upperBound.name()).collect(Collectors.toList());
        assertThat(upperTypeNames)
          .isEqualTo(Arrays.asList("Number", "Comparable"));
      });
      it("doesn't have lower bounds", () -> {
        assertThat(context().typeInstance().generics().bounds().lower().count())
          .isEqualTo(0);
      });

      it("can have superclass from upper bounds", () -> {
        assertThat(context().typeInstance().inheritance().superclass().map(Named::name).get())
          .isEqualTo("Number");
      });

      it("can have interfaces from upper bounds", () -> {
        List<String> interfaceNames = context().typeInstance().inheritance().interfaces().map(Named::name).collect(Collectors.toList());
        assertThat(interfaceNames)
          .isEqualTo(Arrays.asList("Comparable"));
      });


      it("can have attached annotations", () -> {
        assertThat(context().typeInstance().annotations().findFirst().get().annotationType())
          .isEqualTo(TestAnnotation1.class);
      });
      it("bounds can have attached annotations too", () -> {
        List<Class<? extends Annotation>> upperBoundAnnotations = context().typeInstance().generics().bounds().upper()
          .flatMap((upperBound) -> upperBound.annotations())
          .map((annotation) -> annotation.annotationType())
          .collect(Collectors.toList());
        assertThat(upperBoundAnnotations)
          .isEqualTo(Arrays.asList(TestAnnotation2.class));
      });

    });
  }


  /**
   * Creates the representation for a real type variable.<br>
   * The static keyword is needed to preserve type variable annotation (I don't know why).<br>
   * If this method were non-static the annotation TestAnnotation1 is not preserved for reflection
   */
  public static <T extends @TestAnnotation2 Number & Comparable> TypeInstance createTypeVariable() {
    AnnotatedType referencedTypeVariable = new ReferenceOf<@TestAnnotation1 T>() {
    }.getReferencedAnnotatedType();
    TypeInstance typeInstance = Diamond.types().from(referencedTypeVariable);
    return typeInstance;
  }

}
