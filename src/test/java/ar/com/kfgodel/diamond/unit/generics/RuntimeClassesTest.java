package ar.com.kfgodel.diamond.unit.generics;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ChildClass;
import ar.com.kfgodel.diamond.unit.testobjects.lineage.ParentClass;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.AnnotatedType;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the behavior of the runtime classes for different types
 * Created by kfgodel on 12/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class RuntimeClassesTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("the runtime classes of", () -> {

      test().runtimeClasses(()->
        test().typeInstance()
          .runtime().classes()
          .collectToList()
      );

      it("a non generic class is itself", () -> {
        test().typeInstance(()-> Diamond.of(String.class));

        assertThat(test().runtimeClasses()).containsExactly(String.class);
      });
      it("a parameterized type is its raw class", () -> {
        test().typeInstance(()-> getParameterizedParentClass());

        assertThat(test().runtimeClasses()).containsExactly(ParentClass.class);
      });

      it("an unbounded type variable is Object", () -> {
        test().typeInstance(()-> getUnboundedWildcardType());

        assertThat(test().runtimeClasses()).containsExactly(Object.class);
      });
      it("an upper bounded type variable is its upper bound type", () -> {
        test().typeInstance(()-> getChildClassSubTypeWildcardType());

        assertThat(test().runtimeClasses()).containsExactly(ChildClass.class);
      });
      it("a multi upper bounded type variable are its bounds", () -> {
        test().typeInstance(()-> getChildClassAndNumberSubtypeVariableType());

        System.out.println(test().runtimeClasses());

        assertThat(test().runtimeClasses()).containsExactlyInAnyOrder(ChildClass.class, Collection.class);
      });

      it("an array type is the raw array type", () -> {
        test().typeInstance(()-> getArrayOfParameterizedListsType());

        assertThat(test().runtimeClasses()).containsExactly(List[].class);
      });
    });

  }

  private static TypeInstance getUnboundedWildcardType() {
    TypeInstance listType = getTypeFrom(new ReferenceOf<List<?>>() {});
    TypeInstance unboundedWildcard = listType.generics().arguments().findFirst().get();
    return unboundedWildcard;
  }

  private static TypeInstance getChildClassSubTypeWildcardType() {
    return getTypeFrom(new ReferenceOf<List<? extends ChildClass>>() {})
      .generics().arguments().findFirst().get();
  }

  private static <A extends ChildClass & Collection> TypeInstance getChildClassAndNumberSubtypeVariableType() {
    return getTypeFrom(new ReferenceOf<A>() {});
  }

  private static TypeInstance getTypeFrom(ReferenceOf<?> reference) {
    AnnotatedType annotatedType = reference.getReferencedAnnotatedType();
    TypeInstance typeInstance = Diamond.types().from(annotatedType);
    return typeInstance;
  }

  private static TypeInstance getParameterizedParentClass() {
    return getTypeFrom(new ReferenceOf<ParentClass<String, Integer>>() {});
  }

  private static TypeInstance getArrayOfParameterizedListsType() {
    final ReferenceOf<List<Integer>[]> reference = new ReferenceOf<List<Integer>[]>() {};
    TypeInstance typeInstance = Diamond.types().from(reference);
    return typeInstance;
  }

}