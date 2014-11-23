package ar.com.kfgodel.diamond.unit.streams;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.hashcode.Hashcodes;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * This type verifies the correct hashcode implementation for streams
 *
 * Created by kfgodel on 23/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class StreamHashTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("hashcode for streams", ()->{

            it("consumes the stream",()->{
                Stream<Integer> stream = Stream.of(1);

                Hashcodes.forElementsIn(stream);

                try {
                    stream.findFirst();
                    failBecauseExceptionWasNotThrown(IllegalStateException.class);
                } catch (IllegalStateException e) {
                    assertThat(e).hasMessage("stream has already been operated upon or closed");
                }
            });

            it("is defined as collection hashcode",()->{
                int emptyStreamHashcode = Hashcodes.forElementsIn(Stream.empty());

                assertThat(emptyStreamHashcode).isEqualTo(Arrays.asList().hashCode());

                int streamHashcode = Hashcodes.forElementsIn(Stream.of(1,2,3));

                assertThat(streamHashcode).isEqualTo(Arrays.asList(1,2,3).hashCode());

            });   

        });
    }
}