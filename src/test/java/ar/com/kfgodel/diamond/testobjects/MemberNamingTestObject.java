package ar.com.kfgodel.diamond.testobjects;

import ar.com.kfgodel.diamond.testobjects.annotations.*;

import java.util.List;

/**
 * This type serves as a test object for naming and declaration test
 * Created by kfgodel on 01/11/14.
 */
public class MemberNamingTestObject {

    @TestAnnotation1
    public <R> MemberNamingTestObject(Integer a){

    }

    @FieldTestAnnotation
    public @TestAnnotation2 List<String> stringList;

    @MethodTestAnnotation
    public @TestAnnotation3 <S>   int methodWithArgs(String a, S b){
        return 0;
    }
}
