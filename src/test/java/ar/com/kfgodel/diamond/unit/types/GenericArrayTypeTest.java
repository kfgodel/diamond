package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
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
import java.lang.reflect.GenericArrayType;
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
    describe("a generic array representaion", () -> {
      beforeEach(() -> {
        context().typeInstance(() -> createGenericArrayType());
      });

      it("has a name", () -> {
        assertThat(context().typeInstance().name())
          .isEqualTo("List[]");
      });

      it("has a declaration", () -> {
        assertThat(context().typeInstance().declaration())
          .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.util.List<java.lang.Integer> @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() []");
      });

      it("has component type", () -> {
        assertThat(context().typeInstance().componentType().get().name())
          .isEqualTo("List");

      });

      it("component declaration name is preserved", () -> {
        assertThat(context().typeInstance().componentType().get().names().typeName())
          .isEqualTo("java.util.List<java.lang.Integer>");
      });

      it("can have attached annotations", () -> {
        List<Class<? extends Annotation>> annotationTypes = context().typeInstance().annotations()
          .map((annotation) -> annotation.annotationType())
          .collect(Collectors.toList());
        assertThat(annotationTypes)
          .isEqualTo(Arrays.asList(TestAnnotation1.class));
      });
      it("component type can have attached annotations too", () -> {
        List<Class<? extends Annotation>> annotationTypes = context().typeInstance().componentType().get().annotations()
          .map((annotation) -> annotation.annotationType())
          .collect(Collectors.toList());
        assertThat(annotationTypes)
          .isEqualTo(Arrays.asList(TestAnnotation2.class));
      });

      it("can be accessed from its type instance", () -> {
        final AnnotatedType reflectionType = context().typeInstance().reflectedAs(AnnotatedType.class).get();
        assertThat(reflectionType.getType()).isInstanceOf(GenericArrayType.class);
      });

    });
  }

  private static TypeInstance createGenericArrayType() {
    AnnotatedType annotatedType = getAnnotatedType();
    TypeInstance typeInstance = Diamond.types().from(annotatedType);
    return typeInstance;
  }

  private static AnnotatedType getAnnotatedType() {
    return new ReferenceOf<@TestAnnotation2 List<Integer> @TestAnnotation1 []>() {
      }.getReferencedAnnotatedType();
  }
}
