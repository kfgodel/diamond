package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.sources.modifiers.Visibility;
import ar.com.kfgodel.diamond.testobjects.modifiers.DefaultMembersTestObject;
import ar.com.kfgodel.diamond.testobjects.modifiers.PrivateMembersTestObject;
import ar.com.kfgodel.diamond.testobjects.modifiers.ProtectedMembersTestObject;
import ar.com.kfgodel.diamond.testobjects.modifiers.PublicMembersTestObject;
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
        describe("a field modifiers", () -> {
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
        });
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