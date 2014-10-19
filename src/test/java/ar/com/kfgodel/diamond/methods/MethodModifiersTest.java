package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
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
public class MethodModifiersTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {
        describe("a method modifier", () -> {
            it("can be public",()->{
                TypeMethod method = getPublicMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PUBLIC);
            });

            it("can be private",()->{
                TypeMethod method = getPrivateMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PRIVATE);
            });

            it("can be protected",()->{
                TypeMethod method = getProtectedMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PROTECTED);
            });

            it("can be package (default)",()->{
                TypeMethod method = getDefaultMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(Visibility.PACKAGE);
            });
        });
    }

    private TypeMethod getPublicMethod() {
        return getTypeMethod(PublicMembersTestObject.class);
    }
    private TypeMethod getPrivateMethod() {
        return getTypeMethod(PrivateMembersTestObject.class);
    }
    private TypeMethod getProtectedMethod() {
        return getTypeMethod(ProtectedMembersTestObject.class);
    }
    private TypeMethod getDefaultMethod() {
        return getTypeMethod(DefaultMembersTestObject.class);
    }

    private TypeMethod getTypeMethod(Class<?> clase) {
        try {
            return Diamond.methods().from(clase.getDeclaredMethod("method"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

}