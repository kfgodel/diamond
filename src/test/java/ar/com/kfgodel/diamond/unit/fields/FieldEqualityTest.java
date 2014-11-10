package ar.com.kfgodel.diamond.unit.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.FieldDescription;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.impl.fields.description.FieldDescriptor;
import ar.com.kfgodel.diamond.impl.fields.equality.FieldEquality;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.equality.FieldEqualityTestObjectA;
import ar.com.kfgodel.diamond.unit.testobjects.equality.FieldEqualityTestObjectB;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

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

            it("is false if declaring type doesn't match", () -> {
                TypeField stringBField = getStringBField();
                TypeField stringBFieldInDifferentType = getStringBFieldInDifferentType();
                assertThat(stringBField).isNotEqualTo(stringBFieldInDifferentType);

            });

            it("is true if name, type and declaring type match", () -> {
                TypeField stringAField1 = getStringAField();
                TypeField stringAField2 = getStringAField();
                assertThat(stringAField1).isEqualTo(stringAField2);
            });

            describe("hashcode", () -> {

                it("is equal if fields are equals",()->{
                    // Creation from description is not cached
                    Field field = null;
                    try {
                        field = FieldEqualityTestObjectB.class.getDeclaredField("b");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException("Test assumption failed",e);
                    }
                    FieldDescription fieldDescription = FieldDescriptor.INSTANCE.describe(field);
                    TypeField one = Diamond.fields().fromDescription(fieldDescription);
                    TypeField other = Diamond.fields().fromDescription(fieldDescription);

                    assertThat(one).isNotSameAs(other);
                    assertThat(one).isEqualTo(other);
                    assertThat(one.hashCode()).isEqualTo(other.hashCode());
                });   
            });

        });
    }

    private TypeField getStringBField() {
        try {
            return Diamond.fields().from(FieldEqualityTestObjectA.class.getDeclaredField("b"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("unexpected test error", e);
        }
    }

    private TypeField getStringBFieldInDifferentType() {
        try {
            return Diamond.fields().from(FieldEqualityTestObjectB.class.getDeclaredField("b"));
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