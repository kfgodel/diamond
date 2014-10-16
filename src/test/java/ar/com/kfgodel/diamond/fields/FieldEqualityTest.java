package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.fields.equality.FieldEquality;
import ar.com.kfgodel.diamond.testobjects.FieldEqualityTestObjectA;
import ar.com.kfgodel.diamond.testobjects.FieldEqualityTestObjectB;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of field equality comparison
 * Created by kfgodel on 16/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldEqualityTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("field equality", () -> {

            it("is reified by FieldEquality", () -> {
                TypeField stringField = getStringAField();
                assertThat(FieldEquality.INSTANCE.areEquals(stringField, stringField)).
                        isTrue();
            });

            it("is false if names mismatch",()->{
                TypeField stringAField = getStringAField();
                TypeField stringBField = getStringBField();
                assertThat(stringAField).isNotEqualTo(stringBField);
            });

            it("is false if type mismatch",()->{
                TypeField stringAField = getStringAField();
                TypeField numberAField = getNumberAField();
                assertThat(stringAField).isNotEqualTo(numberAField);
            });

            xit("is false if declaring type doesn't match", () -> {

            });

            it("is true if name, type and declaring type match", () -> {
                TypeField stringAField1 = getStringAField();
                TypeField stringAField2 = getStringAField();
                assertThat(stringAField1).isEqualTo(stringAField2);
            });
        });
    }

    private TypeField getNumberAField() {
        try {
            return Diamond.fields().from(FieldEqualityTestObjectB.class.getDeclaredField("a"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeField getStringBField() {
        try {
            return Diamond.fields().from(FieldEqualityTestObjectA.class.getDeclaredField("b"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeField getStringAField() {
        try {
            return Diamond.fields().from(FieldEqualityTestObjectA.class.getDeclaredField("a"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }


}