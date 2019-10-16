package ar.com.kfgodel.diamond.unit.testobjects.methods;

/**
 * This type serves as a test object for bound method tests
 * Created by kfgodel on 16/11/14.
 */
public class BoundMethodTestObject {

  public int sum(int a, int b) {
    return a + b;
  }

  public String invokedName = "none";

  public void runnable() {
    invokedName = "no-arg void";
  }

  public void consumer(int arg) {
    invokedName = "(" + arg + ") void";
  }

  public void biconsumer(int arg1, int arg2) {
    invokedName = "(" + arg1 + ", " + arg2 + ") void";
  }

  public int supplier() {
    invokedName = "no-arg non-void";
    return 3;
  }

  public int function(String arg) {
    invokedName = "(" + arg + ") non-void";
    return arg.length();
  }

  public int unaryOperator(int arg) {
    invokedName = "unary (" + arg + ") non-void";
    return -arg;
  }

  public boolean predicate(int arg) {
    invokedName = "(" + arg + ") boolean";
    return arg > 0;
  }

  // Redefined to force an identity comparison in bound method test
  @Override
  public boolean equals(Object obj) {
    return obj instanceof BoundMethodTestObject;
  }

  // Redefined to make test assertions predictable
  @Override
  public String toString() {
    return "BoundMethodTestObject instance";
  }
}
