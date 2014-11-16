package ar.com.kfgodel.diamond.unit.testobjects.invokables;

/**
 * This type serves as test object for composite function tests
 * Created by kfgodel on 17/11/14.
 */
public class CompositeFunctionTestObject {

    private CompositeFunctionTestObject a = this;
    private CompositeFunctionTestObject b = this;
    private CompositeFunctionTestObject c = this;

    public CompositeFunctionTestObject getA() {
        return a;
    }

    public CompositeFunctionTestObject getB() {
        return b;
    }

    public CompositeFunctionTestObject getC() {
        return c;
    }
}
