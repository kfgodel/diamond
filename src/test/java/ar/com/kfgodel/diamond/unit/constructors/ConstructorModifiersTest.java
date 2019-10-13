package ar.com.kfgodel.diamond.unit.constructors;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.DefaultMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PrivateMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.ProtectedMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior the modifiers for constructors
 * Created by kfgodel on 18/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class ConstructorModifiersTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a constructor modifier", () -> {
            it("can be public",()->{
                TypeConstructor constructor = getPublicConstructor();
                assertThat(constructor.is(Modifiers.PUBLIC));
            });

            it("can be private",()->{
                TypeConstructor constructor = getPrivateConstructor();
                assertThat(constructor.is(Modifiers.PRIVATE));
            });

            it("can be protected",()->{
                TypeConstructor constructor = getProtectedConstructor();
                assertThat(constructor.is(Modifiers.PROTECTED));
            });

            it("can be package (default)",()->{
                TypeConstructor constructor = getDefaultConstructor();
                assertThat(constructor.is(Modifiers.PACKAGE));
            });
        });
    }

    private TypeConstructor getPublicConstructor() {
        return getTypeConstructor(PublicMembersTestObject.class);
    }
    private TypeConstructor getPrivateConstructor() {
        return getTypeConstructor(PrivateMembersTestObject.class);
    }
    private TypeConstructor getProtectedConstructor() {
        return getTypeConstructor(ProtectedMembersTestObject.class);
    }
    private TypeConstructor getDefaultConstructor() {
        return getTypeConstructor(DefaultMembersTestObject.class);
    }

    private TypeConstructor getTypeConstructor(Class<?> clase) {
        try {
            return Diamond.constructors().from(clase.getDeclaredConstructor());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

}