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
 * This type tests the behavior of the runtime type for different native types
 * Created by kfgodel on 12/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class RuntimeTypeTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("the runtime type of", () -> {
      it("a non generic class is itself", () -> {
        TypeInstance runtimeType = Diamond.of(String.class).runtime().type();

        assertThat(runtimeType).isEqualTo(Diamond.of(String.class));
      });
      it("a parameterized type is its raw class", () -> {
        TypeInstance runtimeType = getParameterizedParentClass().runtime().type();

        assertThat(runtimeType).isEqualTo(Diamond.of(ParentClass.class));
      });

      it("an unbounded type variable is Object", () -> {
        TypeInstance runtimeType = getUnboundedWildcardType().runtime().type();

        assertThat(runtimeType).isEqualTo(Diamond.of(Object.class));
      });
      it("an upper bounded type variable is its upper bound type", () -> {
        TypeInstance runtimeType = getChildClassSubTypeWildcardType().runtime().type();

        assertThat(runtimeType).isEqualTo(Diamond.of(ChildClass.class));
      });
      it("a multi upper bounded type variable is Object", () -> {
        TypeInstance runtimeType = getChildClassAndNumberSubtypeVariableType().runtime().type();

        assertThat(runtimeType).isEqualTo(Diamond.of(Object.class));
      });

      it("an array type is the raw array type", () -> {
        TypeInstance runtimeType = getArrayOfParameterizedListsType().runtime().type();

        assertThat(runtimeType).isEqualTo(Diamond.of(List[].class));
      });
    });

  }

  private static TypeInstance getUnboundedWildcardType() {
    TypeInstance listType = getTypeFrom(new ReferenceOf<List<?>>() {});
    TypeInstance unboundedWildcard = listType.generic().arguments().findFirst().get();
    return unboundedWildcard;
  }

  private static TypeInstance getChildClassSubTypeWildcardType() {
    return getTypeFrom(new ReferenceOf<List<? extends ChildClass>>() {})
      .generic().arguments().findFirst().get();
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