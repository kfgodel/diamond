package ar.com.kfgodel.lazyvalue.impl;

import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.function.Supplier;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * This type test the behavior of a lazily defined value
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class CachedValueTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a cached value", () -> {

      describe("lazily defined", () -> {
        test().cached(() -> CachedValue.from(test().supplier()));
        test().supplier(() -> Mockito.mock(Supplier.class, Mockito.RETURNS_SMART_NULLS));

        it("doesn't call its supplier until needed", () -> {
          Mockito.verify(test().supplier(), never()).get();
        });

        it("calls its supplier the first time the value is needed", () -> {
          test().cached().get();

          Mockito.verify(test().supplier(), times(1)).get();
        });

        it("doesn't call it again, after the first time", () -> {
          test().cached().get();
          test().cached().get();
          test().cached().get();

          Mockito.verify(test().supplier(), times(1)).get();
        });

      });

    });
  }
}
