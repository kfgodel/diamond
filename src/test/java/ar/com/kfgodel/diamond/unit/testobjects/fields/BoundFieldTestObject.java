package ar.com.kfgodel.diamond.unit.testobjects.fields;

/**
 * This type serves as a test object for bound field tests
 * Created by kfgodel on 16/11/14.
 */
public class BoundFieldTestObject {

    public int field;

    public int otherField;

    //Redefined to make test fail, and force bound equality use identity comparison
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoundFieldTestObject;
    }

    // Redefined to make test assertion predictable
    @Override
    public String toString() {
        return "BoundFieldTestObject instance";
    }
}
