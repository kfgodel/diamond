package ar.com.kfgodel.lazyvalue.impl;

import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class verifies the simple self supplier class
 * Date: 9/12/19 - 17:51
 */
@RunWith(JavaSpecRunner.class)
public class SelfSupplierTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a self supplier", () -> {
      test().cached(() -> SelfSupplier.of(134));

      it("returns the given instance each time it's called", () -> {
        final Integer firstValue = test().cached().get();
        assertThat(firstValue).isEqualTo(134);
        final Integer secondValue = test().cached().get();
        assertThat(secondValue).isSameAs(firstValue);
      });

    });
  }
}