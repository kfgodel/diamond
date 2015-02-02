package ar.com.kfgodel.diamond.unit.lambdas;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kfgodel on 01/02/15.
 */
@RunWith(JavaSpecRunner.class)
public class LambdaTests extends JavaSpec<DiamondTestContext> {
    public static Logger LOG = LoggerFactory.getLogger(LambdaTests.class);

    @Override
    public void define() {
        describe("a lambda", () -> {
            
            describe("obtained from an inline expression", () -> {
                
                it("runnable",()->{
                    Lambda lambda = Diamond.lambdas().fromRunnable(() -> LOG.debug("Running as runnable"));
                    
                    assertThat(lambda.parameters().count()).isEqualTo(0);
                    assertThat(lambda.returnType().name()).isEqualTo("void");
                }); 
                
                it("consumer",()->{
                    Lambda lambda = Diamond.lambdas().fromConsumer((arg1) -> LOG.debug("Running as consumer: " + arg1));

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("void");
                });
                
                it("biconsumer",()->{
                    Lambda lambda = Diamond.lambdas().fromBiConsumer((arg1, arg2) -> LOG.debug("Running as bi-consumer: " + arg1 + arg2));

                    assertThat(lambda.parameters().count()).isEqualTo(2);
                    assertThat(lambda.returnType().name()).isEqualTo("void");
                }); 
                
                it("supplier",()->{
                    Lambda lambda = Diamond.lambdas().fromSupplier(() -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(0);
                    assertThat(lambda.returnType().name()).isEqualTo("T");
                });
                
                it("function",()->{
                    Lambda lambda = Diamond.lambdas().fromFunction((arg1) -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("R");
                });
                
                it("bifunction",()->{
                    Lambda lambda = Diamond.lambdas().fromBiFunction((arg1, arg2) -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(2);
                    assertThat(lambda.returnType().name()).isEqualTo("R");
                });

                it("predicate",()->{
                    Lambda lambda = Diamond.lambdas().fromFunction((arg1) -> false);

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("R");
                });

                it("invokable",()->{
                    Lambda lambda = Diamond.lambdas().fromInvokable((args) -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("Object");
                });

            });

            describe("parameters", () -> {
                
                it("are generically named",()->{
                    Lambda lambda = Diamond.lambdas().fromBiConsumer((one, two) -> one.equals(two));

                    List<String> argNames = lambda.parameters()
                            .map(ExecutableParameter::name)
                            .collect(Collectors.toList());
                    
                    assertThat(argNames).isEqualTo(Lists.newArrayList("arg0", "arg1"));
                }); 
                
                it("have a type according to the expression",()->{
                    Lambda lambda = Diamond.lambdas().fromBiConsumer((one, two) -> one.equals(two));

                    List<String> argNames = lambda.parameters()
                            .map((parameter)-> parameter.declaredType().name())
                            .collect(Collectors.toList());

                    assertThat(argNames).isEqualTo(Lists.newArrayList("T", "U"));
                });

            });

            describe("return type", () -> {
                
                it("is usually Object due to compiler type inference limitations",()->{
                    Lambda lambda = Diamond.lambdas().fromSupplier(() -> 1);

                    assertThat(lambda.returnType().name()).isEqualTo("T");
                });   
            });

            describe("can be invoked",()->{

                Variable<Integer> argumentCount = Variable.create();
                
                context().lambda(()-> Diamond.lambdas().fromInvokable((args)-> argumentCount.set(args.length).get()));
                
                it("as runnable", () -> {

                    context().lambda().asFunction().run();

                    assertThat(argumentCount.get()).isEqualTo(0);
                });
                it("as consumer",()->{
                    context().lambda().asFunction().accept(1);

                    assertThat(argumentCount.get()).isEqualTo(1);
                });
                it("as bi-consumer",()->{
                    context().lambda().asFunction().accept(1, 2);

                    assertThat(argumentCount.get()).isEqualTo(2);
                });
                it("as supplier",()->{
                    context().lambda().asFunction().get();

                    assertThat(argumentCount.get()).isEqualTo(0);
                }); 
                it("as function",()->{
                    context().lambda().asFunction().apply(1);

                    assertThat(argumentCount.get()).isEqualTo(1);
                });
                
                it("as pseudo bi-function due to incompatible inheritance", () -> {
                    context().lambda().asFunction().apply(1,2);

                    assertThat(argumentCount.get()).isEqualTo(2);
                });
                
                it("as predicate",()->{
                    context().lambda(()-> Diamond.lambdas().fromInvokable((args)-> argumentCount.set(args.length) != null));

                    context().lambda().asFunction().test(1);

                    assertThat(argumentCount.get()).isEqualTo(1);
                });
                it("as invokable",()->{
                    context().lambda().asFunction().invoke(1, 2, 3, 4, 5);

                    assertThat(argumentCount.get()).isEqualTo(5);
                });

            });

        });

    }
}