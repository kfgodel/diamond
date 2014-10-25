package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.testobjects.invocations.MethodInvocationTestObject;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the invocation capabilities of a TypeMethod
 * Created by kfgodel on 25/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodInvocationTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("TypeMethod as method invoker", () -> {

            context().object(MethodInvocationTestObject::new);
            context().typeInstance(() -> Diamond.of(context().object().getClass()));
//            context().method(() -> context().typeInstance().methods().existingNamed(context().name()));


            describe("accessibility", () -> {
                it("can invoke public methods",()->{
                    context().name(() -> "publicMethod");

                    Object result = context().method().invokeOn(context().object());

                    assertThat(result).isEqualTo(1);
                });
                it("can invoke protected methods",()->{
                    context().name(() -> "protectedMethod");

                    Object result = context().method().invokeOn(context().object());

                    assertThat(result).isEqualTo(2);

                });
                it("can invoke default methods",()->{
                    context().name(() -> "defaultMethod");

                    Object result = context().method().invokeOn(context().object());

                    assertThat(result).isEqualTo(3);

                });
                it("can invoke private methods",()->{
                    context().name(() -> "privateMethod");

                    Object result = context().method().invokeOn(context().object());

                    assertThat(result).isEqualTo(4);
                });
            });

        });

    }
}