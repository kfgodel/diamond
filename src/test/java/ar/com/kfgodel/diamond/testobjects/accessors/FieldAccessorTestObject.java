package ar.com.kfgodel.diamond.testobjects.accessors;

/**
 * This type serves as a test object for field accessors
 * Created by kfgodel on 23/10/14.
 */
public class FieldAccessorTestObject {

    private int privateField;
    int defaultField;
    protected int protectedField;
    public int publicField;

    public int getPrivateField() {
        return privateField;
    }

    public void setPrivateField(int privateField) {
        this.privateField = privateField;
    }

    public int getDefaultField() {
        return defaultField;
    }

    public void setDefaultField(int defaultField) {
        this.defaultField = defaultField;
    }

    public int getProtectedField() {
        return protectedField;
    }

    public void setProtectedField(int protectedField) {
        this.protectedField = protectedField;
    }

    public int getPublicField() {
        return publicField;
    }

    public void setPublicField(int publicField) {
        this.publicField = publicField;
    }
}
