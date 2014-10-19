package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.sources.modifiers.FieldModifier;
import ar.com.kfgodel.diamond.api.sources.modifiers.Mutability;
import ar.com.kfgodel.diamond.api.sources.modifiers.Visibility;
import ar.com.kfgodel.diamond.testobjects.modifiers.*;
import org.junit.runner.RunWith;

import java.util.stream.Collectors;

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
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PUBLIC);
            });

            it("can be private",()->{
                TypeField field = getPrivateField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PRIVATE);
            });

            it("can be protected",()->{
                TypeField field = getProtectedField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PROTECTED);
            });

            it("can be package (default)",()->{
                TypeField field = getDefaultField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PACKAGE);
            });
            
            it("can be static",()->{
                TypeField field = getStaticField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(Mutability.STATIC);
            });

            it("can be final",()->{
                TypeField field = getFinalField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(Mutability.FINAL);
            });

            it("can be transient",()->{
                TypeField field = getTransientField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(FieldModifier.TRANSIENT);
            });

            it("can be volatile",()->{
                TypeField field = getVolatileField();
                assertThat(field.modifiers().collect(Collectors.toList()))
                        .contains(FieldModifier.VOLATILE);
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