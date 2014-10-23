package ar.com.kfgodel.diamond.value;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.lazyvalue.impl.SuppliedValue;
import org.junit.runner.RunWith;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type test the behavior of a lazily defined value
 * Created by kfgodel on 21/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class LazyValueTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a lazy value", ()->{
            Supplier<Integer> generator = () -> 42;
            beforeEach(()->{
                context().value(() -> SuppliedValue.lazilyBy(generator));
            });

            it("has an undefined value", ()->{
                assertThat(context().value().isAlreadyDefined())
                        .isFalse();
            });
            it("has a supplier lambda to define its value", ()->{
                assertThat(context().value().generator().get()).
                        isEqualTo(generator);
            });
            it("value is defined the first time it's accessed", ()->{
                Integer generated = context().value().get();
                assertThat(generated).isEqualTo(42);
            });
            it("value it's retained once defined", ()->{
                Integer firstTime = context().value().get();
                Integer secondTime = context().value().get();
                assertThat(firstTime).isSameAs(secondTime);
            });
            it("supplier lambda is discarded once defined, to prevent unintentional memory leaks", ()->{
                context().value().get();
                Optional<Supplier<Integer>> discardedLambda = context().value().generator();
                assertThat(discardedLambda.isPresent()).isFalse();
            });

            it("value can be optionally accessed if not yet defined", ()->{
                Optional<Integer> optionalValue = context().value().getIfDefined();
                assertThat(optionalValue.isPresent()).isFalse();
            });

            describe("eagerly defined", () -> {

                beforeEach(()->{
                    context().value(() -> SuppliedValue.eagerlyFrom(13));
                });

                it("doesn't have an undefined value",()->{
                    assertThat(context().value().isAlreadyDefined())
                            .isTrue();
                });

                it("doesn't have a supplier lambda", ()->{
                    assertThat(context().value().generator().isPresent()).
                            isFalse();
                });

                it("has the defined value",()->{
                    Integer value = context().value().get();
                    assertThat(value).isEqualTo(13);
                });

            });

        });
    }
}
