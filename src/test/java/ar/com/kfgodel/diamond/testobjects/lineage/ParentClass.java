package ar.com.kfgodel.diamond.testobjects.lineage;

import ar.com.kfgodel.diamond.testobjects.TestAnnotation1;
import ar.com.kfgodel.diamond.testobjects.TestAnnotation2;

import java.util.List;

/**
 * This type represents a middle ancestor type
 * Created by kfgodel on 19/09/14.
 */
public class ParentClass<@TestAnnotation1 P1,P2> extends GrandParentClass<@TestAnnotation2 P2> {

    private int aParentsPrivateField;
    public List<String> aParentsPublicField;


    public void aParentMethod(){

    }

}
