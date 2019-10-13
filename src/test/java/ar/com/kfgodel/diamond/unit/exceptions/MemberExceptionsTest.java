package ar.com.kfgodel.diamond.unit.exceptions;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.naming.Named;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.exceptions.MemberExceptionsTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the correct retrieval of exception for member types
 * Created by kfgodel on 02/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MemberExceptionsTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("exceptions", () -> {

            context().typeInstance(()-> Diamond.of(MemberExceptionsTestObject.class));

            describe("on fields", () -> {

                context().field(()-> context().typeInstance().fields().named("field").get());

                it("is an empty stream", () -> {
                    Stream<TypeInstance> exceptions = context().field().declaredExceptions();

                    assertThat(exceptions.count()).isEqualTo(0);
                });
            });

            describe("on methods", () -> {

                context().method(() -> context().typeInstance().methods().named("method").get());

                it("is the full exception list declared by the method", () -> {
                    Stream<TypeInstance> exceptions = context().method().declaredExceptions();

                    assertThat(exceptions.map(Named::name).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("RuntimeException", "Exception", "Throwable"));
                });
            });

            describe("on constructors", () -> {

                context().constructor(()-> context().typeInstance().constructors().niladic().get());

                it("is the full exception list as methods",()->{
                    Stream<TypeInstance> exceptions = context().constructor().declaredExceptions();

                    assertThat(exceptions.map(Named::name).collect(Collectors.toList()))
                            .isEqualTo(Arrays.asList("IllegalArgumentException"));
                });   
            });
        });
    }
}