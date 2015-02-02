package ar.com.kfgodel.diamond.unit.lambdas;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                    assertThat(lambda.returnType().name()).isEqualTo("Object");
                });
                
                it("function",()->{
                    Lambda lambda = Diamond.lambdas().fromFunction((arg1) -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("Object");
                });
                
                it("bifunction",()->{
                    Lambda lambda = Diamond.lambdas().fromBiFunction((arg1, arg2) -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(2);
                    assertThat(lambda.returnType().name()).isEqualTo("Object");
                });

                it("predicate",()->{
                    Lambda lambda = Diamond.lambdas().fromFunction((arg1) -> false);

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("Boolean");
                });

                it("invokable",()->{
                    Lambda lambda = Diamond.lambdas().fromInvokable((args) -> 1);

                    assertThat(lambda.parameters().count()).isEqualTo(1);
                    assertThat(lambda.returnType().name()).isEqualTo("Object");
                });

            });

            describe("parameters", () -> {
                
                it("are generically named",()->{
                    
                }); 
                
                it("have a type according to the expression",()->{
                    
                });   

            });

            describe("return type", () -> {
                it("is usually Object due to compiler inference",()->{
                    
                });   
            });

            describe("can be invoked",()->{

                Variable<Integer> cantidadArgumentos = Variable.create();
                
                context().lambda(()-> Diamond.lambdas().fromInvokable((args)-> cantidadArgumentos.set(args.length).get()));
                
                it("as runnable", () -> {

                    context().lambda().asFunction().run();

                    assertThat(cantidadArgumentos.get()).isEqualTo(0);
                });
                it("as consumer",()->{
                    context().lambda().asFunction().accept(1);

                    assertThat(cantidadArgumentos.get()).isEqualTo(1);
                });
                it("as bi-consumer",()->{
                    context().lambda().asFunction().accept(1,2);

                    assertThat(cantidadArgumentos.get()).isEqualTo(2);
                });
                it("as supplier",()->{
                    context().lambda().asFunction().get();

                    assertThat(cantidadArgumentos.get()).isEqualTo(0);
                }); 
                it("as function",()->{
                    context().lambda().asFunction().apply(1);

                    assertThat(cantidadArgumentos.get()).isEqualTo(1);
                });
                
                it("not as bi-function due to incompatible inheritability");
                
                it("as predicate",()->{
                    context().lambda().asFunction().test(1);

                    assertThat(cantidadArgumentos.get()).isEqualTo(1);
                });
                it("as invokable",()->{
                    context().lambda().asFunction().invoke(1,2,3,4,5);

                    assertThat(cantidadArgumentos.get()).isEqualTo(5);
                });

            });

        });

    }
}