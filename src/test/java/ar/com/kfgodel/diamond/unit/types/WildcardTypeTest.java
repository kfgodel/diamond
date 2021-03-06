package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.WildcardType;
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
    describe("a type wildcard representation", () -> {
      beforeEach(() -> {
        context().typeInstance(() -> createKeyWildcardTye());
      });

      it("has a name", () -> {
        assertThat(context().typeInstance().name())
          .isEqualTo("?");
      });

      it("has a declaration name", () -> {
        assertThat(context().typeInstance().declaration())
          .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() ? extends @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation3() java.lang.Number");
      });

      it("has upper bounds", () -> {
        List<String> upperTypeNames = context().typeInstance().generic().bounds().upper().map((upperBound) -> upperBound.name()).collect(Collectors.toList());
        assertThat(upperTypeNames)
          .isEqualTo(Arrays.asList("Number"));
      });

      it("has lower bounds", () -> {
        context().typeInstance(() -> createValueWildcardType());
        List<String> upperTypeNames = context().typeInstance().generic().bounds().lower().map((lowerBound) -> lowerBound.name()).collect(Collectors.toList());
        assertThat(upperTypeNames)
          .isEqualTo(Arrays.asList("Serializable"));
      });

      it("can have attached annotations", () -> {
        assertThat(context().typeInstance().annotations().findFirst().get().annotationType())
          .isEqualTo(TestAnnotation1.class);
      });

      it("upper bounds can have annotations too", () -> {
        List<Class<? extends Annotation>> upperBoundAnnotations = context().typeInstance().generic().bounds().upper()
          .flatMap((upperBound) -> upperBound.annotations())
          .map((annotation) -> annotation.annotationType())
          .collect(Collectors.toList());
        assertThat(upperBoundAnnotations)
          .isEqualTo(Arrays.asList(TestAnnotation3.class));
      });

      it("lower bounds can have annotations too", () -> {
        context().typeInstance(() -> createValueWildcardType());
        List<Class<? extends Annotation>> upperBoundAnnotations = context().typeInstance().generic().bounds().lower()
          .flatMap((upperBound) -> upperBound.annotations())
          .map((annotation) -> annotation.annotationType())
          .collect(Collectors.toList());
        assertThat(upperBoundAnnotations)
          .isEqualTo(Arrays.asList(TestAnnotation3.class));
      });

      it("can be accessed from its type instance", () -> {
        final AnnotatedType reflectionType = context().typeInstance()
          .reflectedAs(AnnotatedType.class)
          .get();
        assertThat(reflectionType.getType()).isInstanceOf(WildcardType.class);
      });
    });
  }

  private TypeInstance createKeyWildcardTye() {
    Function<AnnotatedType[], AnnotatedType> selector = (array) -> array[0];
    return createMapBasedWildcardType(0);
  }

  /**
   * Static keyword is necessary for erasure preservation in runtime of wildcard annotations
   */
  private static TypeInstance createMapBasedWildcardType(int expectedArgType) {
    final TypeInstance mapType = Diamond.types().from(new ReferenceOf<Map<
      @TestAnnotation1 ? extends @TestAnnotation3 Number,
      @TestAnnotation2 ? super @TestAnnotation3 Serializable
      >>() {
    });

    return mapType.generic().arguments()
      .skip(expectedArgType)
      .findFirst()
      .get();
  }

  private TypeInstance createValueWildcardType() {
    Function<AnnotatedType[], AnnotatedType> selector = (array) -> array[1];
    return createMapBasedWildcardType(1);
  }

}
