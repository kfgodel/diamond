package ar.com.kfgodel.diamond.impl.types.equality;

import ar.com.kfgodel.diamond.api.equals.CompositeIdentityToken;
import ar.com.kfgodel.diamond.api.types.TypeInstance;
import ar.com.kfgodel.diamond.impl.equals.ImmutableIdentityParts;

import java.util.stream.Collectors;

/**
 * This type represents the reification of the equality concept for types
 * Created by kfgodel on 04/10/14.
 */
public class TypeEquality {

    public static final TypeEquality INSTANCE = new TypeEquality();

    public boolean areEquals(TypeInstance one, Object obj){
        if(one == obj){
            return true;
        }
        if(one == null || obj == null || !(obj instanceof TypeInstance)){
            return false;
        }
        TypeInstance other = (TypeInstance) obj;
        return one.getIdentityToken().equals(other.getIdentityToken());
    }

    public CompositeIdentityToken calculateTokenFor(TypeInstance typeInstance) {
        return ImmutableIdentityParts.create(
                typeInstance.names().bareName(),
                typeInstance.componentType().orElse(null),
                ImmutableIdentityParts.create(typeInstance.generics().arguments().collect(Collectors.toList())),
                ImmutableIdentityParts.create(typeInstance.generics().bounds().upper().collect(Collectors.toList())),
                ImmutableIdentityParts.create(typeInstance.generics().bounds().lower().collect(Collectors.toList())));
    }
}
