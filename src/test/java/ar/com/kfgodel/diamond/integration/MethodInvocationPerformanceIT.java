package ar.com.kfgodel.diamond.integration;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.diamond.unit.DiamondTestContext;
import ar.com.kfgodel.diamond.unit.testobjects.invocations.MethodInvocationTestObject;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the performance of executing methods reflectively
 * Created by kfgodel on 09/11/14.
 */
@RunWith(JavaSpecRunner.class)
public class MethodInvocationPerformanceIT extends JavaSpec<DiamondTestContext> {
    public static Logger LOG = LoggerFactory.getLogger(MethodInvocationPerformanceIT.class);

    public static final int ITERATIONS = 100_000_000;
    public static final String FIELD_NAME = "publicField";

    @Override
    public void define() {
        describe("performance to invoke methods", () -> {

            it("requires warmup",()->{
                MethodInvocationTestObject object = new MethodInvocationTestObject();
                int result = 0;
                for (int i = 0; i < ITERATIONS; i++) {
                    result = object.methodA().methodB().methodC().methodD();
                }
                assertThat(result).isEqualTo(4);
            });

            it("non reflected invocation",()->{
                measureTest("1. direct methods", (object) -> {
                    int result = 0;
                    for (int i = 0; i < ITERATIONS; i++) {
                        result = object.methodA().methodB().methodC().methodD();
                    }
                    assertThat(result).isEqualTo(4);
                });
            });

            it("converted unreflected method-handles invocation",()->{
                try {
                    Method nativeMethodA = MethodInvocationTestObject.class.getDeclaredMethod("methodA");
                    Method nativeMethodB = MethodInvocationTestObject.class.getDeclaredMethod("methodB");
                    Method nativeMethodC = MethodInvocationTestObject.class.getDeclaredMethod("methodC");
                    Method nativeMethodD = MethodInvocationTestObject.class.getDeclaredMethod("methodD");

                    MethodHandle originalMethodA = MethodHandles.lookup().unreflect(nativeMethodA);
                    MethodHandle originalMethodB = MethodHandles.lookup().unreflect(nativeMethodB);
                    MethodHandle originalMethodC = MethodHandles.lookup().unreflect(nativeMethodC);
                    MethodHandle originalMethodD = MethodHandles.lookup().unreflect(nativeMethodD);

                    MethodHandle methodA = originalMethodA.asType(MethodType.methodType(Object.class, Object.class));
                    MethodHandle methodB = originalMethodB.asType(MethodType.methodType(Object.class, Object.class));
                    MethodHandle methodC = originalMethodC.asType(MethodType.methodType(Object.class, Object.class));
                    MethodHandle methodD = originalMethodD.asType(MethodType.methodType(Object.class, Object.class));

                    measureTest("2.1 converted methodhandle", (object) -> {
                        int result = 0;
                        for (int i = 0; i < ITERATIONS; i++) {
                            result = invokeConvertedWithHandles(methodA, methodB, methodC, methodD, object);
                        }
                        assertThat(result).isEqualTo(4);
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });

            it("inexact method-handles invocation",()->{
                try {
                    MethodHandle methodA = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodA", MethodType.methodType(MethodInvocationTestObject.class));
                    MethodHandle methodB = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodB", MethodType.methodType(MethodInvocationTestObject.class));
                    MethodHandle methodC = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodC", MethodType.methodType(MethodInvocationTestObject.class));
                    MethodHandle methodD = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodD", MethodType.methodType(int.class));
                    measureTest("2.2 inexact methodhandle", (object) -> {
                        int result = 0;
                        for (int i = 0; i < ITERATIONS; i++) {
                            try {
                                Object resultA = methodA.invoke(object);
                                Object resultB = methodB.invoke(resultA);
                                Object resultC = methodC.invoke(resultB);
                                result = ((int)methodD.invoke(resultC));
                            } catch (Throwable e) {
                                throw new RuntimeException("Unexpected test error", e);
                            }
                        }
                        assertThat(result).isEqualTo(4);
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });


            it("exact method-handles invocation",()->{
                try {
                    MethodHandle methodA = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodA", MethodType.methodType(MethodInvocationTestObject.class));
                    MethodHandle methodB = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodB", MethodType.methodType(MethodInvocationTestObject.class));
                    MethodHandle methodC = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodC", MethodType.methodType(MethodInvocationTestObject.class));
                    MethodHandle methodD = MethodHandles.lookup().findVirtual(MethodInvocationTestObject.class, "methodD", MethodType.methodType(int.class));
                    measureTest("3.1 methodhandle", (object) -> {
                        int result = 0;
                        for (int i = 0; i < ITERATIONS; i++) {
                            try {
                                MethodInvocationTestObject resultA = (MethodInvocationTestObject) methodA.invokeExact(object);
                                MethodInvocationTestObject resultB = (MethodInvocationTestObject) methodB.invokeExact(resultA);
                                MethodInvocationTestObject resultC = (MethodInvocationTestObject) methodC.invokeExact(resultB);
                                result = ((int)methodD.invokeExact(resultC));
                            } catch (Throwable e) {
                                throw new RuntimeException("Unexpected test error", e);
                            }
                        }
                        assertThat(result).isEqualTo(4);
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });

            it("exact unreflected method-handles invocation",()->{
                try {
                    Method nativeMethodA = MethodInvocationTestObject.class.getDeclaredMethod("methodA");
                    Method nativeMethodB = MethodInvocationTestObject.class.getDeclaredMethod("methodB");
                    Method nativeMethodC = MethodInvocationTestObject.class.getDeclaredMethod("methodC");
                    Method nativeMethodD = MethodInvocationTestObject.class.getDeclaredMethod("methodD");

                    MethodHandle methodA = MethodHandles.lookup().unreflect(nativeMethodA);
                    MethodHandle methodB = MethodHandles.lookup().unreflect(nativeMethodB);
                    MethodHandle methodC = MethodHandles.lookup().unreflect(nativeMethodC);
                    MethodHandle methodD = MethodHandles.lookup().unreflect(nativeMethodD);
                    measureTest("3.2 exact unreflected methodhandle", (object) -> {
                        int result = 0;
                        for (int i = 0; i < ITERATIONS; i++) {
                            result = invokeExactlyWithHandles(methodA, methodB, methodC, methodD, object);
                        }
                        assertThat(result).isEqualTo(4);
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });

            it("typeMethod invocation",()->{
                TypeMethod methodA = Diamond.of(MethodInvocationTestObject.class).methods().named("methodA").get();
                TypeMethod methodB = Diamond.of(MethodInvocationTestObject.class).methods().named("methodB").get();
                TypeMethod methodC = Diamond.of(MethodInvocationTestObject.class).methods().named("methodC").get();
                TypeMethod methodD = Diamond.of(MethodInvocationTestObject.class).methods().named("methodD").get();
                measureTest("4.1 typeMehods", (object) -> {
                    int result = 0;
                    for (int i = 0; i < ITERATIONS; i++) {
                        Object resultA = methodA.invokeOn(object);
                        Object resultB = methodB.invokeOn(resultA);
                        Object resultC = methodC.invokeOn(resultB);
                        result = ((Number)methodD.invokeOn(resultC)).intValue();
                    }
                    assertThat(result).isEqualTo(4);
                });
            });

            it("native reflection invocation",()->{
                try {
                    Method nativeMethodA = MethodInvocationTestObject.class.getDeclaredMethod("methodA");
                    Method nativeMethodB = MethodInvocationTestObject.class.getDeclaredMethod("methodB");
                    Method nativeMethodC = MethodInvocationTestObject.class.getDeclaredMethod("methodC");
                    Method nativeMethodD = MethodInvocationTestObject.class.getDeclaredMethod("methodD");
                    measureTest("4.2 native reflection", (object) -> {
                        int result = 0;
                        for (int i = 0; i < ITERATIONS; i++) {
                            try {
                                Object resultA = nativeMethodA.invoke(object);
                                Object resultB = nativeMethodB.invoke(resultA);
                                Object resultC = nativeMethodC.invoke(resultB);
                                result = ((Number)nativeMethodD.invoke(resultC)).intValue();
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException("Unexpected test error", e);
                            }
                        }
                        assertThat(result).isEqualTo(4);
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected test error", e);
                }
            });

        });

    }

    private int invokeExactlyWithHandles(MethodHandle methodA, MethodHandle methodB, MethodHandle methodC, MethodHandle methodD, MethodInvocationTestObject object) {
        try {
            MethodInvocationTestObject resultA = (MethodInvocationTestObject) methodA.invokeExact(object);
            MethodInvocationTestObject resultB = (MethodInvocationTestObject) methodB.invokeExact(resultA);
            MethodInvocationTestObject resultC = (MethodInvocationTestObject) methodC.invokeExact(resultB);
            return ((int)methodD.invokeExact(resultC));
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private int invokeConvertedWithHandles(MethodHandle methodA, MethodHandle methodB, MethodHandle methodC, MethodHandle methodD, Object object) {
        try {
            Object resultA = methodA.invokeExact(object);
            Object resultB = methodB.invokeExact(resultA);
            Object resultC = methodC.invokeExact(resultB);
            Object resultD = methodD.invokeExact(resultC);
            return ((Number)resultD).intValue();
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected test error", e);
        }
    }

    private void measureTest(String testName, Consumer<MethodInvocationTestObject> testCode) {
        MethodInvocationTestObject object = new MethodInvocationTestObject();
        long started = System.nanoTime();
        testCode.accept(object);
        long ended = System.nanoTime();
        LOG.info("{}: {} ms", testName , TimeUnit.NANOSECONDS.toMillis(ended - started));
    }
}