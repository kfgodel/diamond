package ar.com.kfgodel.diamond.unit.constructors;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.impl.constructors.equality.ConstructorEquality;
import ar.com.kfgodel.diamond.unit.testobjects.equality.ConstructorEqualityTestObjectA;
import ar.com.kfgodel.diamond.unit.testobjects.equality.ConstructorEqualityTestObjectB;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the equality definition for constructors
 * Created by kfgodel on 16/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class ConstructorEqualityTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("constructor equality", () -> {

            it("is reified by ConstructorEquality", () -> {
                TypeConstructor niladicConstructor = getNiladicConstructor();
                assertThat(ConstructorEquality.INSTANCE.areEquals(niladicConstructor, niladicConstructor)).
                        isTrue();
            });
            
            it("is false if constructor parameters count doesn't match",()->{
                TypeConstructor niladicConstructor = getNiladicConstructor();
                TypeConstructor stringParameterConstructor = getStringParameterConstructor();
                assertThat(niladicConstructor).isNotEqualTo(stringParameterConstructor);
            });

            it("is false if constructor parameters type doesn't match",()->{
                TypeConstructor stringParameterConstructor = getStringParameterConstructor();
                TypeConstructor numberParameterConstructor = getNumberParameterConstructor();
                assertThat(stringParameterConstructor).isNotEqualTo(numberParameterConstructor);
            });

            it("is false if declaring type doesn't match", () -> {
                TypeConstructor niladicConstructor = getNiladicConstructor();
                TypeConstructor niladicConstructorInDifferentType = getNiladicConstructorInDifferentType();
                assertThat(niladicConstructor).isNotEqualTo(niladicConstructorInDifferentType);
            });
            
            it("is true if parameters and declaring type match",()->{
                TypeConstructor stringParameterConstructor1 = getStringParameterConstructor();
                TypeConstructor stringParameterConstructor2 = getStringParameterConstructor();
                assertThat(stringParameterConstructor1).isEqualTo(stringParameterConstructor2);
            });

            describe("hashcode", () -> {

                it("is equal if constructors are equals",()->{
                    TypeConstructor one = getStringParameterConstructor();
                    TypeConstructor other = getStringParameterConstructor();

                    assertThat(one).isNotSameAs(other);
                    assertThat(one).isEqualTo(other);
                    assertThat(one.hashCode()).isEqualTo(other.hashCode());
                });
            });
        });

    }

    private TypeConstructor getNumberParameterConstructor() {
        try {
            return Diamond.constructors().from(ConstructorEqualityTestObjectA.class.getDeclaredConstructor(Number.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private TypeConstructor getStringParameterConstructor() {
        try {
            return Diamond.constructors().from(ConstructorEqualityTestObjectA.class.getDeclaredConstructor(String.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private TypeConstructor getNiladicConstructor() {
        try {
            return Diamond.constructors().from(ConstructorEqualityTestObjectA.class.getDeclaredConstructor());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private TypeConstructor getNiladicConstructorInDifferentType() {
        try {
            return Diamond.constructors().from(ConstructorEqualityTestObjectB.class.getDeclaredConstructor());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

}