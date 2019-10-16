package ar.com.kfgodel.diamond.unit.testobjects.methods;

/**
 * This type serves as a test object for instance methods as functions
 * Created by kfgodel on 26/10/14.
 */
public class FunctionalInstanceMethodTestObject {

  public int lastResult;

  public int getLastResult() {
    return lastResult;
  }

  public void consumer() {
    lastResult = 7;
  }

  public void biConsumer(int param) {
    lastResult = param;
  }

  public int function() {
    return 21;
  }

}
