package ar.com.kfgodel.diamond.impl.equals;

import ar.com.kfgodel.diamond.api.equals.CompositeEqualityToken;

import java.util.Arrays;
import java.util.Collection;

/**
 * This type represents an equality token composed of several parts.<br>
 *     It's equal to other equally composed tokens
 * Created by kfgodel on 23/11/14.
 */
public class ArrayCompositeEqualityToken implements CompositeEqualityToken {

    private Object[] parts;

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || !(obj instanceof CompositeEqualityToken)){
            return false;
        }
        CompositeEqualityToken other = (CompositeEqualityToken) obj;
        return Arrays.equals(this.parts, other.getParts());
    }

    @Override
    public Object[] getParts() {
        return parts;
    }

    public static ArrayCompositeEqualityToken create(Object... parts) {
        ArrayCompositeEqualityToken structure = new ArrayCompositeEqualityToken();
        structure.parts = parts;
        return structure;
    }

    public static ArrayCompositeEqualityToken create(Collection<?> parts) {
        return create(parts.toArray());
    }


}
