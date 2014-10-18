package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.impl.methods.equality.MethodEquality;
import ar.com.kfgodel.diamond.testobjects.MethodEqualityTestObjectA;
import ar.com.kfgodel.diamond.testobjects.MethodEqualityTestObjectB;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of the method equality
 * Created by kfgodel on 16/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodEqualityTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("method equality", () -> {

            it("is reified by MethodEquality", () -> {
                TypeMethod voidNoArgA = getVoidNoArgAMethod();
                assertThat(MethodEquality.INSTANCE.areEquals(voidNoArgA, voidNoArgA)).
                        isTrue();
            });

            it("is false if names mismatch",()->{
                TypeMethod voidNoArgA = getVoidNoArgAMethod();
                TypeMethod voidNoArgB = getVoidNoArgBMethod();
                assertThat(voidNoArgA).isNotEqualTo(voidNoArgB);
            });

            it("is false if return type mismatch",()->{
                TypeMethod voidNoArgA = getVoidNoArgAMethod();
                TypeMethod stringNoArgA = getStringNoArgAMethod();
                assertThat(voidNoArgA).isNotEqualTo(stringNoArgA);
            });

            it("is false if method parameters count doesn't match",()->{
                TypeMethod voidNoArgA = getVoidNoArgAMethod();
                TypeMethod stringParameterMethod = getStringParameterAMethod();
                assertThat(voidNoArgA).isNotEqualTo(stringParameterMethod);
            });

            it("is false if method parameters type doesn't match",()->{
                TypeMethod stringParameterMethod = getStringParameterAMethod();
                TypeMethod numberParameterMethod = getNumberParameterAMethod();
                assertThat(stringParameterMethod).isNotEqualTo(numberParameterMethod);
            });


            it("is false if declaring type doesn't match", () -> {
                TypeMethod voidNoArgB = getVoidNoArgBMethod();
                TypeMethod voidNoArgBInOtherType = getVoidNoArgBInDifferentTypeMethod();
                assertThat(voidNoArgB).isNotEqualTo(voidNoArgBInOtherType);
            });

            it("is true if name, type and declaring type match", () -> {
                TypeMethod voidNoArgA1 = getVoidNoArgAMethod();
                TypeMethod voidNoArgA2 = getVoidNoArgAMethod();
                assertThat(voidNoArgA1).isEqualTo(voidNoArgA2);
            });
        });
    }

    private TypeMethod getNumberParameterAMethod() {
        try {
            return Diamond.methods().from(MethodEqualityTestObjectA.class.getDeclaredMethod("a", Number.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeMethod getStringParameterAMethod() {
        try {
            return Diamond.methods().from(MethodEqualityTestObjectA.class.getDeclaredMethod("a", String.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeMethod getStringNoArgAMethod() {
        try {
            return Diamond.methods().from(MethodEqualityTestObjectB.class.getDeclaredMethod("a"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeMethod getVoidNoArgBMethod() {
        try {
            return Diamond.methods().from(MethodEqualityTestObjectA.class.getDeclaredMethod("b"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeMethod getVoidNoArgBInDifferentTypeMethod() {
        try {
            return Diamond.methods().from(MethodEqualityTestObjectB.class.getDeclaredMethod("b"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }


    private TypeMethod getVoidNoArgAMethod() {
        try {
            return Diamond.methods().from(MethodEqualityTestObjectA.class.getDeclaredMethod("a"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }
}