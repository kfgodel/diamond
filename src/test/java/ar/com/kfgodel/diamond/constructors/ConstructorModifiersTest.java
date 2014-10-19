package ar.com.kfgodel.diamond.constructors;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
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
public class ConstructorModifiersTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a constructor modifier", () -> {
            it("can be public",()->{
                TypeConstructor constructor = getPublicConstructor();
                assertThat(constructor.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PUBLIC);
            });

            it("can be private",()->{
                TypeConstructor constructor = getPrivateConstructor();
                assertThat(constructor.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PRIVATE);
            });

            it("can be protected",()->{
                TypeConstructor constructor = getProtectedConstructor();
                assertThat(constructor.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PROTECTED);
            });

            it("can be package (default)",()->{
                TypeConstructor constructor = getDefaultConstructor();
                assertThat(constructor.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PACKAGE);
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