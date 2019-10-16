package ar.com.kfgodel.diamond.unit.methods;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.members.modifiers.Modifiers;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.AbstractMemberTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.DefaultMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.FinalMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.NativeMemberTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PrivateMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.ProtectedMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.PublicMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.StaticMembersTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.StrictfpMemberTestObject;
import ar.com.kfgodel.diamond.unit.testobjects.modifiers.SynchronizedMemberTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
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
      it("can be public", () -> {
        TypeMethod method = getPublicMethod();
        assertThat(method.is(Modifiers.PUBLIC));
      });

      it("can be private", () -> {
        TypeMethod method = getPrivateMethod();
        assertThat(method.is(Modifiers.PRIVATE));
      });

      it("can be protected", () -> {
        TypeMethod method = getProtectedMethod();
        assertThat(method.is(Modifiers.PROTECTED));
      });

      it("can be package (default)", () -> {
        TypeMethod method = getDefaultMethod();
        assertThat(method.is(Modifiers.PACKAGE));
      });

      it("can be static", () -> {
        TypeMethod method = getStaticMethod();
        assertThat(method.modifiers().collect(Collectors.toList()))
          .contains(Modifiers.STATIC);
      });

      it("can be final", () -> {
        TypeMethod method = getFinalMethod();
        assertThat(method.is(Modifiers.FINAL));
      });

      it("can be native", () -> {
        TypeMethod method = getNativeMethod();
        assertThat(method.is(Modifiers.NATIVE));
      });

      it("can be synchronized", () -> {
        TypeMethod method = getSyncMethod();
        assertThat(method.is(Modifiers.SYNCHRONIZED));
      });

      it("can be strictfp", () -> {
        TypeMethod method = getStrictMethod();
        assertThat(method.is(Modifiers.STRICTFP));
      });

      it("can be abstract", () -> {
        TypeMethod method = getAbstractMethod();
        assertThat(method.is(Modifiers.ABSTRACT));
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