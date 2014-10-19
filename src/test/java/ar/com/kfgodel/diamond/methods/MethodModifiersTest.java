package ar.com.kfgodel.diamond.methods;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.sources.modifiers.MethodModifier;
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

            it("can be static",()->{
                TypeMethod method = getStaticMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(Mutability.STATIC);
            });

            it("can be final",()->{
                TypeMethod method = getFinalMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(Mutability.FINAL);
            });

            it("can be native",()->{
                TypeMethod method = getNativeMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(MethodModifier.NATIVE);
            }); 
            
            it("can be synchronized",()->{
                TypeMethod method = getSyncMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(MethodModifier.SYNCHRONIZED);
            }); 
            
            it("can be strictfp",()->{
                TypeMethod method = getStrictMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(MethodModifier.STRICTFP);
            });

            it("can be abstract",()->{
                TypeMethod method = getAbstractMethod();
                assertThat(method.modifiers().collect(Collectors.toList()))
                        .contains(MethodModifier.ABSTRACT);
            });
        });
    }

    private TypeMethod getAbstractMethod() {
        return getTypeMethod(AbstractMemberTestObject.class);
    }

    private TypeMethod getStrictMethod() {
        return getTypeMethod(StrictfpMemberTestObject.class);
    }

    private TypeMethod getSyncMethod() {
        return getTypeMethod(SynchronizedMemberTestObject.class);
    }

    private TypeMethod getNativeMethod() {
        return getTypeMethod(NativeMemberTestObject.class);
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
    private TypeMethod getStaticMethod() {
        return getTypeMethod(StaticMembersTestObject.class);
    }
    private TypeMethod getFinalMethod() {
        return getTypeMethod(FinalMembersTestObject.class);
    }

    private TypeMethod getTypeMethod(Class<?> clase) {
        try {
            return Diamond.methods().from(clase.getDeclaredMethod("method"));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

}