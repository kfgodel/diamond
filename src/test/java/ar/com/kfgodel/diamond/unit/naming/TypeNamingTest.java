package ar.com.kfgodel.diamond.unit.naming;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the representation of class names
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeNamingTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("types", () -> {

      it("have a name that is equal to their shortname", () -> {
        TypeInstance objectType = Diamond.of(Object.class);
        assertThat(objectType.name()).isEqualTo(objectType.names().shortName());
      });

      describe("for classes / parameterized types", () -> {
        beforeEach(() -> {
          context().typeInstance(TypeNamingTest::getStringListType);
        });

        it("shortname is the simple name", () -> {
          assertThat(context().typeInstance().names().shortName())
            .isEqualTo("List");
        });
        it("classloader name is the class name", () -> {
          assertThat(context().typeInstance().names().classloaderName())
            .isEqualTo("java.util.List");
        });
        it("canonical name is the class canonical name", () -> {
          assertThat(context().typeInstance().names().canonicalName())
            .isEqualTo("java.util.List");
        });
        it("bare name is the canonical name", () -> {
          assertThat(context().typeInstance().names().bareName())
            .isEqualTo(context().typeInstance().names().canonicalName());
        });
        it("typeName is the class type name", () -> {
          assertThat(context().typeInstance().names().typeName())
            .isEqualTo("java.util.List<java.lang.String>");
        });
        it("declaration name is the soure declaration equivalent", () -> {
          assertThat(context().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() java.util.List<@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String>");
        });
      });

      describe("for array types", () -> {
        beforeEach(() -> {
          context().typeInstance(TypeNamingTest::getStringArrayType);
        });

        it("shortname is the simple name", () -> {
          assertThat(context().typeInstance().names().shortName())
            .isEqualTo("String[]");
        });
        it("classloader name is the class name", () -> {
          assertThat(context().typeInstance().names().classloaderName())
            .isEqualTo("[Ljava.lang.String;");
        });
        it("canonical name is the array canonical name", () -> {
          assertThat(context().typeInstance().names().canonicalName())
            .isEqualTo("java.lang.String[]");
        });
        it("bare name is the square brackets", () -> {
          assertThat(context().typeInstance().names().bareName())
            .isEqualTo("[]");
        });
        it("typeName is the class type name", () -> {
          assertThat(context().typeInstance().names().typeName())
            .isEqualTo("java.lang.String[]");
        });
        it("declaration name is the soure declaration equivalent", () -> {
          assertThat(context().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() []");
        });
      });

      describe("for type variables", () -> {
        beforeEach(() -> {
          context().typeInstance(TypeNamingTest::getTypeVariableA);
        });

        it("shortname is the variable name", () -> {
          assertThat(context().typeInstance().names().shortName())
            .isEqualTo("A");
        });
        it("classloader name is typeName", () -> {
          assertThat(context().typeInstance().names().classloaderName())
            .isEqualTo(context().typeInstance().names().typeName());
        });
        it("canonical name is the typeName", () -> {
          assertThat(context().typeInstance().names().canonicalName())
            .isEqualTo(context().typeInstance().names().typeName());
        });
        it("bare name is the variable name", () -> {
          assertThat(context().typeInstance().names().bareName())
            .isEqualTo("A");
        });
        it("typeName is the type name (for some reason it doesn't include bounds)", () -> {
          assertThat(context().typeInstance().names().typeName())
            .isEqualTo("A");
        });
        it("declaration name is the soure declaration equivalent", () -> {
          assertThat(context().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() A extends @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String");
        });
      });

      describe("for type wildcards", () -> {
        beforeEach(() -> {
          context().typeInstance(TypeNamingTest::getWildcardType);
        });

        it("shortname is the wildcard symbol", () -> {
          assertThat(context().typeInstance().names().shortName())
            .isEqualTo("?");
        });
        it("classloader name is typeName", () -> {
          assertThat(context().typeInstance().names().classloaderName())
            .isEqualTo(context().typeInstance().names().typeName());
        });
        it("canonical name is the typeName", () -> {
          assertThat(context().typeInstance().names().canonicalName())
            .isEqualTo(context().typeInstance().names().typeName());
        });
        it("bare name is the wildcard symbol", () -> {
          assertThat(context().typeInstance().names().bareName())
            .isEqualTo("?");
        });
        it("typeName is the type name", () -> {
          assertThat(context().typeInstance().names().typeName())
            .isEqualTo("? extends java.lang.String");
        });
        it("declaration name is the soure declaration equivalent", () -> {
          assertThat(context().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() ? extends @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String");
        });
      });


    });
  }

  private static TypeInstance getStringListType() {
    return getTypeFrom(new ReferenceOf<@TestAnnotation1 List<@TestAnnotation2 String>>() {
    });
  }

  private static TypeInstance getStringArrayType() {
    return getTypeFrom(new ReferenceOf<@TestAnnotation2 String @TestAnnotation1 []>() {
    });
  }

  private static <A extends @TestAnnotation2 String> TypeInstance getTypeVariableA() {
    return getTypeFrom(new ReferenceOf<@TestAnnotation1 A>() {
    });
  }

  private static TypeInstance getWildcardType() {
    return getTypeFrom(new ReferenceOf<List<@TestAnnotation1 ? extends @TestAnnotation2 String>>() {
    }).generics().arguments().findFirst().get();
  }


  private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
    AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
    TypeInstance typeInstance = Diamond.types().from(annotatedType);
    return typeInstance;
  }

}
