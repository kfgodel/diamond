package ar.com.kfgodel.diamond.unit.testobjects.methods;

/**
 * This type serves as a test object for method call tests
 * Created by kfgodel on 17/11/14.
 */
public class MethodCallTestObject {

    public String instanceInvoked = "none";
    public static String staticInvoked = "none";

    private Object firstParam;
    public MethodCallTestObject(Object a, Object b, Object c){
        staticInvoked = "constructor("+a+", "+b+", " + c +")";
        firstParam = a;
    }

    public Object function(Object a, Object b, Object c){
        instanceInvoked = "function("+a+", "+b+", " + c +")";
        return a;
    }

    public void consumer(Object a, Object b, Object c){
        instanceInvoked = "consumer("+a+", "+b+", " + c +")";
    }

    static int supplier(Object a, Object b, Object c){
        staticInvoked = "supplier("+a+", "+b+", " + c +")";
        return 1;
    }

    static void runnable(Object a, Object b, Object c){
        staticInvoked = "runnable("+a+", "+b+", " + c +")";
    }

    public boolean createdWith(Object a){
        return firstParam.equals(a);
    }
}
