package ar.com.kfgodel.diamond.generics;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.api.types.reference.ReferenceOf;
import ar.com.kfgodel.diamond.testobjects.generics.GenericMembersTestObject;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the correct generics information for type members
 * Created by kfgodel on 01/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class GenericMembersTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("generics", () -> {

            context().typeInstance(()-> Diamond.of(GenericMembersTestObject.class));

            describe("for a field", () -> {
                context().field(()-> context().typeInstance().fields().named("field").get());
                
                it("contains an empty stream for generic parameters",()->{

                    Stream<TypeInstance> parameters = context().field().generics().parameters();

                    assertThat(parameters.count()).isEqualTo(0);
                });   
            });

            describe("for a method", () -> {
                context().method(()-> context().typeInstance().methods().named("method").get());
                
                it("contains only method declared type variables",()->{

                    Stream<TypeInstance> parameters = context().method().generics().parameters();

                    assertThat(parameters.map(TypeInstance::name).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("R"));
                });   

            });

            describe("for a constructor", () -> {
                context().constructor(() -> context().typeInstance().constructors().declaredFor(getTypeVariableS()).get());

                it("contains only constructor declared type variables",()->{

                    Stream<TypeInstance> parameters = context().constructor().generics().parameters();

                    assertThat(parameters.map(TypeInstance::name).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("S"));

                });   
            });
        });

    }

    private static <S> TypeInstance getTypeVariableS() {
        return Diamond.types().from(new ReferenceOf<S>(){}.getReferencedTypeVariable());
    }
}