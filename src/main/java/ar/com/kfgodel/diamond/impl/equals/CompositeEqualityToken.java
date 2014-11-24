package ar.com.kfgodel.diamond.impl.equals;

import ar.com.kfgodel.diamond.api.equals.EqualsStructure;

import java.util.Arrays;
import java.util.Collection;

/**
 * This type represents an equality token composed of several parts.<br>
 *     It's equal to other equally composed tokens
 * Created by kfgodel on 23/11/14.
 */
public class CompositeEqualityToken implements EqualsStructure {

    private Object[] parts;

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || !(obj instanceof EqualsStructure)){
            return false;
        }
        EqualsStructure other = (EqualsStructure) obj;
        return Arrays.equals(this.parts, other.getParts());
    }

    @Override
    public Object[] getParts() {
        return parts;
    }

    public static CompositeEqualityToken create(Object... parts) {
        CompositeEqualityToken structure = new CompositeEqualityToken();
        structure.parts = parts;
        return structure;
    }

    public static CompositeEqualityToken create(Collection<?> parts) {
        return create(parts.toArray());
    }


}
