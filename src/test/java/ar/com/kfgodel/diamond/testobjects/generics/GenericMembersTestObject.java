package ar.com.kfgodel.diamond.testobjects.generics;

import java.util.Map;

/**
 * This type serves as a test object for generics information on member types
 * Created by kfgodel on 01/11/14.
 */
public class GenericMembersTestObject<T> {

    T field;

    <R> Map<T, R> method(){
        return null;
    }

    <S> GenericMembersTestObject(S s){

    }

}
