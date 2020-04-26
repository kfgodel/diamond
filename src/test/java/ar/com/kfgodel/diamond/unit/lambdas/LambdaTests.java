package ar.com.kfgodel.diamond.unit.lambdas;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.invokable.Invokable;
import ar.com.kfgodel.diamond.api.lambdas.Lambda;
import ar.com.kfgodel.diamond.api.parameters.ExecutableParameter;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import info.kfgodel.jspek.api.variable.Variable;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;
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

        it("runnable", () -> {
          Lambda lambda = Diamond.lambdas().fromRunnable(() -> LOG.debug("Running as runnable"));

          assertThat(lambda.parameters().count()).isEqualTo(0);
          assertThat(lambda.returnType().name()).isEqualTo("void");
        });

        it("consumer", () -> {
          Lambda lambda = Diamond.lambdas().fromConsumer((arg1) -> LOG.debug("Running as consumer: " + arg1));

          assertThat(lambda.parameters().count()).isEqualTo(1);
          assertThat(lambda.returnType().name()).isEqualTo("void");
        });

        it("biconsumer", () -> {
          Lambda lambda = Diamond.lambdas().fromBiConsumer((arg1, arg2) -> LOG.debug("Running as bi-consumer: " + arg1 + arg2));

          assertThat(lambda.parameters().count()).isEqualTo(2);
          assertThat(lambda.returnType().name()).isEqualTo("void");
        });

        it("supplier", () -> {
          Lambda lambda = Diamond.lambdas().fromSupplier(() -> 1);

          assertThat(lambda.parameters().count()).isEqualTo(0);
          assertThat(lambda.returnType().name()).isEqualTo("Object");
        });

        it("function", () -> {
          Lambda lambda = Diamond.lambdas().fromFunction((arg1) -> 1);

          assertThat(lambda.parameters().count()).isEqualTo(1);
          assertThat(lambda.returnType().name()).isEqualTo("Object");
        });

        it("bifunction", () -> {
          Lambda lambda = Diamond.lambdas().fromBiFunction((arg1, arg2) -> 1);

          assertThat(lambda.parameters().count()).isEqualTo(2);
          assertThat(lambda.returnType().name()).isEqualTo("Object");
        });

        it("predicate", () -> {
          Lambda lambda = Diamond.lambdas().fromFunction((arg1) -> false);

          assertThat(lambda.parameters().count()).isEqualTo(1);
          assertThat(lambda.returnType().name()).isEqualTo("Object");
        });

        it("invokable", () -> {
          Lambda lambda = Diamond.lambdas().fromInvokable(this::returnOne);

          assertThat(lambda.parameters().count()).isEqualTo(1);
          assertThat(lambda.returnType().name()).isEqualTo("Object");
        });

      });

      describe("parameters", () -> {

        it("are generically named", () -> {
          Lambda lambda = Diamond.lambdas().fromBiConsumer((one, two) -> one.equals(two));

          List<String> argNames = lambda.parameters()
            .map(ExecutableParameter::name)
            .collect(Collectors.toList());

          assertThat(argNames).isEqualTo(Lists.newArrayList("arg0", "arg1"));
        });

        it("have a type according to the expression", () -> {
          Lambda lambda = Diamond.lambdas().fromBiConsumer((one, two) -> one.equals(two));

          List<String> argNames = lambda.parameters()
            .map((parameter) -> parameter.declaredType().name())
            .collect(Collectors.toList());

          assertThat(argNames).isEqualTo(Lists.newArrayList("Object", "Object"));
        });

      });

      describe("return type", () -> {

        it("is Object due to compiler type inference limitations", () -> {
          Lambda lambda = Diamond.lambdas().fromSupplier(() -> 1);

          assertThat(lambda.returnType().name()).isEqualTo("Object");
        });
      });

      describe("can be invoked", () -> {

        Variable<Integer> argumentCount = Variable.create();

        context().lambda(() -> {
          return Diamond.lambdas().fromInvokable(new Invokable() {
            @Override
            public <R> R invoke(Object... arguments) {
              return (R) argumentCount.set(arguments.length).get();
            }
          });
        });

        it("as runnable", () -> {

          context().lambda().asLambda().run();

          assertThat(argumentCount.get()).isEqualTo(0);
        });
        it("as consumer", () -> {
          context().lambda().asLambda().accept(1);

          assertThat(argumentCount.get()).isEqualTo(1);
        });
        it("as bi-consumer", () -> {
          context().lambda().asLambda().accept(1, 2);

          assertThat(argumentCount.get()).isEqualTo(2);
        });
        it("as supplier", () -> {
          context().lambda().asLambda().get();

          assertThat(argumentCount.get()).isEqualTo(0);
        });
        it("as function", () -> {
          context().lambda().asLambda().apply(1);

          assertThat(argumentCount.get()).isEqualTo(1);
        });

        it("as pseudo bi-function due to incompatible inheritance", () -> {
          context().lambda().asLambda().apply(1, 2);

          assertThat(argumentCount.get()).isEqualTo(2);
        });

        it("as predicate", () -> {
          context().lambda(() -> Diamond.lambdas().fromInvokable(new Invokable() {
            @Override
            public <R> R invoke(Object... arguments) {
              return (R)Boolean.valueOf(argumentCount.set(arguments.length) != null);
            }
          }));

          context().lambda().asLambda().test(1);

          assertThat(argumentCount.get()).isEqualTo(1);
        });
        it("as invokable", () -> {
          context().lambda().asLambda().invoke(1, 2, 3, 4, 5);

          assertThat(argumentCount.get()).isEqualTo(5);
        });

      });

      describe("equality", () -> {
        it("is true if both represent the same code", () -> {
          Function code = this::method;
          Lambda oneLambda = Diamond.lambdas().fromFunction(code);
          Lambda otherLambda = Diamond.lambdas().fromFunction(code);
          assertThat(oneLambda).isEqualTo(otherLambda);
        });

        it("is false if they represent different code", () -> {
          Lambda oneLambda = Diamond.lambdas().fromFunction(this::method);
          Lambda otherLambda = Diamond.lambdas().fromFunction(this::otherMethod);
          assertThat(oneLambda).isNotEqualTo(otherLambda);
        });
      });


    });

  }

  private <R> R returnOne(Object... objects) {
    return (R)Integer.valueOf(1);
  }

  private Object otherMethod(Object o) {
    return null;
  }

  private boolean method(Object o) {
    return false;
  }
}