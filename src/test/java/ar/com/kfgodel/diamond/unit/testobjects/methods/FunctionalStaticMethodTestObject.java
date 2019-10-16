package ar.com.kfgodel.diamond.unit.testobjects.methods;

/**
 * This type serves as a test object for static methods as functions
 * Created by kfgodel on 26/10/14.
 */
public class FunctionalStaticMethodTestObject {

  public static int lastResult;

  public static void runnable() {
    lastResult = 10;
  }

  public static void consumer(int param) {
    lastResult = param;
  }

  public static void biConsumer(int param1, int param2) {
    lastResult = param1 + param2;
  }

  public static int supplier() {
    return 1;
  }

  public static int function(int param) {
    return param;
  }
}
