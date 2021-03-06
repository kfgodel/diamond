package ar.com.kfgodel.diamond.unit.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the constructor retrieval for different types
 * Created by kfgodel on 15/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class AllConstructorsPerTypeTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("all constructors", () -> {

      describe("for classes", () -> {

        context().typeInstance(() -> Diamond.of(ChildClass.class));

        it("includes public constructors", () -> {
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.is(Modifiers.PUBLIC)))
            .isTrue();
        });

        it("includes protected constructors", () -> {
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.is(Modifiers.PROTECTED)))
            .isTrue();
        });

        it("includes private constructors", () -> {
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.is(Modifiers.PRIVATE)))
            .isTrue();
        });

        it("includes default constructors", () -> {
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.is(Modifiers.PACKAGE)))
            .isTrue();
        });

        it("doesn't include inherited constructors", () -> {
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.parameterTypes().collect(Collectors.toList())
              .equals(Arrays.asList(Diamond.of(Number.class)))))
            .isFalse();
        });

      });

      describe("for type variables and wildcards", () -> {

        context().typeInstance(AllConstructorsPerTypeTest::getUnboundedWildcardType);

        it("is empty", () -> {
          assertThat(context().typeInstance().constructors().all().count())
            .isEqualTo(0);
        });

      });


      describe("for array types", () -> {
        context().typeInstance(() -> Diamond.of(String[].class));

        it("includes an artificial constructor", () -> {
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.parameterTypes().collect(Collectors.toList())
              .equals(Arrays.asList(Diamond.of(int.class)))))
            .isTrue();
        });

        it("even for generic array types", () -> {
          context().typeInstance(this::getGenericArrayType);
          assertThat(context().typeInstance().constructors().all()
            .anyMatch((constructor) -> constructor.parameterTypes().collect(Collectors.toList())
              .equals(Arrays.asList(Diamond.of(int.class)))))
            .isTrue();
        });

      });


      describe("for primitive types", () -> {
        context().typeInstance(() -> Diamond.of(int.class));

        it("is empty", () -> {
          assertThat(context().typeInstance().fields().all().count())
            .isEqualTo(0);
        });

      });

      describe("for parameterized types", () -> {
        context().typeInstance(AllConstructorsPerTypeTest::getParameterizedParentClass);

        it("includes the same constructors as the raw class", () -> {
          List<TypeConstructor> parameterizedConstructors = context().typeInstance().constructors().all().collect(Collectors.toList());
          assertThat(parameterizedConstructors)
            .isEqualTo(Diamond.of(ParentClass.class).constructors().all().collect(Collectors.toList()));
        });
      });


    });

  }

  private TypeInstance getGenericArrayType() {
    return getTypeFrom(new ReferenceOf<List<String>[]>() {});
  }


  private static TypeInstance getUnboundedWildcardType() {
    TypeInstance listType = getTypeFrom(new ReferenceOf<List<?>>() {});
    TypeInstance unboundedWildcard = listType.generic().arguments().findFirst().get();
    return unboundedWildcard;
  }

  private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
    TypeInstance typeInstance = Diamond.types().from(reference);
    return typeInstance;
  }

  private static TypeInstance getParameterizedParentClass() {
    return getTypeFrom(new ReferenceOf<ParentClass<String, Integer>>() {});
  }

}
