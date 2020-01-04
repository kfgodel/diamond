package ar.com.kfgodel.diamond.unit.invokables;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.invokables.CompositeFunctionTestObject;
import ar.com.kfgodel.functions.CompositeFunction;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of an invocation chain
 * Created by kfgodel on 17/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class CompositeFunctionTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a composite function", () -> {

      context().typeInstance(() -> Diamond.of(CompositeFunctionTestObject.class));

      it("is an ordered set of functions, chained one after the other", () -> {
        CompositeFunction composite = CompositeFunction.create(Arrays.asList(
          CompositeFunctionTest::functionA,
          CompositeFunctionTest::functionB,
          CompositeFunctionTest::functionC));
        assertThat(composite).isNotNull();
      });

      it("can be seen as a function that uses the result of one function as the input of the next", () -> {
        CompositeFunction composite = CompositeFunction.create(Arrays.asList(
          CompositeFunctionTest::functionA,
          CompositeFunctionTest::functionB,
          CompositeFunctionTest::functionC));
        Object result = composite.apply("");

        assertThat(result).isEqualTo(functionC(functionB(functionA(""))));
      });

      it("can be used to get a nested field from an instance", () -> {
        TypeField firstField = context().typeInstance().fields().named("a").asUni().get();
        TypeField secondField = context().typeInstance().fields().named("b").asUni().get();
        TypeField thirdField = context().typeInstance().fields().named("c").asUni().get();
        CompositeFunctionTestObject instance = new CompositeFunctionTestObject();

        CompositeFunction composite = CompositeFunction.create(Arrays.asList(firstField, secondField, thirdField));
        Object result = composite.apply(instance);

        assertThat(result).isSameAs(instance);
      });

      it("can be used to invoke a chain of getters", () -> {
        TypeMethod firstMethod = context().typeInstance().methods().named("getA").asUni().get();
        TypeMethod secondMethod = context().typeInstance().methods().named("getB").asUni().get();
        TypeMethod thirdMethod = context().typeInstance().methods().named("getC").asUni().get();
        CompositeFunctionTestObject instance = new CompositeFunctionTestObject();

        CompositeFunction composite = CompositeFunction.create(Arrays.asList(firstMethod, secondMethod, thirdMethod));
        Object result = composite.apply(instance);

        assertThat(result).isSameAs(instance);
      });

      it("is the identity function if no function provided", () -> {
        CompositeFunction compositeIdentity = CompositeFunction.create(Arrays.asList());

        Object functionResult = compositeIdentity.apply("any input");

        assertThat(functionResult).isEqualTo("any input");
      });
    });

  }

  public static Object functionA(Object input) {
    return input.toString() + "A";
  }

  public static Object functionB(Object input) {
    return input.toString() + "B";
  }

  public static Object functionC(Object input) {
    return input.toString() + "C";
  }

}