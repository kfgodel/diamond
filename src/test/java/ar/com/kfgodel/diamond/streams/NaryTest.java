package ar.com.kfgodel.diamond.streams;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.nary.api.MoreThanOneElementException;
import ar.com.kfgodel.nary.impl.NaryFromNative;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the behavior of a class that acts as stream and optional.<br>
 * Created by kfgodel on 06/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class NaryTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a Nary", () -> {

            describe("creation", () -> {

                it("can be made from a stream",()->{
                    NaryFromNative<Integer> nary = NaryFromNative.create(Stream.of(1, 2, 3));

                    assertThat(nary).isNotNull();
                });

                it("can be made from an optional",()->{
                    NaryFromNative<Integer> nary = NaryFromNative.create(Optional.of(1));

                    assertThat(nary).isNotNull();
                });
            });

            it("can behave as a stream",()->{
                NaryFromNative<Integer> nary = NaryFromNative.create(Stream.of(1, 2, 3));

                assertThat(nary.collect(Collectors.toList()))
                        .isEqualTo(Arrays.asList(1,2,3));
            });

            it("can behave as an optional",()->{
                NaryFromNative<Integer> nary = NaryFromNative.create(Optional.of(1));

                assertThat(nary.isPresent()).isTrue();
            });

            describe("created from a stream", () -> {

                describe("empty", () -> {

                    context().nary(()-> NaryFromNative.create(Stream.empty()));

                    it("answers false to is present", () -> {
                        assertThat(context().nary().isPresent()).isFalse();
                    });

                    it("throws an error when trying to access the optional element", () -> {
                        try {
                            context().nary().get();
                            failBecauseExceptionWasNotThrown(NoSuchElementException.class);
                        } catch (NoSuchElementException e) {
                            assertThat(e).hasMessage("No value present");
                        }
                    });
                });

                describe("of one element", () -> {

                    context().nary(()-> NaryFromNative.create(Stream.of(1)));

                    it("answers true to is present",()->{
                        assertThat(context().nary().isPresent()).isTrue();
                    });   
                    
                    it("gets the only value",()->{
                        assertThat(context().nary().get()).isEqualTo(1);
                    });   
                });

                describe("for more than one element", () -> {

                    context().nary(()-> NaryFromNative.create(Stream.of(1, 2, 3)));

                    it("throws an exception if used as optional",()->{
                        try {
                            context().nary().get();
                            failBecauseExceptionWasNotThrown(MoreThanOneElementException.class);
                        } catch (MoreThanOneElementException e) {
                            assertThat(e).hasMessage("There's more than one element in the stream to create an optional: [1, 2]");
                        }
                    });
                });
            });

            describe("created from an optional", () -> {


                describe("empty", () -> {

                    context().nary(()-> NaryFromNative.create(Optional.empty()));

                    it("behaves as an empty stream", () -> {
                        assertThat(context().nary().iterator().hasNext()).isFalse();
                    });
                });


                describe("with a value", () -> {

                    context().nary(()-> NaryFromNative.create(Optional.of(1)));

                    it("behaves as a one element stream",()->{
                        List<String> stringValues = context().nary().map(Object::toString).collect(Collectors.toList());
                        assertThat(stringValues)
                                .isEqualTo(Arrays.asList("1"));
                    });
                });
            });
        });
    }
}