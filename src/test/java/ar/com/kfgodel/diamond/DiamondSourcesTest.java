package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.ClassField;
import ar.com.kfgodel.diamond.api.ClassMethod;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.testobjects.ClassWithIdField;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type tests ways to access diamond instances
 * Created by kfgodel on 17/09/14.
 */
@RunWith(JavaSpecRunner.class)
public class DiamondSourcesTest extends JavaSpec<DiamondTestContext> {
    @Override
    public void define() {

        describe("diamond has its own reflection types", () -> {

            describe("classes", () -> {
                it("can be obtained from Class instances", () -> {
                    TypeInstance diamondClass = Diamond.classes().from(Object.class);
                    assertThat(diamondClass.name()).isEqualTo("Object");
                });
                it("can be obtained from complete class names", ()->{
                    TypeInstance diamondClass = Diamond.classes().named("java.lang.Object");
                    assertThat(diamondClass.name()).isEqualTo("Object");
                });
                it("have a special shortcut", ()->{
                    TypeInstance diamondClass = Diamond.of(Object.class);
                    assertThat(diamondClass.name()).isEqualTo("Object");
                });
            });
            describe("methods", ()->{
                it("can be obtained from Method instances", ()->{
                    Method methodInstance = null;
                    try {
                        methodInstance = Object.class.getMethod("equals", Object.class);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("This is why reflection api turns difficult to use",e);
                    }
                    ClassMethod diamondMethod = Diamond.methods().from(methodInstance);
                    assertThat(diamondMethod.name()).isEqualTo("equals");
                });

                xit("can be obtained from a Class instance and a method name", () -> {
                    ClassMethod diamondMethod = Diamond.methods().in(Object.class).identifiedAs("equals", Object.class);
                    assertThat(diamondMethod.name()).isEqualTo("equals");
                });
            });
            xdescribe("fields", ()->{
                it("can be obtained from Field instances", ()->{
                    Field fieldInstance = null;
                    try {
                        fieldInstance = ClassWithIdField.class.getDeclaredField("id");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException("This is why reflection api turns difficult to use",e);
                    }
                    ClassField diamondField = Diamond.fields().from(fieldInstance);
                    assertThat(diamondField.name()).isEqualTo("id");
                });
                it("can be obtained from a Class instance and a field name", ()->{
                    ClassField diamondField = Diamond.fields().in(ClassWithIdField.class).named("id");
                    assertThat(diamondField.name()).isEqualTo("id");
                });
            });
        });

    }
}
