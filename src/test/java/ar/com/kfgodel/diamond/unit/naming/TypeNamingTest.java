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
 * This type verifies the api diamond offers for accessing type names
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class TypeNamingTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("types", () -> {

      it("have a name that is an alias of their shortname", () -> {
        TypeInstance objectType = Diamond.of(Object.class);
        assertThat(objectType.name()).isEqualTo(objectType.names().shortName());
      });

      describe("for classes / parameterized types", () -> {
        test().typeInstance(TypeNamingTest::getStringListType);

        it("shortname is the simple name", () -> {
          assertThat(test().typeInstance().names().shortName())
            .isEqualTo("List");
        });
        it("classloader name is the runtime class name", () -> {
          assertThat(test().typeInstance().names().commonName())
            .isEqualTo("java.util.List");
        });
        it("canonical name is the class canonical name", () -> {
          assertThat(test().typeInstance().names().canonicalName())
            .isEqualTo("java.util.List");
        });
        it("bare name is the canonical name", () -> {
          assertThat(test().typeInstance().names().bareName())
            .isEqualTo(test().typeInstance().names().canonicalName());
        });
        it("typeName is the parameterized type name", () -> {
          assertThat(test().typeInstance().names().typeName())
            .isEqualTo("java.util.List<java.lang.String>");
        });
        it("declaration name is the source declaration equivalent", () -> {
          assertThat(test().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() java.util.List<@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String>");
        });
      });

      describe("for array types", () -> {
        test().typeInstance(TypeNamingTest::getStringArrayType);

        it("shortname is the simple name", () -> {
          assertThat(test().typeInstance().names().shortName())
            .isEqualTo("String[]");
        });
        it("classloader name is the runtime class name", () -> {
          assertThat(test().typeInstance().names().commonName())
            .isEqualTo("[Ljava.lang.String;");
        });
        it("canonical name is the array canonical name", () -> {
          assertThat(test().typeInstance().names().canonicalName())
            .isEqualTo("java.lang.String[]");
        });
        it("bare name is the square brackets", () -> {
          assertThat(test().typeInstance().names().bareName())
            .isEqualTo("[]");
        });
        it("typeName is the array class type name", () -> {
          assertThat(test().typeInstance().names().typeName())
            .isEqualTo("java.lang.String[]");
        });
        it("declaration name is the source declaration equivalent", () -> {
          assertThat(test().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() []");
        });
      });

      describe("for type variables", () -> {
        test().typeInstance(TypeNamingTest::getTypeVariableA);

        it("shortname is the variable name", () -> {
          assertThat(test().typeInstance().names().shortName())
            .isEqualTo("A");
        });
        it("classloader name is typeName", () -> {
          assertThat(test().typeInstance().names().commonName())
            .isEqualTo(test().typeInstance().names().typeName());
        });
        it("canonical name is the typeName", () -> {
          assertThat(test().typeInstance().names().canonicalName())
            .isEqualTo(test().typeInstance().names().typeName());
        });
        it("bare name is the variable name", () -> {
          assertThat(test().typeInstance().names().bareName())
            .isEqualTo("A");
        });
        it("typeName is defined by the TypeVariable instance (for some reason it doesn't include bounds)", () -> {
          assertThat(test().typeInstance().names().typeName())
            .isEqualTo("A");
        });
        it("declaration name is the source declaration equivalent", () -> {
          assertThat(test().typeInstance().declaration())
            .isEqualTo("@ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation1() A extends @ar.com.kfgodel.diamond.unit.testobjects.annotations.TestAnnotation2() java.lang.String");
        });
      });

      describe("for type wildcards", () -> {
        test().typeInstance(TypeNamingTest::getWildcardType);

        it("shortname is the wildcard symbol", () -> {
          assertThat(test().typeInstance().names().shortName())
            .isEqualTo("?");
        });
        it("classloader name is the typeName", () -> {
          assertThat(test().typeInstance().names().commonName())
            .isEqualTo(test().typeInstance().names().typeName());
        });
        it("canonical name is the typeName", () -> {
          assertThat(test().typeInstance().names().canonicalName())
            .isEqualTo(test().typeInstance().names().typeName());
        });
        it("bare name is the wildcard symbol", () -> {
          assertThat(test().typeInstance().names().bareName())
            .isEqualTo("?");
        });
        it("typeName is defined by the WildcardType instance (it includes bounds)", () -> {
          assertThat(test().typeInstance().names().typeName())
            .isEqualTo("? extends java.lang.String");
        });
        it("declaration name is the source declaration equivalent", () -> {
          assertThat(test().typeInstance().declaration())
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
