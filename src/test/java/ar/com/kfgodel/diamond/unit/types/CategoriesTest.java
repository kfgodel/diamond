package ar.com.kfgodel.diamond.unit.types;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.categories.Categories;
import ar.com.kfgodel.diamond.api.types.categories.TypeCategory;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.TestEnum;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the different categories behavior
 * Created by kfgodel on 03/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class CategoriesTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a type category", () -> {

      it("has a name used to identify it",()->{
          assertThat(Categories.REFERENCE.name()).isEqualTo("REFERENCE");
          assertThat(Categories.ARRAY.name()).isEqualTo("ARRAY");
          assertThat(Categories.NUMERIC.name()).isEqualTo("NUMERIC");
      });

      it("is a categorization used to group similar types without a type hierarchy", () -> {
        TypeInstance objectType = Diamond.of(Object.class);

        boolean answer = objectType.is().partOf(Categories.REFERENCE);

        assertThat(answer).isTrue();
      });

      it("a type can have more than one category", () -> {
        TypeInstance listType = Diamond.of(List.class);

        List<TypeCategory> typeCategoryList = listType.categories().collectToList();

        assertThat(typeCategoryList).contains(Categories.REFERENCE);
        assertThat(typeCategoryList).contains(Categories.CONTAINER);
      });

      describe("allows discrimination of", () -> {

        it("primitive types", () -> {
          TypeInstance objectType = Diamond.of(int.class);

          boolean answer = objectType.is().partOf(Categories.PRIMITIVE);

          assertThat(answer).isTrue();
        });

        it("reference types (a.k.a non-primitives)", () -> {
          TypeInstance objectType = Diamond.of(Object.class);

          boolean answer = objectType.is().partOf(Categories.REFERENCE);

          assertThat(answer).isTrue();
        });

        it("reference types (a.k.a non-primitives)", () -> {
          TypeInstance objectType = Diamond.of(Object.class);

          boolean answer = objectType.is().partOf(Categories.REFERENCE);

          assertThat(answer).isTrue();
        });

        it("array types", () -> {
          TypeInstance objectType = Diamond.of(int[].class);

          boolean answer = objectType.is().partOf(Categories.ARRAY);

          assertThat(answer).isTrue();
        });

        it("value types (numbers and text)", () -> {
          TypeInstance objectType = Diamond.of(String.class);

          boolean answer = objectType.is().partOf(Categories.VALUE);

          assertThat(answer).isTrue();
        });

        it("boolean types (boxed and unboxed)", () -> {
          TypeInstance objectType = Diamond.of(boolean.class);

          boolean answer = objectType.is().partOf(Categories.BOOLEAN);

          assertThat(answer).isTrue();
        });

        it("numeric types (boxed and unboxed)", () -> {
          TypeInstance objectType = Diamond.of(long.class);

          boolean answer = objectType.is().partOf(Categories.NUMERIC);

          assertThat(answer).isTrue();
        });

        it("text types", () -> {
          TypeInstance objectType = Diamond.of(StringBuffer.class);

          boolean answer = objectType.is().partOf(Categories.TEXT);

          assertThat(answer).isTrue();
        });

        it("enum types", () -> {
          TypeInstance objectType = Diamond.of(TestEnum.class);

          boolean answer = objectType.is().partOf(Categories.ENUM);

          assertThat(answer).isTrue();
        });

        it("container types", () -> {
          TypeInstance objectType = Diamond.of(Map.class);

          boolean answer = objectType.is().partOf(Categories.CONTAINER);

          assertThat(answer).isTrue();
        });
        it("interface types", () -> {
          TypeInstance objectType = Diamond.of(List.class);

          boolean answer = objectType.is().partOf(Categories.INTERFACE);

          assertThat(answer).isTrue();
        });
        it("class types", () -> {
          TypeInstance objectType = Diamond.of(ArrayList.class);

          boolean answer = objectType.is().partOf(Categories.CLASS);

          assertThat(answer).isTrue();
        });
        it("annotation types", () -> {
          TypeInstance objectType = Diamond.of(Documented.class);

          boolean answer = objectType.is().partOf(Categories.ANNOTATION);

          assertThat(answer).isTrue();
        });
        it("anonymous types", () -> {
          TypeInstance objectType = Diamond.of(new Runnable() {
            @Override
            public void run() {
            }
          }.getClass());

          boolean answer = objectType.is().partOf(Categories.ANONYMOUS);

          assertThat(answer).isTrue();
        });
      });


    });

  }
}