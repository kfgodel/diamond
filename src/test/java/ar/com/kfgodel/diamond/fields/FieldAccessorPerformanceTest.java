package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.testobjects.accessors.FieldAccessorTestObject;
import org.junit.runner.RunWith;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by kfgodel on 24/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldAccessorPerformanceTest extends JavaSpec<DiamondTestContext> {

    public static final int ITERATIONS = 100000000;
    public static final String FIELD_NAME = "publicField";

    @Override
    public void define() {
        describe("performance to access a field value", () -> {

            it("requires warmup",()->{
                FieldAccessorTestObject object = new FieldAccessorTestObject();
                for (int i = 0; i < ITERATIONS; i++) {
                    object.publicField = object.publicField + 2;
                }
            });

            it("fastest with direct access",()->{
                measureTest("public field", (object) -> {
                    for (int i = 0; i < ITERATIONS; i++) {
                        object.publicField = object.publicField + 2;
                    }
                });
            });

            it("2fastest with direct getter&setter access",()->{
                measureTest("getter&setter", (object) -> {
                    for (int i = 0; i < ITERATIONS; i++) {
                        object.setPublicField(object.getPublicField()+2);
                    }
                });
            });
            it("3fastest with method-handles access",()->{
                try {
                    MethodHandle getter = MethodHandles.lookup().findGetter(FieldAccessorTestObject.class, FIELD_NAME, int.class);
                    MethodHandle setter = MethodHandles.lookup().findSetter(FieldAccessorTestObject.class, FIELD_NAME, int.class);
                    measureTest("methodhandle", (object) -> {
                        for (int i = 0; i < ITERATIONS; i++) {
                            try {
                                setter.invokeExact(object, (int)getter.invokeExact(object) + 2);
                            } catch (Throwable e) {
                                throw new RuntimeException("Unexpected test error", e);
                            }
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });
            it("4fastest with reflection access",()->{
                try {
                    Field field = FieldAccessorTestObject.class.getDeclaredField(FIELD_NAME);
                    measureTest("reflection", (object) -> {
                        for (int i = 0; i < ITERATIONS; i++) {
                            try {
                                field.set(object, (Integer) field.get(object) + 1);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException("Unexpected test error", e);
                            }
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });
            it("5fastest with typeField access",()->{
                TypeField field = Diamond.of(FieldAccessorTestObject.class).fields().existingNamed(FIELD_NAME);
                measureTest("typeField", (object) -> {
                    for (int i = 0; i < ITERATIONS; i++) {
                        field.setValueOn(object, field.<Integer>getValueFrom(object) + 2);
                    }
                });
            });
        });

    }

    private void measureTest(String testName, Consumer<FieldAccessorTestObject> testCode) {
        FieldAccessorTestObject object = new FieldAccessorTestObject();
        long started = System.nanoTime();
        testCode.accept(object);
        long ended = System.nanoTime();
        System.out.println(testName +": " + TimeUnit.NANOSECONDS.toMillis(ended - started) +  "ms");
    }
}