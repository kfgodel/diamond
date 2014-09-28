package ar.com.kfgodel.diamond.generics;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.impl.generics.parameters.SubstitutionAnalizer;
import ar.com.kfgodel.diamond.impl.generics.parameters.ValueSubstituter;
import ar.com.kfgodel.diamond.impl.generics.parameters.ValueSubstitution;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type the resolution of supertype type arguments
 * Created by kfgodel on 27/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class ValueSubstituterTest extends JavaSpec<SubstituterTestContext> {
    @Override
    public void define() {
        describe("super args supplier", ()->{

            describe("can be used with any type of object", ()->{

                // Base values for all scenarios
                context().subtypeArguments(()-> Arrays.<Object>asList("1", "2", "3"));
                context().subtypeParameters(()-> Arrays.<Object>asList("A", "B", "C"));
                context().superTypeArguments(()->Arrays.<Object>asList("A", "B", "C"));

                /**
                 * Executed on each test, it substitutes supertype args with subtype args based on subtype parameters
                 * and supertype arguments relation (emulating type extension)
                 */
                context().replacedSuperTypeArguments(() -> {
                    List<ValueSubstitution> substitutions = SubstitutionAnalizer.analyze(context().subtypeParameters(), context().superTypeArguments());
                    ValueSubstituter<Object> substituter = ValueSubstituter.create(substitutions);
                    List<Object> replacedSuperTypeArguments = new ArrayList<>(context().superTypeArguments());
                    substituter.accept(context().subtypeArguments(), replacedSuperTypeArguments);
                    return replacedSuperTypeArguments;
                });

                it("matches subtype arguments and supertype arguments by a composed matching", ()->{
                    assertThat(context().replacedSuperTypeArguments())
                            .isEqualTo(Arrays.asList("1","2","3"));
                });

                it("matches subtype arguments and parameters by position", ()->{
                    context().subtypeArguments(()-> Arrays.<Object>asList(3, 2, 1, 0, -1));

                    assertThat(context().replacedSuperTypeArguments())
                            .isEqualTo(Arrays.asList(3,2,1));
                });

                it("matches subtype parameters and supertype arguments by equality", ()->{
                    context().superTypeArguments(()-> Arrays.<Object>asList("A", "A", "B"));

                    assertThat(context().replacedSuperTypeArguments())
                            .isEqualTo(Arrays.asList("1","1","2"));
                });

                it("uses original supertye arg if no subtype arg matches", ()->{
                    context().subtypeParameters(()-> Arrays.<Object>asList("C","Z"));

                    assertThat(context().replacedSuperTypeArguments())
                            .isEqualTo(Arrays.asList("A","B","1"));
                });

            });

        });
    }
}
