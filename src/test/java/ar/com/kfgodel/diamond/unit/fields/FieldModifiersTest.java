package ar.com.kfgodel.diamond.unit.fields;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.DefaultMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.FinalMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PrivateMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.ProtectedMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.StaticMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.TransientMemberTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.VolatileMemberTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior the modifiers for constructors
 * Created by kfgodel on 18/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldModifiersTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a field modifier", () -> {
            it("can be public",()->{
                TypeField field = getPublicField();
                assertThat(field.is(Modifiers.PUBLIC));
            });

            it("can be private",()->{
                TypeField field = getPrivateField();
                assertThat(field.is(Modifiers.PRIVATE));
            });

            it("can be protected",()->{
                TypeField field = getProtectedField();
                assertThat(field.is(Modifiers.PROTECTED));
            });

            it("can be package (default)",()->{
                TypeField field = getDefaultField();
                assertThat(field.is(Modifiers.PACKAGE));
            });
            
            it("can be static",()->{
                TypeField field = getStaticField();
                assertThat(field.is(Modifiers.STATIC));
            });

            it("can be final",()->{
                TypeField field = getFinalField();
                assertThat(field.is(Modifiers.FINAL));
            });

            it("can be transient",()->{
                TypeField field = getTransientField();
                assertThat(field.is(Modifiers.TRANSIENT));
            });

            it("can be volatile",()->{
                TypeField field = getVolatileField();
                assertThat(field.is(Modifiers.VOLATILE));
            });
        });
    }

    private TypeField getVolatileField() {
        return getTypeField(VolatileMemberTestObject.class);
    }

    private TypeField getTransientField() {
        return getTypeField(TransientMemberTestObject.class);
    }

    private TypeField getFinalField() {
        return getTypeField(FinalMembersTestObject.class);
    }

    private TypeField getStaticField() {
        return getTypeField(StaticMembersTestObject.class);
    }

    private TypeField getPublicField() {
        return getTypeField(PublicMembersTestObject.class);
    }
    private TypeField getPrivateField() {
        return getTypeField(PrivateMembersTestObject.class);
    }
    private TypeField getProtectedField() {
        return getTypeField(ProtectedMembersTestObject.class);
    }
    private TypeField getDefaultField() {
        return getTypeField(DefaultMembersTestObject.class);
    }

    private TypeField getTypeField(Class<?> clase) {
        try {
            return Diamond.fields().from(clase.getDeclaredField("field"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

}