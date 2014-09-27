package ar.com.kfgodel.diamond.generics;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.impl.fragments.SuperTypeArgsMatcher;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type the resolution of supertype type arguments
 * Created by kfgodel on 27/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class SuperTypeArgsMatcherTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("super args supplier", ()->{

            describe("can be used with any type of object", ()->{

                List<Object> subtypeArgs = Arrays.<Object>asList("1", "2", "3");
                List<Object> subtypeParameters = Arrays.<Object>asList("A", "B", "C");
                List<Object> supertypeArgs = Arrays.<Object>asList("A", "B", "C");

                it("matches subtype arguments and supertype arguments by a composed matching", ()->{
                    SuperTypeArgsMatcher<Object> supplier = SuperTypeArgsMatcher.create(subtypeArgs.stream(), subtypeParameters.stream(), supertypeArgs.stream());

                    assertThat(supplier.get().collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("1","2","3"));
                });

                it("matches subtype arguments and parameters by position", ()->{
                    Stream<Object> changedSubArgs = Arrays.<Object>asList(3, 2, 1, 0, -1).stream();

                    SuperTypeArgsMatcher<Object> supplier = SuperTypeArgsMatcher.create(changedSubArgs, subtypeParameters.stream(), supertypeArgs.stream());

                    assertThat(supplier.get().collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList(3,2,1));
                });

                it("matches subtype parameters and supertype arguments by equality", ()->{
                    Stream<Object> changedsuperArgs = Arrays.<Object>asList("A", "A", "B").stream();

                    SuperTypeArgsMatcher<Object> supplier = SuperTypeArgsMatcher.create(subtypeArgs.stream(), subtypeParameters.stream() , changedsuperArgs);

                    assertThat(supplier.get().collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("1","1","2"));
                });

                it("uses original supertye arg if no subtype arg matches", ()->{
                    Stream<Object> changedParameters = Arrays.<Object>asList("C","Z").stream();

                    SuperTypeArgsMatcher<Object> supplier = SuperTypeArgsMatcher.create(subtypeArgs.stream(), changedParameters, supertypeArgs.stream());

                    assertThat(supplier.get().collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("A","B","1"));
                });

            });

        });
    }
}
