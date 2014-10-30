package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.exceptions.DiamondException;
import ar.com.kfgodel.diamond.testobjects.methods.FunctionalInstanceMethodTestObject;
import ar.com.kfgodel.diamond.testobjects.methods.FunctionalStaticMethodTestObject;
import org.junit.runner.RunWith;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * this type verifies the behavior of methods as functional elements
 * Created by kfgodel on 26/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FunctionalMethodTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("method as a function", () -> {

            context().typeInstance(() -> Diamond.of(context().testClass()));

            describe("for static methods", () -> {
                context().testClass(()-> FunctionalStaticMethodTestObject.class);

                it("is a runnable to call a no arg void method",()->{
                    //Pre-condition
                    FunctionalStaticMethodTestObject.lastResult = 0;

                    Runnable runnable = context().typeInstance().methods().existingNamed("runnable");
                    runnable.run();

                    // After calling the method, lastResult gets updated
                    assertThat(FunctionalStaticMethodTestObject.lastResult).isEqualTo(10);
                }); 
                
                it("is a consumer to call a one arg void method",()->{
                    //Pre-condition
                    FunctionalStaticMethodTestObject.lastResult = 0;

                    Consumer<Object> consumer= context().typeInstance().methods().existingNamed("consumer");
                    consumer.accept(4);

                    assertThat(FunctionalStaticMethodTestObject.lastResult).isEqualTo(4);
                });

                it("is a biConsumer to call a two arg void method",()->{
                    //Pre-condition
                    FunctionalStaticMethodTestObject.lastResult = 0;

                    BiConsumer<Object, Object> biConsumer= context().typeInstance().methods().existingNamed("biConsumer");
                    biConsumer.accept(4, 1);

                    assertThat(FunctionalStaticMethodTestObject.lastResult).isEqualTo(5);
                }); 
                
                it("is a supplier to call a no-arg non-void method",()->{
                    Supplier<Object> supplier= context().typeInstance().methods().existingNamed("supplier");
                    Object value = supplier.get();

                    assertThat(value).isEqualTo(1);

                }); 
                
                it("is a function to call a one arg non-void method",()->{
                    Function<Object, Object> function= context().typeInstance().methods().existingNamed("function");
                    Object value = function.apply(85);

                    assertThat(value).isEqualTo(85);
                });

                it("throws an exception if functionally called with wrong argument count",()->{
                    Consumer<Object> consumer = context().typeInstance().methods().existingNamed("biConsumer");

                    try {
                        consumer.accept(1);
                        failBecauseExceptionWasNotThrown(DiamondException.class);
                    } catch (Exception e) {
                        assertThat(e).hasMessage("Invocation rejected for method[public static void ar.com.kfgodel.diamond.testobjects.methods.FunctionalStaticMethodTestObject.biConsumer(int,int)] due to wrong arguments[1]");
                    }
                });

                it("throws an exception if functionally called with wrong argument types",()->{
                    Consumer<Object> consumer = context().typeInstance().methods().existingNamed("consumer");

                    try {
                        consumer.accept("a");
                        failBecauseExceptionWasNotThrown(DiamondException.class);
                    } catch (Exception e) {
                        assertThat(e).hasMessage("Invocation rejected for method[public static void ar.com.kfgodel.diamond.testobjects.methods.FunctionalStaticMethodTestObject.consumer(int)] due to wrong arguments[a]");
                    }
                });
                
            });

            describe("for instance methods", () -> {
                context().testClass(()-> FunctionalInstanceMethodTestObject.class);
                context().object(() -> context().typeInstance().newInstance());

                it("is a consumer to call a no-arg void method",()->{
                    FunctionalInstanceMethodTestObject object = context().object();

                    Consumer<Object> consumer= context().typeInstance().methods().existingNamed("consumer");
                    consumer.accept(object);

                    assertThat(object.getLastResult()).isEqualTo(7);
                });
                
                it("is a biConsumer to call a one arg void method",()->{
                    FunctionalInstanceMethodTestObject object = context().object();

                    BiConsumer<Object,Object> biConsumer= context().typeInstance().methods().existingNamed("biConsumer");
                    biConsumer.accept(object,13);

                    assertThat(object.getLastResult()).isEqualTo(13);
                }); 
                
                it("is a function to call a no-arg non-void method",()->{
                    FunctionalInstanceMethodTestObject object = context().object();

                    Function<Object,Object> function= context().typeInstance().methods().existingNamed("function");
                    Object value = function.apply(object);

                    assertThat(value).isEqualTo(21);
                });   
                
                it("throws an exception if functionally called as a static method",()->{
                    Supplier<Object> supplier = context().typeInstance().methods().existingNamed("function");

                    try {
                        supplier.get();
                        failBecauseExceptionWasNotThrown(DiamondException.class);
                    } catch (Exception e) {
                        assertThat(e).hasMessage("Instance invocation for method[public int ar.com.kfgodel.diamond.testobjects.methods.FunctionalInstanceMethodTestObject.function()] cannot be done on null instance");
                    }
                });
                
            });


        });

    }
}
