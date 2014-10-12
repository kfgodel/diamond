package ar.com.kfgodel.diamond.testobjects.lineage;

/**
 * This type represents the lower descendant of the test lineage
 * Created by kfgodel on 19/09/14.
 */
public class ChildClass<C> extends ParentClass<C, Integer> {

    private int aPrivateField;
    protected float aProtectedField;
    public Object aPublicField;
    String aDefaultField;
    static public Double aStaticField;


    public void aPublicMethod(){
    }

    protected void aProtectedMethod(){

    }

    private void aPrivateMethod(){

    }

    void aDefaultMethod(){

    }

    public static void aStaticMethod(){

    }
}
