package ar.com.kfgodel.diamond.unit.interfaces;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface1;
import ar.com.kfgodel.diamond.unit.testobjects.interfaces.ChildInterface2;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.GrandParentClass;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of interface retrieval for all basic types
 * Created by kfgodel on 04/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class AllTypeInterfacesTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("interfaces", () -> {

      describe("for classes", () -> {

        it("can be empty if no interface implemented", () -> {
          context().typeInstance(() -> Diamond.of(GrandParentClass.class));

          Stream<TypeInstance> interfaces = context().typeInstance().inheritance().interfaces();

          assertThat(interfaces.count()).isEqualTo(0);
        });

        it("contains all the implemented interfaces", () -> {
          context().typeInstance(() -> Diamond.of(ChildClass.class));

          List<String> interfaceNames = context().typeInstance().inheritance().interfaces().map(Named::name).collect(Collectors.toList());

          assertThat(interfaceNames).isEqualTo(Arrays.asList("ChildInterface1", "ChildInterface2"));
        });

        it("contains the extended interfaces of an interface", () -> {
          context().typeInstance(() -> Diamond.of(ChildInterface1.class));

          List<String> interfaceNames = context().typeInstance().inheritance().interfaces().map(Named::name).collect(Collectors.toList());

          assertThat(interfaceNames).isEqualTo(Arrays.asList("ParentInterface1"));
        });

      });

      describe("for type variables and wildcards", () -> {

        describe("without upper bounds", () -> {

          context().typeInstance(AllTypeInterfacesTest::getUnboundedWildcardType);

          it("is empty", () -> {
            Stream<TypeInstance> interfaces = context().typeInstance().inheritance().interfaces();

            assertThat(interfaces.count()).isEqualTo(0);
          });
        });

        describe("with upper bounds", () -> {

          context().typeInstance(AllTypeInterfacesTest::getComparableAndChildInterface2SubtypeVariableType);

          it("includes only the interfaces declared as upper bounds", () -> {

            List<String> interfaceNames = context().typeInstance().inheritance().interfaces().map(Named::name).collect(Collectors.toList());

            assertThat(interfaceNames).isEqualTo(Arrays.asList("Comparable", "ChildInterface2"));
          });
        });

      });


      describe("for array types", () -> {
        context().typeInstance(() -> Diamond.of(String[].class));

        it("includes cloneable and serializable", () -> {
          Stream<TypeInstance> interfaces = context().typeInstance().inheritance().interfaces();

          List<String> interfaceNames = interfaces.map(Named::name).collect(Collectors.toList());

          assertThat(interfaceNames).isEqualTo(Arrays.asList("Cloneable", "Serializable"));
        });

      });


      describe("for primitive types", () -> {
        context().typeInstance(() -> Diamond.of(int.class));

        it("is empty", () -> {
          Stream<TypeInstance> interfaces = context().typeInstance().inheritance().interfaces();

          assertThat(interfaces.count()).isEqualTo(0);
        });

      });

    });

  }

  private static TypeInstance getUnboundedWildcardType() {
    TypeInstance listType = getTypeFrom(new ReferenceOf<List<?>>() {
    });
    TypeInstance unboundedWildcard = listType.generics().arguments().findFirst().get();
    return unboundedWildcard;
  }

  private static <A extends Comparable & ChildInterface2> TypeInstance getComparableAndChildInterface2SubtypeVariableType() {
    return getTypeFrom(new ReferenceOf<A>() {
    });
  }

  private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
    AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
    TypeInstance typeInstance = Diamond.types().from(annotatedType);
    return typeInstance;
  }

}