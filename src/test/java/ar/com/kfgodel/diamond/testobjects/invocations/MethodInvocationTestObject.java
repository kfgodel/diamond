package ar.com.kfgodel.diamond.testobjects.invocations;

/**
 * This type serves for testing method invocations
 * Created by kfgodel on 25/10/14.
 */
public class MethodInvocationTestObject {

    public int publicMethod(){
        return 1;
    }

    protected int protectedMethod(){
        return 2;
    }

    int defaultMethod(){
        return 3;
    }

    private int privateMethod(){
        return 4;
    }

    public void exceptionMethod(){
        throw new RuntimeException("I don't finish successfully");
    }

    @Override
    public String toString() {
        return "a test instance";
    }
}
