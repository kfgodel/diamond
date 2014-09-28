package ar.com.kfgodel.diamond.impl.generics;

/**
 * This type represents  type parameterization were no relation to subtype is involved, thus
 * all original supertype arguments are preserved
 * Created by kfgodel on 27/09/14.
 */
public class NoParameterization implements SupertypeParameterization {

    public static final NoParameterization INSTANCE = new NoParameterization();

}
