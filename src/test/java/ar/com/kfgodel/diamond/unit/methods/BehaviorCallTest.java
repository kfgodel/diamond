package ar.com.kfgodel.diamond.unit.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.members.TypeBehavior;
import ar.com.kfgodel.diamond.api.members.call.BehaviorCall;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.methods.MethodCallTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of a method call
 * Created by kfgodel on 17/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class BehaviorCallTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a behavior call", () -> {
            it("is a method or constructor with arguments defined",()->{
                TypeMethod method = Diamond.of(MethodCallTestObject.class).methods().named("createdWith").get();

                BehaviorCall call = method.withArguments(1);

                assertThat(call).isNotNull();
            }); 
            
            it("can be used to invoke same method and arguments on different instances",()->{
                TypeMethod method = Diamond.of(MethodCallTestObject.class).methods().named("createdWith").get();
                BehaviorCall call = method.withArguments("a");


                MethodCallTestObject firstInstance = new MethodCallTestObject("a", "b", "c");
                MethodCallTestObject secondInstance = new MethodCallTestObject("d", "e", "f");

                assertThat(call.<Boolean>invokeOn(firstInstance)).isEqualTo(true);
                assertThat(call.<Boolean>invokeOn(secondInstance)).isEqualTo(false);
            });
            
            it("can be used to invoke same constructor (or static method) with implicit arguments several times",()->{
                TypeConstructor constructor = Diamond.of(MethodCallTestObject.class).constructors().withNativeParameters(Object.class,Object.class,Object.class).get();
                BehaviorCall call = constructor.withArguments(1,2,3);

                MethodCallTestObject firstCreated = call.invoke();
                assertThat(firstCreated).isNotNull();

                MethodCallTestObject secondCreated = call.invoke();
                assertThat(secondCreated).isNotNull();

                assertThat(firstCreated).isNotSameAs(secondCreated);
            });   

            it("has an argument bindable behavior)",()->{
                TypeMethod method = Diamond.of(MethodCallTestObject.class).methods().named("createdWith").get();
                BehaviorCall call = method.withArguments(1);

                TypeBehavior behavior =  call.boundBehavior();

                assertThat(behavior).isNotNull();
                assertThat(behavior).isSameAs(method);
            }); 
            
            it("has defined implicit arguments",()->{
                TypeMethod method = Diamond.of(MethodCallTestObject.class).methods().named("createdWith").get();
                BehaviorCall call = method.withArguments(1);

                Object[] arguments = call.arguments();

                assertThat(arguments[0]).isEqualTo(1);
            });

            describe("as function", () -> {

                context().typeInstance(()-> Diamond.of(MethodCallTestObject.class));
                context().method(() -> context().typeInstance().methods().named(context().name()).get());

                beforeEach(() -> {
                    MethodCallTestObject.staticInvoked = "none";
                });

                it("can be used as a supplier for static non-void methods",()->{
                    context().name(() -> "supplier");

                    Supplier<Object> asSupplier = context().method().withArguments(1, 2, 3);
                    asSupplier.get();

                    assertThat(MethodCallTestObject.staticInvoked).isEqualTo("supplier(1, 2, 3)");
                });

                it("can be used as runnable for void static methods",()->{
                    context().name(() -> "runnable");

                    Runnable asRunnable = context().method().withArguments(1, 2, 3);
                    asRunnable.run();

                    assertThat(MethodCallTestObject.staticInvoked).isEqualTo("runnable(1, 2, 3)");
                });

                it("can be used as a function for instance non-void methods",()->{
                    MethodCallTestObject instance = new MethodCallTestObject("a", "b", "c");
                    context().name(() -> "function");

                    Function<Object, Object> asFunction = context().method().withArguments(1, 2, 3);
                    asFunction.apply(instance);

                    assertThat(instance.instanceInvoked).isEqualTo("function(1, 2, 3)");
                });

                it("can be used as consumer for void instance methods",()->{
                    MethodCallTestObject instance = new MethodCallTestObject("a", "b", "c");
                    context().name(() -> "consumer");

                    Consumer<Object> asFunction = context().method().withArguments(1, 2, 3);
                    asFunction.accept(instance);

                    assertThat(instance.instanceInvoked).isEqualTo("consumer(1, 2, 3)");
                });
            });
        });
    }
}