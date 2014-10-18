package ar.com.kfgodel.diamond.impl.methods.equality;

import ar.com.kfgodel.diamond.api.methods.TypeMethod;
import ar.com.kfgodel.streams.StreamEquality;

/**
 * This type represents the equality criteria to compare methods
 * Created by kfgodel on 16/10/14.
 */
public class MethodEquality {

    public static final MethodEquality INSTANCE = new MethodEquality();

    public boolean areEquals(TypeMethod one, Object obj){
        if(!(obj instanceof TypeMethod)){
            return false;
        }
        TypeMethod other = (TypeMethod) obj;
        if(!one.name().equals(other.name())){
            return false;
        }
        if(!one.declaringType().equals(other.declaringType())){
            return false;
        }
        if(!one.returnType().equals(other.returnType())){
            return false;
        }
        boolean haveEqualParameters = StreamEquality.INSTANCE.areEquals(one.parameterTypes(), other.parameterTypes());
        return haveEqualParameters;
    }
}
