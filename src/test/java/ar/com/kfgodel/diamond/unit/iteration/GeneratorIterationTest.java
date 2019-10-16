package ar.com.kfgodel.diamond.unit.iteration;

import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.iteration.GeneratorSpliterator;
import ar.com.kfgodel.nary.api.Nary;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests the iteration with a generator spliterator
 * Created by kfgodel on 13/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class GeneratorIterationTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("a generator iteration", () -> {

      it("starts with a supplied value", () -> {
        GeneratorSpliterator<Integer> integerSpliterator = GeneratorSpliterator.create(Nary.of(1));
        assertThat(integerSpliterator.toStream().collect(Collectors.toList()))
          .isEqualTo(Arrays.asList(1));
      });

      it("uses a function to get the next item", () -> {
        GeneratorSpliterator<Integer> infiniteSpliterator = GeneratorSpliterator.create(Nary.of(1), (integer) -> Nary.of(integer + 1));
        assertThat(infiniteSpliterator.toStream().filter((integer) -> integer % 2 == 0).findFirst().get())
          .isEqualTo(2);
      });

      it("iteration finishes when last value generator returns an empty optional", () -> {
        GeneratorSpliterator<Integer> integerSpliterator = GeneratorSpliterator.create(Nary.of(1), (integer) -> (integer == 4) ? Nary.empty() : Nary.of(integer + 1));
        assertThat(integerSpliterator.toStream().collect(Collectors.toList()))
          .isEqualTo(Arrays.asList(1, 2, 3, 4));
      });

      it("has characteristics supplied to describe it", () -> {
        GeneratorSpliterator<Integer> spliterator = GeneratorSpliterator.create(Nary.of(1), (integer) -> Nary.of(integer + 1), Spliterator.NONNULL | Spliterator.DISTINCT | Spliterator.CONCURRENT);
        assertThat(spliterator.characteristics())
          .isEqualTo(Spliterator.NONNULL | Spliterator.DISTINCT | Spliterator.CONCURRENT);
      });

      it("can be used to iterate an empty set", () -> {
        GeneratorSpliterator<Integer> integerSpliterator = GeneratorSpliterator.create(Nary.empty());
        assertThat(integerSpliterator.toStream().collect(Collectors.toList()))
          .isEqualTo(Arrays.asList());
      });

    });
  }
}