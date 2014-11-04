package ar.com.kfgodel.diamond.testobjects.lineage;

import ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.annotations.TestAnnotation2;
import ar.com.kfgodel.diamond.testobjects.interfaces.ParentInterface2;

import java.io.Serializable;
import java.util.List;

/**
 * This type represents a middle ancestor type
 * Created by kfgodel on 19/09/14.
 */
public class ParentClass<@TestAnnotation1 P1,P2> extends GrandParentClass<@TestAnnotation2 P2> implements ParentInterface2<Integer, Integer> {

    private int aParentsPrivateField;
    public List<String> aParentsPublicField;


    public void aParentMethod(){

    }

    protected ParentClass(Number number){

    }

    public ParentClass(Number number, Serializable serializable){

    }

}
