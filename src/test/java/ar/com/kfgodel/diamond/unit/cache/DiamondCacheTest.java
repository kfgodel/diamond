package ar.com.kfgodel.diamond.unit.cache;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.constructors.TypeConstructor;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.CachedMemberTestObject;
import info.kfgodel.jspek.api.JavaSpec;
import info.kfgodel.jspek.api.JavaSpecRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the correct cache of instances
 * Created by kfgodel on 09/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class DiamondCacheTest extends JavaSpec<DiamondTestContext> {
  @Override
  public void define() {
    describe("diamond instance cache", () -> {

      it("is used types accessed from its native representation", () -> {
        TypeInstance firstInstance = Diamond.of(CachedMemberTestObject.class);
        TypeInstance secondInstance = Diamond.of(CachedMemberTestObject.class);

        assertThat(firstInstance).isSameAs(secondInstance);
      });

      it("is used for methods accessed from its native version", () -> {
        Method nativeMethod = null;
        try {
          nativeMethod = CachedMemberTestObject.class.getDeclaredMethod("method");
        } catch (NoSuchMethodException e) {
          throw new RuntimeException("failed test", e);
        }
        TypeMethod firstInstance = Diamond.methods().from(nativeMethod);
        TypeMethod secondInstance = Diamond.methods().from(nativeMethod);
        assertThat(firstInstance).isSameAs(secondInstance);
      });

      it("is used for fields accessed from its native version", () -> {
        Field nativeField = null;
        try {
          nativeField = CachedMemberTestObject.class.getDeclaredField("field");
        } catch (NoSuchFieldException e) {
          throw new RuntimeException("failed test", e);
        }
        TypeField firstInstance = Diamond.fields().from(nativeField);
        TypeField secondInstance = Diamond.fields().from(nativeField);
        assertThat(firstInstance).isSameAs(secondInstance);
      });

      it("is used for constructor accessed from its native version", () -> {
        Constructor nativeConstructor = null;
        try {
          nativeConstructor = CachedMemberTestObject.class.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
          throw new RuntimeException("failed test", e);
        }
        TypeConstructor firstInstance = Diamond.constructors().from(nativeConstructor);
        TypeConstructor secondInstance = Diamond.constructors().from(nativeConstructor);
        assertThat(firstInstance).isSameAs(secondInstance);
      });

      it("can be invalidated", () -> {
        TypeInstance firstInstance = Diamond.of(CachedMemberTestObject.class);
        Diamond.cache().invalidate();
        TypeInstance secondInstance = Diamond.of(CachedMemberTestObject.class);

        assertThat(firstInstance).isNotSameAs(secondInstance);

      });
    });
  }
}
