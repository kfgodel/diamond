package ar.com.kfgodel.diamond.unit.testobjects.annotations;

/**
 * This type serves as a test object for annotation tests
 * Created by kfgodel on 01/11/14.
 */
public class MemberAnnotationTestObject {

    int unannotatedField;

    @TestAnnotation1
    @TestAnnotation2
    int annotatedField;

    void unannotatedMethod(){

    }

    @TestAnnotation1
    @TestAnnotation2
    void annotatedMethod(){


    }

    MemberAnnotationTestObject(){

    }

    @TestAnnotation1
    @TestAnnotation2
    MemberAnnotationTestObject(Integer a){

    }

}
