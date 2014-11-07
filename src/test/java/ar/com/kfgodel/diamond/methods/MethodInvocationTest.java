package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.HaltedMethodInvocationException;
import ar.com.kfgodel.diamond.testobjects.invocations.MethodInvocationTestObject;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

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
            context().method(() -> context().typeInstance().methods().named(context().name()).get());


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

            it("throws a special exception for method's own exceptions",()->{
                context().name(()-> "exceptionMethod");

                try {
                    context().method().invokeOn(context().object());
                    failBecauseExceptionWasNotThrown(HaltedMethodInvocationException.class);
                } catch (HaltedMethodInvocationException e) {
                    assertThat(e).hasMessage("Invocation halted for method[public void ar.com.kfgodel.diamond.testobjects.invocations.MethodInvocationTestObject.exceptionMethod()] on instance[a test instance] and arguments[]: I don't finish successfully");
                    assertThat(e.getHaltingCause()).hasMessage("I don't finish successfully");
                    assertThat(e.getInvokedInstance()).isSameAs(context().object());
                    assertThat(e.getInvokedArguments()).isEmpty();
                    assertThat(e.getInvokedMethod().getName()).isEqualTo(context().name());
                }
            });   
            
        });

    }
}