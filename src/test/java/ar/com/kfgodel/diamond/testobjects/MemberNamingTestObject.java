package ar.com.kfgodel.diamond.testobjects;

import java.util.List;

/**
 * This type serves as a test object for naming and declaration test
 * Created by kfgodel on 01/11/14.
 */
public class MemberNamingTestObject {

    @TestAnnotation1
    public <R> MemberNamingTestObject(Integer a){

    }

    @TestAnnotation2
    public @TestAnnotation1 List<String> stringList;

    @TestAnnotation3
    public <S> int methodWithArgs(String a, S b){
        return 0;
    }
}
