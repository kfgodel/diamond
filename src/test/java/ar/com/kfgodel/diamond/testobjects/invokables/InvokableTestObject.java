package ar.com.kfgodel.diamond.testobjects.invokables;

/**
 * This type serves as a testing object for invokable types
 * Created by kfgodel on 29/10/14.
 */
public class InvokableTestObject {

    public InvokableTestObject(){

    }

    public InvokableTestObject(int a, int b){

    }

    public static int staticSumMethod(int a, int b){
        return a + b;
    }

    public int instanceMultiply(int a, int b){
        return a * b;
    }

    public static void staticVoidMethod(){

    }

    public static int staticField;
    public int instanceField;
}
