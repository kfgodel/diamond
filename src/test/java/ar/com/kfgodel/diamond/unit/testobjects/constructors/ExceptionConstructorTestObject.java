package ar.com.kfgodel.diamond.unit.testobjects.constructors;

/**
 * This type serves as a constructor test for exceptions
 * Created by kfgodel on 25/10/14.
 */
public class ExceptionConstructorTestObject {

    public ExceptionConstructorTestObject(){
        throw new RuntimeException("I don't finish successfully");
    }

}
