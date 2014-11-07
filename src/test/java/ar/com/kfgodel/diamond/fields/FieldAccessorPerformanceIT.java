package ar.com.kfgodel.diamond.fields;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.DiamondTestContext;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;
import ar.com.kfgodel.diamond.testobjects.accessors.FieldAccessorTestObject;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * This type tests the performance of accessing fields with different options
 * Created by kfgodel on 24/10/14.
 */
@RunWith(JavaSpecRunner.class)
public class FieldAccessorPerformanceIT extends JavaSpec<DiamondTestContext> {
    public static Logger LOG = LoggerFactory.getLogger(FieldAccessorPerformanceIT.class);

    public static final int ITERATIONS = 100_000_000;
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

            it("1 fastest with direct access",()->{
                measureTest("1. public field", (object) -> {
                    for (int i = 0; i < ITERATIONS; i++) {
                        object.publicField = object.publicField + 2;
                    }
                });
            });

            it("2 fastest with direct getter&setter access",()->{
                measureTest("2. getter&setter", (object) -> {
                    for (int i = 0; i < ITERATIONS; i++) {
                        object.setPublicField(object.getPublicField()+2);
                    }
                });
            });
            it("3 fastest with method-handles access",()->{
                try {
                    MethodHandle getter = MethodHandles.lookup().findGetter(FieldAccessorTestObject.class, FIELD_NAME, int.class);
                    MethodHandle setter = MethodHandles.lookup().findSetter(FieldAccessorTestObject.class, FIELD_NAME, int.class);
                    measureTest("3. methodhandle", (object) -> {
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
            it("4 fastest with exact unreflected method-handles access",()->{
                try {
                    Field field = FieldAccessorTestObject.class.getDeclaredField(FIELD_NAME);
                    MethodHandle getter = MethodHandles.lookup().unreflectGetter(field);
                    MethodHandle setter = MethodHandles.lookup().unreflectSetter(field);
                    measureTest("4. unreflected methodhandle", (object) -> {
                        for (int i = 0; i < ITERATIONS; i++) {
                            invokeExactlyWithHandles(getter, setter, object);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });
            it("5 fastest with reflection access",()->{
                try {
                    Field field = FieldAccessorTestObject.class.getDeclaredField(FIELD_NAME);
                    measureTest("5. reflection", (object) -> {
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
            it("6 fastest with typeField access",()->{
                TypeField field = Diamond.of(FieldAccessorTestObject.class).fields().named(FIELD_NAME).get();
                measureTest("6. typeField", (object) -> {
                    for (int i = 0; i < ITERATIONS; i++) {
                        field.setValueOn(object, field.<Integer>getValueFrom(object) + 2);
                    }
                });
            });
            it("7 fastest with converted unreflected method-handles access",()->{
                try {
                    Field field = FieldAccessorTestObject.class.getDeclaredField(FIELD_NAME);
                    MethodHandle originalGetter = MethodHandles.lookup().unreflectGetter(field);
                    MethodHandle originalSetter = MethodHandles.lookup().unreflectSetter(field);

                    MethodHandle getter = originalGetter.asType(MethodType.methodType(Integer.class, Object.class));
                    MethodHandle setter = originalSetter.asType(MethodType.methodType(void.class, Object.class, Object.class));

                    measureTest("7. converted methodhandle", (object) -> {
                        for (int i = 0; i < ITERATIONS; i++) {
                            invokeConvertedWithHandles(getter, setter, object);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });

        });

    }

    private void invokeExactlyWithHandles(MethodHandle getter, MethodHandle setter, FieldAccessorTestObject object) {
        try {
            setter.invokeExact(object, (int)getter.invokeExact(object) + 2);
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private void invokeConvertedWithHandles(MethodHandle originalGetter, MethodHandle originalSetter, Object object) {
        try {
            Object i = (Integer) originalGetter.invokeExact(object) + 2;
            originalSetter.invokeExact(object, i);
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private void measureTest(String testName, Consumer<FieldAccessorTestObject> testCode) {
        FieldAccessorTestObject object = new FieldAccessorTestObject();
        long started = System.nanoTime();
        testCode.accept(object);
        long ended = System.nanoTime();
        LOG.info("{}: {} ms", testName , TimeUnit.NANOSECONDS.toMillis(ended - started));
    }
}